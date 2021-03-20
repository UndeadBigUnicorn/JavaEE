package com.undeadbigunicorn.demo.config;
import com.undeadbigunicorn.demo.domain.entities.UserEntity;
import com.undeadbigunicorn.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Authenticate a user from the database
 */
@Component("userDetailsService")
public class UserModelDetailsService implements UserDetailsService {

    private final UserService userService;

    public UserModelDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.findByLogin(username).orElseThrow(() ->
                new UsernameNotFoundException("No user for login: " + username));

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserEntity user) {
        return new HashSet<>(UserService.mapAuthorities(user.getPermissions()));
    }
}