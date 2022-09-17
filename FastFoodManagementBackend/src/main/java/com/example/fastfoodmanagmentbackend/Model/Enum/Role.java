package com.example.fastfoodmanagmentbackend.Model.Enum;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    WORKER,
    OWNER;

    @Override
    public String getAuthority() {
        return name();
    }
}
