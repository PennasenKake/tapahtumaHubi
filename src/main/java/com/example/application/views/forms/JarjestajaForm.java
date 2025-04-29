package com.example.application.views.forms;

import com.example.application.model.Jarjestaja;
import com.example.application.service.JarjestajaService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;

import java.util.function.Consumer;

public class JarjestajaForm extends Dialog {

    private final JarjestajaService jarjestajaService;
    private final Consumer<Void> updateCallback;
    private Binder<Jarjestaja> binder = new Binder<>(Jarjestaja.class);

    public JarjestajaForm(JarjestajaService jarjestajaService, Consumer<Void> updateCallback) {
        this.jarjestajaService = jarjestajaService;
        this.updateCallback = updateCallback;

        setHeaderTitle("Järjestäjä");

        FormLayout formLayout = new FormLayout();
        TextField nimiField = new TextField("Nimi");
        TextField yhteystiedotField = new TextField("Yhteystiedot");
        TextField tyyppiField = new TextField("Tyyppi");

        binder.forField(nimiField)
                .asRequired("Nimi on pakollinen")
                .bind(Jarjestaja::getNimi, Jarjestaja::setNimi);
        binder.bind(yhteystiedotField, Jarjestaja::getYhteystiedot, Jarjestaja::setYhteystiedot);
        binder.bind(tyyppiField, Jarjestaja::getTyyppi, Jarjestaja::setTyyppi);

        Button saveButton = new Button("Tallenna", e -> saveJarjestaja());
        Button cancelButton = new Button("Peruuta", e -> close());
        Button deleteButton = new Button("Poista", e -> deleteJarjestaja());
        deleteButton.getElement().getStyle().set("margin-left", "auto").set("color", "red");

        formLayout.add(nimiField, yhteystiedotField, tyyppiField, saveButton, cancelButton, deleteButton);
        add(formLayout);
    }

    public void setJarjestaja(Jarjestaja jarjestaja) {
        binder.setBean(jarjestaja);
    }

    private void saveJarjestaja() {
        if (binder.validate().isOk()) {
            Jarjestaja jarjestaja = binder.getBean();
            jarjestajaService.save(jarjestaja);
            Notification.show("Järjestäjä tallennettu!");
            updateCallback.accept(null);
            close();
        }
    }

    private void deleteJarjestaja() {
        Jarjestaja jarjestaja = binder.getBean();
        if (jarjestaja.getId() != null) {
            try {
                jarjestajaService.delete(jarjestaja.getId());
                Notification.show("Järjestäjä poistettu!");
                updateCallback.accept(null);
                close();
            } catch (Exception e) {
                Notification.show("Järjestäjän poistaminen epäonnistui: " + e.getMessage(), 3000, Notification.Position.TOP_CENTER);
            }
        }
    }
}