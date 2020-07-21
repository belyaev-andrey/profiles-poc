package com.company.profiles.web.screens.fundingapplication;

import com.haulmont.cuba.gui.screen.*;
import com.company.profiles.entity.FundingApplication;

@UiController("profiles_FundingApplication.edit")
@UiDescriptor("funding-application-edit.xml")
@EditedEntityContainer("fundingApplicationDc")
@LoadDataBeforeShow
public class FundingApplicationEdit extends StandardEditor<FundingApplication> {
}