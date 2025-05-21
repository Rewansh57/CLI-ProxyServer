package org.example.springsecuritylearning2.videotut.service;

import org.example.springsecuritylearning2.videotut.model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Collections;


public class UserPrincipal implements UserDetails {
    private  Users user;

   public UserPrincipal(Users user){
       this.user=user;



   }
   public Collection<? extends GrantedAuthority> getAuthorities(){
return Collections.singleton(new SimpleGrantedAuthority("USER"));

   }

   @Override
   public String getPassword(){
       return user.getPassword();

   }
   @Override
    public String getUsername(){
       return user.getUsername();

   }

   public boolean isAccountNotExpired(){
       return true;

   }


   public  boolean isAccountNonLocked() {
        return true;
    }

     public boolean isCredentialsNonExpired() {
        return true;
    }

     public boolean isEnabled() {
        return true;
    }

}
