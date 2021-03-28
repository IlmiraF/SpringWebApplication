package ru.kpfu.itis.group907.farkhutdinova.myspringwebapplication.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
