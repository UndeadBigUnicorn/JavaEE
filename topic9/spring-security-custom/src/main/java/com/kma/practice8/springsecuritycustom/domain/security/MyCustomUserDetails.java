package com.kma.practice8.springsecuritycustom.domain.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class MyCustomUserDetails extends User {

    private final String customAuthField;
    private final String companyAlias;

    public MyCustomUserDetails(
        final String username,
        final String password,
        final List<? extends GrantedAuthority> authorities,
        final String customAuthField,
        final String companyAlias
    ) {
        super(username, password, authorities);
        this.customAuthField = customAuthField;
        this.companyAlias = companyAlias;
    }
}
