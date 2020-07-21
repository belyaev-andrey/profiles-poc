package com.company.profiles.core.role;

import com.company.profiles.entity.Profile;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.*;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;
import com.haulmont.cuba.security.role.SpecificPermissionsContainer;

@Role(name = ProfileLoginRole.NAME)
public class ProfileLoginRole extends AnnotatedRoleDefinition {
    public final static String NAME = "ProfileLogin";

    @ScreenAccess(screenIds = {"login", "loginWindow"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = Profile.class, operations = EntityOp.READ)
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = Profile.class, view = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }

    @SpecificAccess(permissions = "cuba.gui.loginToClient")
    @Override
    public SpecificPermissionsContainer specificPermissions() {
        return super.specificPermissions();
    }
}
