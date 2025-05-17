package org.example.springsecuritylearning.springsecurity.Context.secondProject.configuration;

import org.example.springsecuritylearning.springsecurity.Context.secondProject.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {
    private final User user;
    public SecurityUser(User user){
        this.user=user;

    }
    public String getUsername(){
        return user.getName();

    }
    public String getPassword(){
        return user.getPassword();

    }
    public boolean isAccountNoExpired(){
        return false;

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(()-> user.getAuthority());


    }
    @Override
    public boolean isAccountNonExpired(){
        return false;

    }
    @Override
    public boolean isAccountNonLocked(){
        return false;

    }

}
