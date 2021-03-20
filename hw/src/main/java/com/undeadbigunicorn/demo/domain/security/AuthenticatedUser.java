package com.undeadbigunicorn.demo.domain.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class AuthenticatedUser extends User {

    private final String library;

    public AuthenticatedUser(
            final String username,
            final String password,
            final List<? extends GrantedAuthority> authorities,
            final String library
    ) {
        super(username, password, authorities);
        this.library = library;
    }
}