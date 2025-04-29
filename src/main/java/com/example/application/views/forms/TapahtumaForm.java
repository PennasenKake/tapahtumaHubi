package com.example.application.views.forms;

import com.example.application.model.Tapahtuma;
import com.example.application.model.Paikat;
import com.example.application.model.Jarjestaja;
import com.example.application.service.TapahtumaService;
import com.example.application.service.PaikkaService;
import com.example.application.service.JarjestajaService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class TapahtumaForm extends Dialog {

    private final TapahtumaService tapahtumaService;
    private final PaikkaService paikkaService;
    private final JarjestajaService jarjestajaService;
    private final Consumer<Void> updateCallback;
    private Binder<Tapahtuma> binder = new Binder<>(Tapahtuma.class);

    public TapahtumaForm(TapahtumaService tapahtumaService, PaikkaService paikkaService, JarjestajaService jarjestajaService, Consumer<Void> updateCallback) {
        this.tapahtumaService = tapahtumaService;
        this.paikkaService = paikkaService;
        this.jarjestajaService = jarjestajaService;
        this.updateCallback = updateCallback;

        setHeaderTitle("Tapahtuma");

        
        FormLayout formLayout = new FormLayout();
        TextField nimiField = new TextField("Nimi");
        com.vaadin.flow.component.datetimepicker.DateTimePicker aikaField = new DateTimePicker("Aika");
        TextField puhelinnumeroField = new TextField("Puhelinnumero");
        TextField kuvausField = new TextField("Kuvaus");
        ComboBox<Long> paikkaField = new ComboBox<>("Paikka");
        ComboBox<Long> jarjestajaField = new ComboBox<>("Järjestäjä");

        paikkaField.setItems(paikkaService.findAll().stream().map(Paikat::getId).collect(Collectors.toList()));
        paikkaField.setItemLabelGenerator(id -> paikkaService.findById(id).getNimi());
        jarjestajaField.setItems(jarjestajaService.findAll().stream().map(Jarjestaja::getId).collect(Collectors.toList()));
        jarjestajaField.setItemLabelGenerator(id -> jarjestajaService.findById(id).getNimi());

        binder.forField(nimiField)
                .asRequired("Nimi on pakollinen")
                .bind(Tapahtuma::getNimi, Tapahtuma::setNimi);
        binder.forField(aikaField)
                .asRequired("Aika on pakollinen")
                .bind(Tapahtuma::getAika, Tapahtuma::setAika);
        
        binder.bind(puhelinnumeroField, Tapahtuma::getPuhelinnumero, Tapahtuma::setPuhelinnumero);
        binder.bind(kuvausField, Tapahtuma::getKuvaus, Tapahtuma::setKuvaus);
        binder.bind(paikkaField, tapahtuma -> tapahtuma.getPaikka() != null ? tapahtuma.getPaikka().getId() : null,
                (tapahtuma, id) -> tapahtuma.setPaikka(paikkaService.findById(id)));
        binder.bind(jarjestajaField, tapahtuma -> tapahtuma.getJarjestaja() != null ? tapahtuma.getJarjestaja().getId() : null,
                (tapahtuma, id) -> tapahtuma.setJarjestaja(jarjestajaService.findById(id)));

        Button saveButton = new Button("Tallenna", e -> saveTapahtuma());
        Button cancelButton = new Button("Peruuta", e -> close());
        Button deleteButton = new Button("Poista", e -> deleteTapahtuma());
        deleteButton.getElement().getStyle().set("margin-left", "auto").set("color", "red");

        formLayout.add(nimiField, aikaField, puhelinnumeroField, kuvausField, paikkaField, jarjestajaField, saveButton, cancelButton, deleteButton);
        add(formLayout);
    }

    public void setTapahtuma(Tapahtuma tapahtuma) {
        binder.setBean(tapahtuma);
    }

    private void saveTapahtuma() {
        if (binder.validate().isOk()) {
            Tapahtuma tapahtuma = binder.getBean();
            tapahtumaService.save(tapahtuma);
            Notification.show("Tapahtuma tallennettu!");
            updateCallback.accept(null);
            close();
        }
    }

    
    /**
     * Deletes the current Tapahtuma entity bound to the form.
     * If the entity has an ID, it attempts to delete it from the database using the TapahtumaService.
     * Shows a notification on success or failure.
     */
    private void deleteTapahtuma() {
        // Retrieve the current Tapahtuma entity from the binder.
        Tapahtuma tapahtuma = binder.getBean();
        
        // Check if the Tapahtuma entity has an ID, meaning it's likely not a new entity.
        if (tapahtuma.getId() != null) {
            try {
                // Attempt to delete the Tapahtuma entity from the database using its ID.
                tapahtumaService.delete(tapahtuma.getId());
                
                // Show a notification to inform the user that the Tapahtuma was successfully deleted.
                Notification.show("Tapahtuma poistettu!");
                
                // Trigger the update callback to refresh any dependent views or data.
                updateCallback.accept(null);
                
                // Close the form after deletion.
                close();
            } catch (Exception e) {
                // If an exception occurs during deletion, show a notification with the error message.
                Notification.show("Tapahtuman poistaminen epäonnistui: " + e.getMessage(), 3000, Notification.Position.TOP_CENTER);
            }
        }
    }
}
