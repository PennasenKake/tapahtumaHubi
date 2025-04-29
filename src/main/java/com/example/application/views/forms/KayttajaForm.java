package com.example.application.views.forms;

import com.example.application.model.Kayttaja;
import com.example.application.service.KayttajaService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;

import java.util.Arrays;

public class KayttajaForm extends Dialog {

    private final KayttajaService kayttajaService;
    private Binder<Kayttaja> binder = new Binder<>(Kayttaja.class);
    private Kayttaja kayttaja;

    public KayttajaForm(KayttajaService kayttajaService) {
        this.kayttajaService = kayttajaService;
        this.kayttaja = new Kayttaja();

        setHeaderTitle("Rekisteröidy");

        FormLayout formLayout = new FormLayout();
        TextField nimiField = new TextField("Nimi");
        EmailField emailField = new EmailField("Sähköposti");
        PasswordField salasanaField = new PasswordField("Salasana");
        ComboBox<String> rooliField = new ComboBox<>("Rooli");
        TextField osoiteField = new TextField("Osoite");
        IntegerField postinumeroField = new IntegerField("Postinumero");
        TextField postitoimipaikkaField = new TextField("Postitoimipaikka");
        TextField puhelinnumeroField = new TextField("Puhelinnumero");

        rooliField.setItems(Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
        rooliField.setValue("ROLE_USER");

        binder.forField(nimiField)
                .asRequired("Nimi on pakollinen")
                .bind(Kayttaja::getNimi, Kayttaja::setNimi);
        binder.forField(emailField)
                .asRequired("Sähköposti on pakollinen")
                .withValidator(email -> email.contains("@"), "Syötä kelvollinen sähköposti")
                .bind(Kayttaja::getEmail, Kayttaja::setEmail);
        binder.forField(salasanaField)
                .asRequired("Salasana on pakollinen")
                .withValidator(s -> s.length() >= 6, "Salasanan on oltava vähintään 6 merkkiä")
                .bind(Kayttaja::getSalasana, Kayttaja::setSalasana);
        binder.forField(rooliField)
                .asRequired("Rooli on pakollinen")
                .bind(Kayttaja::getRooli, Kayttaja::setRooli);
        binder.bind(osoiteField, Kayttaja::getOsoite, Kayttaja::setOsoite);
        binder.forField(postinumeroField)
                .asRequired("Postinumero on pakollinen")
                .bind(Kayttaja::getPostinumero, Kayttaja::setPostinumero);
        binder.bind(postitoimipaikkaField, Kayttaja::getPostitoimipaikka, Kayttaja::setPostitoimipaikka);
        binder.bind(puhelinnumeroField, Kayttaja::getPuhelinnumero, Kayttaja::setPuhelinnumero);

        Button saveButton = new Button("Rekisteröidy", e -> saveKayttaja());
        Button cancelButton = new Button("Peruuta", e -> close());

        formLayout.add(nimiField, emailField, salasanaField, rooliField, osoiteField, postinumeroField,
                postitoimipaikkaField, puhelinnumeroField, saveButton, cancelButton);
        add(formLayout);

        binder.setBean(kayttaja);
    }

/*************  ✨ Windsurf Command 🌟  *************/
    /**
     * Save the user object to the database.
     */
    private void saveKayttaja() {
        try {
            binder.writeBean(kayttaja);
            kayttajaService.save(kayttaja);
            close();
        } catch (ValidationException e) {
            // Validation errors are handled by the binder
        }

    }
}