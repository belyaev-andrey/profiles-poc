# Proof of Concept App for Profiles Switching

This application shows the capability of user profile selection on the login screen. 
When a user logs on, they can select a set of roles - a.k.a. "Profile" that 
will be applied after logon. The application does not apply the roles to the session yet, 
but you are able to select profile and see the list of assigned roles in a notification 
(see `Funding Applications` browser screen, `Profile` button). 

To work with application, you need to:
1. Log in as `admin/admin` user.
2. Assign role `ProfileLogin` to the `anonymous` user. This will allow to fetch profiles list to the login window. 
3. Create a couple of profiles in th `Application -> Profiles` screen
4. Log out and log in again, but select a profile this time.
5. In the `Application -> Funding Applications` screen, press `Profile` button. You should see a notification with roles list for the selected profile.

To make the application work properly, we need to update `com.haulmont.cuba.security.app.role.RolesHelper` 
and `com.haulmont.cuba.security.sys.UserSessionManager` classes that define set of roles for the current user session. 