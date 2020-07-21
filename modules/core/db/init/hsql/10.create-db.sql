-- begin PROFILES_FUNDING_APPLICATION
create table PROFILES_FUNDING_APPLICATION (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    USER_ID varchar(36) not null,
    APPLICATION_DATE timestamp not null,
    APPROVED boolean,
    --
    primary key (ID)
)^
-- end PROFILES_FUNDING_APPLICATION
-- begin PROFILES_PROFILE
create table PROFILES_PROFILE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    USER_ID varchar(36),
    --
    primary key (ID)
)^
-- end PROFILES_PROFILE
-- begin PROFILES_PROFILE_ROLE_LINK
create table PROFILES_PROFILE_ROLE_LINK (
    PROFILE_ID varchar(36) not null,
    ROLE_ID varchar(36) not null,
    primary key (PROFILE_ID, ROLE_ID)
)^
-- end PROFILES_PROFILE_ROLE_LINK
