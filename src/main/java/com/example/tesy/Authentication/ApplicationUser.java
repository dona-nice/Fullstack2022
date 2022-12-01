package com.example.tesy.Authentication;

import com.example.tesy.people.PeopleEntity;
import com.example.tesy.right.RightEntity;
import com.example.tesy.role.RoleEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class ApplicationUser implements UserDetails {

    private PeopleEntity user;

    public ApplicationUser(PeopleEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<RoleEntity> roles = user.getAssignedRole();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (RoleEntity role : roles){
            Set<RightEntity> rights = role.getAssignedRight().stream().collect(Collectors.toSet());

            for (RightEntity right: rights){

                authorities.add(new SimpleGrantedAuthority(right.getRightName()));
            }
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName().toUpperCase()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPasswd();
    }

    @Override
    public String getUsername() {

        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
