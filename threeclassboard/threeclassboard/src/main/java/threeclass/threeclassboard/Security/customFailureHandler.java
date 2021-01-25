package threeclass.threeclassboard.Security;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class customFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String msg = null;

        if(exception instanceof BadCredentialsException){
            msg = "ID OR Password Wrong";
        }else if(exception instanceof InsufficientAuthenticationException){
            msg = "Invalid Secret Key";
        }
        else if (exception instanceof AuthenticationServiceException) {
            msg = "ID OR Password Empty";

        }
        setDefaultFailureUrl("/user/login?error=true&exception="+msg);

        super.onAuthenticationFailure(request, response, exception);
    }
}
