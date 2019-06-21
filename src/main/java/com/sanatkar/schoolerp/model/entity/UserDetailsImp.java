package com.sanatkar.schoolerp.model.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Create by ashkan on 2019/06/15
 */
public class UserDetailsImp implements UserDetails {

    private User user;

    public UserDetailsImp(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return user.getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().toString()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isActivated();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isActivated();
    }

    @Override
    public boolean isEnabled() {
        return user.isActivated();
    }
}
