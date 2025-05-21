package org.example.springsecuritylearning2.videotut.service;

import lombok.RequiredArgsConstructor;
import org.example.springsecuritylearning2.videotut.model.Users;
import org.example.springsecuritylearning2.videotut.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  Users user=userRepository.findByUsername((username));

  if (user==null){
      throw new UsernameNotFoundException(username);

  }
  return new UserPrincipal(user);





    }

}
