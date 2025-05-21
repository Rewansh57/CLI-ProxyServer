package AuthenticatioFilterProcess;

import org.example.springsecuritylearning.springsecurity.Context.secondProject.model.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class CustomAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsService userDetailsService;


    public Authentication authenticate(Authentication authentication) {
        UserDetails u=userDetailsService.loadUserByUsername(authentication.getName());

        String username=authentication.getName();
        String password=authentication.getCredentials().toString();
        if (u.getPassword().equals(password)){
            return new UsernamePasswordAuthenticationToken(username,password,u.getAuthorities());

        }

    }
    @Override
    public  boolean supports(Class<?> authenticationType){
        return authenticationType.equals(UsernamePasswordAuthenticationToken.class);


    }

}
