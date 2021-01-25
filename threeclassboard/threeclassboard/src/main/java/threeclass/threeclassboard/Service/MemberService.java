package threeclass.threeclassboard.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import threeclass.threeclassboard.domain.Member;
import threeclass.threeclassboard.domain.Role;
import threeclass.threeclassboard.dto.MemberDto;
import threeclass.threeclassboard.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findMembers() {return memberRepository.findAll(); }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    public Member findByName(String memberName){
        return memberRepository.findByName(memberName);}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByName(username);

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("천득권").equals(username)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(member.getName(),member.getPassword(),authorities);
    }

    @Transactional
    public Long joinUser(MemberDto memberDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        Member member = new Member(memberDto.getName(), memberDto.getPassword());
        memberRepository.save(member);

        return member.getId();
    }
}
