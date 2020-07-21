package com.company.profiles.web.screens.profile;

import com.haulmont.cuba.gui.screen.*;
import com.company.profiles.entity.Profile;

@UiController("profiles_Profile.browse")
@UiDescriptor("profile-browse.xml")
@LookupComponent("profilesTable")
@LoadDataBeforeShow
public class ProfileBrowse extends StandardLookup<Profile> {
}