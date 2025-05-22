package org.example.springsecuritylearning2.videotut.Controller;

import lombok.RequiredArgsConstructor;

import org.example.springsecuritylearning2.videotut.model.Users;

import org.example.springsecuritylearning2.videotut.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class ProjectController {
    private final UserService userService;




   @PostMapping
   public String  upload(@RequestBody Users user){
       BCryptPasswordEncoder encoder =new BCryptPasswordEncoder(12);
       user.setPassword(encoder.encode(user.getPassword()));
       userService.post(user);
       return "User logged in Db";



   }

   @PostMapping("/post")
    public String verify(@RequestBody Users user){
       return userService.verify(user);

   }




}
