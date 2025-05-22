package org.example.springsecuritylearning2.videotut.service;

import lombok.RequiredArgsConstructor;
import org.example.springsecuritylearning2.videotut.model.Users;
import org.example.springsecuritylearning2.videotut.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthenticationManager authManager;

    private final UserRepository userRepository;

    public void post(Users user){
        userRepository.save(user);

    }
    public String verify(Users user){
         Authentication authentication =new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
authManager.authenticate(authentication);

if (authentication.isAuthenticated()){
    return "Success";


}
return "Failed";






    }

}
