package threeclass.threeclassboard.Security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import threeclass.threeclassboard.Service.MemberService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity //나는 스프링 시큐리티 설정할 클래스다
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;
    private final AuthenticationFailureHandler customFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 스프링 시큐리티에서 제공하는 비밀번호 암호화 객체
        // service에서 비밀번호를 암호화할 수 있도록 빈으로 등록
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 스프링 시큐리티에서 모든인증은 Authentication Manager를 통해 이루어지며
        // Authentication Manager 를 생성하기위해서는 Authentication ManagerBuilder 를 사용해야함
        // 로그인 처리 즉, 인증을 위해서는 UserDetailService를 통해서 필요한 정보를 가져오는데
        // 서비스 클래스 멤버 서비스에서 이를 처리
        // 암호화를 위해 passwordEncoder 사용
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception { //WebSecurity는 FilterChainProxy를 생성하는 필터
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
        // 해당 경로의 파일들은 시큐리티가 무시함
        // 즉, 이파일들은 무조건 통과하며, 파일기준은 resources/static 임
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //HttpSecurityhttp는 httpSecurity를 통해 http 요청에 대한 웹기반 보안을 구성 할 수있음
        // authorizeRequests()
        http.authorizeRequests()
                // HttpServletRequest에 따라 접근을 제한
                // antMatchers() 메서드로 특정 경로를 지정하며, permitall, hasRole 메서드로 역할에 따른 접근 설정을 잡아줌
                // 여기서 롤은 권한 즉 어떤 페이지는 관리자만, 어떤페이지는 회원만 접근해야할때 역할설정하는것
                // ex) .antMatchers(/admin/**).hasRole("ADMIN") /admin으로 시작하는 경로는 ADMIN을 가진 사용자만
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/board/**").access("hasRole('MEMBER') or hasRole('ADMIN')")
                .antMatchers("/post/**").access("hasRole('MEMBER') or hasRole('ADMIN')")
                .antMatchers("/comment/**").access("hasRole('MEMBER') or hasRole('ADMIN')")
                .and() //  로그인 설정
                .formLogin() // 폼 기반으로 인증, 로그인정보는 기본적으로 HttpSession이용
                .loginPage("/user/login") // /login 경로로 접근하면, 스프링시큐리티에서 제공하는 로그인 폼 사용가능
                .defaultSuccessUrl("/board/List") // 로그인 성공시 이동되는 페이지
                .failureHandler(customFailureHandler)
                .permitAll()
                .and()
                .logout() //로그아웃 지원하는 메서드
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))// 로그아웃의 기본 url이 아닌 다른 url로 매핑합니다
                .logoutSuccessUrl("/user/login")
                .invalidateHttpSession(true) // http 세션 초기화하는 작업
                .and()
                .exceptionHandling().accessDeniedPage("/user/denined");

    }
}
