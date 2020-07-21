package com.company.profiles.web.screens.profile;

import com.haulmont.cuba.gui.screen.*;
import com.company.profiles.entity.Profile;

@UiController("profiles_Profile.edit")
@UiDescriptor("profile-edit.xml")
@EditedEntityContainer("profileDc")
@LoadDataBeforeShow
public class ProfileEdit extends StandardEditor<Profile> {
}