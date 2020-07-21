package com.company.profiles.web.screens;

import com.company.profiles.entity.Profile;
import com.company.profiles.security.LoginProfileCredentials;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.LoadDataBeforeShow;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.cuba.security.auth.LoginPasswordCredentials;
import com.haulmont.cuba.security.global.InternalAuthenticationException;
import com.haulmont.cuba.security.global.LoginException;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.cuba.web.App;
import com.haulmont.cuba.web.app.login.LoginScreen;
import com.haulmont.cuba.web.exception.ExceptionHandlers;
import com.vaadin.server.ErrorEvent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@UiController("loginProfile")
@UiDescriptor("ext-login-screen.xml")
public class ExtLoginScreen extends LoginScreen {

    @Inject
    private CollectionLoader<Profile> profilesDl;

    @Inject
    private Logger log;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        profilesDl.load();
    }

    @Inject
    private LookupField<Profile> profileSelect;

    @Override
    protected void doLogin() {
        String login = loginField.getValue();
        String password = passwordField.getValue() != null ? passwordField.getValue() : "";

        Map<String, Object> params = new HashMap<>(urlRouting.getState().getParams());

        if (StringUtils.isEmpty(login) || StringUtils.isEmpty(password)) {
            notifications.create(Notifications.NotificationType.WARNING)
                    .withCaption(messages.getMainMessage("loginWindow.emptyLoginOrPassword"))
                    .show();
            return;
        }

        try {
            Locale selectedLocale = localesSelect.getValue();
            app.setLocale(selectedLocale);
            Profile profile = profileSelect.getValue();

            doLogin(new LoginProfileCredentials(login, password, selectedLocale, profile, params));

            // locale could be set on the server
            if (connection.getSession() != null) {
                Locale loggedInLocale = connection.getSession().getLocale();
                if (globalConfig.getLocaleSelectVisible()) {
                    app.addCookie(App.COOKIE_LOCALE, loggedInLocale.toLanguageTag());
                }
            }
        } catch (InternalAuthenticationException e) {
            log.error("Internal error during login", e);

            showUnhandledExceptionOnLogin(e);
        } catch (LoginException e) {
            log.info("Login failed: {}", e.toString());

            String message = StringUtils.abbreviate(e.getMessage(), 1000);
            showLoginException(message);
        } catch (Exception e) {
            if (connection.isAuthenticated()) {
                ExceptionHandlers handlers = app.getExceptionHandlers();
                handlers.handle(new ErrorEvent(e));
            } else {
                log.warn("Unable to login", e);

                showUnhandledExceptionOnLogin(e);
            }
        }


    }
}