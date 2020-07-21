package com.company.profiles.web.screens.fundingapplication;

import com.company.profiles.entity.Profile;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.screen.*;
import com.company.profiles.entity.FundingApplication;
import com.haulmont.cuba.security.entity.Role;
import com.haulmont.cuba.security.global.UserSession;

import javax.inject.Inject;
import java.util.stream.Collectors;

@UiController("profiles_FundingApplication.browse")
@UiDescriptor("funding-application-browse.xml")
@LookupComponent("fundingApplicationsTable")
@LoadDataBeforeShow
public class FundingApplicationBrowse extends StandardLookup<FundingApplication> {
    @Inject
    private Notifications notifications;
    @Inject
    private UserSession userSession;

    @Subscribe("profileBtn")
    public void onProfileBtnClick(Button.ClickEvent event) {
        Object sessionAttribute = userSession.getAttribute("profile");
        String caption = "Profile not selected!";
        if (sessionAttribute != null) {
            Profile profile = (Profile) sessionAttribute;
            caption = profile.getName()+"\nRoles: "+profile.getRoles().stream().map(Role::getName).collect(Collectors.joining(" "));
        }
        notifications.create().withCaption(caption).show();
    }



}