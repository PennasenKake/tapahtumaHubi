package com.example.application.views.forms;

import com.example.application.model.Jarjestaja;
import com.example.application.model.Paikat;
import com.example.application.service.PaikkaService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;

import java.util.function.Consumer;

public class PaikkaForm extends Dialog {

    private final PaikkaService paikkaService;
    private final Consumer<Void> updateCallback;
    private Binder<Paikat> binder = new Binder<>(Paikat.class);

    public PaikkaForm(PaikkaService paikkaService, Consumer<Void> updateCallback) {
        this.paikkaService = paikkaService;
        this.updateCallback = updateCallback;

        setHeaderTitle("Paikka");

        FormLayout formLayout = new FormLayout();
        TextField nimi = new TextField("Paikan nimi");
        TextField osoite = new TextField("Osoite");
        IntegerField kapasiteetti = new IntegerField("Kapasiteetti");
        IntegerField postinumero = new IntegerField("Postinumero");
        TextField postitoimipaikka = new TextField("Postitoimipaikka");

        binder.forField(nimi)
                .asRequired("Nimi on pakollinen")
                .bind(Paikat::getNimi, Paikat::setNimi);
        binder.bind(osoite, Paikat::getOsoite, Paikat::setOsoite);
        binder.forField(kapasiteetti)
                .asRequired("Kapasiteetti on pakollinen")
                .withValidator(val -> val != null && val >= 0, "Kapasiteetin on oltava vähintään 0")
                .bind(Paikat::getKapasiteetti, Paikat::setKapasiteetti);
        binder.forField(postinumero)
                .asRequired("Postinumero on pakollinen")
                .withValidator(val -> val != null && val >= 0, "Postinumeron on oltava kelvollinen numero")
                .bind(Paikat::getPostinumero, Paikat::setPostinumero);
        binder.bind(postitoimipaikka, Paikat::getPostitoimipaikka, Paikat::setPostitoimipaikka);

        Button saveButton = new Button("Tallenna", e -> savePaikka());
        Button cancelButton = new Button("Peruuta", e -> close());
        Button deleteButton = new Button("Poista", e -> deletePaikka());
        deleteButton.getElement().getStyle().set("margin-left", "auto").set("color", "red");

        formLayout.add(nimi, osoite, kapasiteetti, postinumero, postitoimipaikka, saveButton, cancelButton, deleteButton);
        add(formLayout);
    }

    public void setPaikka(Paikat paikka) {
        binder.setBean(paikka);
    }

    private void savePaikka() {
        if (binder.validate().isOk()) {
            Paikat paikka = binder.getBean();
            paikkaService.save(paikka);
            Notification.show("Paikka tallennettu!");
            updateCallback.accept(null);
            close();
        }
    }

    private void deletePaikka() {
        Paikat paikka = binder.getBean();
        if (paikka.getId() != null) {
            try {
                paikkaService.delete(paikka.getId());
                Notification.show("Paikka poistettu!");
                updateCallback.accept(null);
                close();
            } catch (Exception e) {
                Notification.show("Paikan poistaminen epäonnistui: " + e.getMessage(), 3000, Notification.Position.TOP_CENTER);
            }
        }
    }
}