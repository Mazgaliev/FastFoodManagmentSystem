package com.example.fastfoodmanagmentbackend.Model;


import com.example.fastfoodmanagmentbackend.Model.Enum.Role;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.WorkerId;
import com.example.fastfoodmanagmentbackend.Model.base.AbstractEntity;
import com.example.fastfoodmanagmentbackend.Model.base.DomainObjectId;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Getter
public class Person extends AbstractEntity<WorkerId> implements UserDetails {

    private String username;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;


    public Person(String username, String password, Role role) {
        super(DomainObjectId.randomId(WorkerId.class));
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Person() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
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
