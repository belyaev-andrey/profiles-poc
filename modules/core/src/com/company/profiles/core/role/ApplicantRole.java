package com.company.profiles.core.role;

import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.Role;

@Role(name = ApplicantRole.NAME)
public class ApplicantRole extends AnnotatedRoleDefinition {
    public final static String NAME = "Applicant";
}
