package com.company.profiles.listeners;

import com.company.profiles.entity.Profile;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.security.auth.events.UserLoggedInEvent;
import com.haulmont.cuba.security.entity.Role;
import com.haulmont.cuba.security.global.UserSession;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Component("profiles_AuthenticationEventListener")
public class AuthenticationEventListener {

    @Inject
    private DataManager dataManager;

    @EventListener
    public void afterLogin(UserLoggedInEvent event) {

        UserSession userSession = event.getUserSession();
        Profile attribute = userSession.getAttribute("profile");
        if (attribute != null) {
            UUID profileId = attribute.getId();
            Profile profile = dataManager.load(Profile.class).id(profileId).view("profile-with-roles-view").one();
            userSession.setAttribute("profile", profile);
        }
    }

}