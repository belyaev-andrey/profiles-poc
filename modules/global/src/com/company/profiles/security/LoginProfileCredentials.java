package com.company.profiles.security;

import com.company.profiles.entity.Profile;
import com.haulmont.cuba.security.auth.LoginPasswordCredentials;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LoginProfileCredentials extends LoginPasswordCredentials {

    private final Profile profile;

    public LoginProfileCredentials(String login, String password, Locale locale, Profile profile, Map<String, Object> params) {
        super(login, password, locale, params);
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }

    @Override
    public Map<String, Serializable> getSessionAttributes() {
        if (super.getSessionAttributes() == null) {
            setSessionAttributes(new HashMap<>());
        }
        Map<String, Serializable> attributes = super.getSessionAttributes();
        if (profile != null) {
            attributes.putIfAbsent("profile", profile);
        }
        return attributes;
    }
}
