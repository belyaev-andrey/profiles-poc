package com.company.profiles.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.security.entity.Role;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "PROFILES_PROFILE")
@Entity(name = "profiles_Profile")
@NamePattern("%s|name")
public class Profile extends StandardEntity {
    private static final long serialVersionUID = -1367765924351274385L;

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Lookup(type = LookupType.DROPDOWN, actions = "lookup")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @JoinTable(name = "PROFILES_PROFILE_ROLE_LINK",
            joinColumns = @JoinColumn(name = "PROFILE_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    @ManyToMany
    private List<Role> roles;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}