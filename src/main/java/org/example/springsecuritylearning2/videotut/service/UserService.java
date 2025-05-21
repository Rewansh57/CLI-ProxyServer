package org.example.springsecuritylearning2.videotut.service;

import lombok.RequiredArgsConstructor;
import org.example.springsecuritylearning2.videotut.model.Users;
import org.example.springsecuritylearning2.videotut.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void post(Users user){
        userRepository.save(user);

    }
    public AuthenticationManager verify(Users user){


    }

}
