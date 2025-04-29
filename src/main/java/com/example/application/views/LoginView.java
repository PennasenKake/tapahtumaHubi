

package com.example.application.views;

import com.example.application.service.KayttajaService;
import com.example.application.views.forms.KayttajaForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.*;

@Route(value = "kirjaudu", layout = MainView.class)
@PageTitle("Kirjaudu")
@AnonymousAllowed
public class LoginView extends Div {

    private static final KayttajaService KayttajaService = null;
    private final LoginForm loginForm = new LoginForm();

    public LoginView() {
        addClassNames(Display.FLEX, FlexDirection.COLUMN, AlignItems.CENTER, JustifyContent.CENTER,
                Margin.AUTO, MaxWidth.SCREEN_MEDIUM, Width.FULL, Padding.LARGE);

        LoginI18n i18n = LoginI18n.createDefault();
        LoginI18n.Form i18nForm = i18n.getForm();
        i18nForm.setTitle("Kirjaudu sisään");
        i18nForm.setUsername("Käyttäjänimi");
        i18nForm.setPassword("Salasana");
        i18nForm.setSubmit("Kirjaudu sisään");
        i18nForm.setForgotPassword("Unohtuiko salasana?");
        i18n.setForm(i18nForm);

        LoginI18n.ErrorMessage i18nErrorMessage = i18n.getErrorMessage();
        i18nErrorMessage.setTitle("Väärä käyttäjätunnus tai salasana");
        i18nErrorMessage.setMessage("Tarkista että käyttäjätunnus ja salasana ovat oikein ja yritä uudestaan.");
        i18n.setErrorMessage(i18nErrorMessage);

        loginForm.setI18n(i18n);
        loginForm.setAction("login");
        loginForm.addClassName("login-form");

        Button registerButton = new Button("Rekisteröidy", e -> {
            KayttajaForm form = new KayttajaForm(KayttajaService);
            form.open();
        });
        registerButton.addClassName("register-button");

        add(loginForm, registerButton);

        
    }
}