package com.example.application.views.forms;

import com.example.application.model.Paikat;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import elemental.json.JsonArray;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.server.VaadinSession;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PaikkaForm extends Dialog {

    private TextField nimi = new TextField("Paikan nimi");
    private TextField osoite = new TextField("Osoite");
    private IntegerField postinumero = new IntegerField("Postinumero");
    private TextField postitoimipaikka = new TextField("Postitoimipaikka");
    private Button tallenna = new Button("Tallenna");

    public PaikkaForm() {
        setHeaderTitle("Lisää uusi paikka");

        FormLayout form = new FormLayout();
        form.add(nimi, osoite, postinumero, postitoimipaikka);

        tallenna.addClickListener(event -> tallennaPaikka());

        Button button = new Button("Peruuta");

        VerticalLayout layout = new VerticalLayout(form, tallenna, button);
        add(layout);
    }

    
    private void tallennaPaikka() {
        try {
            URL url = new URL("/api/paikat"); // suhteellinen URL toimii Vaadinilla
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            JsonObject paikkaJson = Json.createObject();
            paikkaJson.put("nimi", nimi.getValue());
            paikkaJson.put("paikkaOsoite", osoite.getValue());
            paikkaJson.put("paikkaPostinumero", postinumero.getValue());
            paikkaJson.put("paikkaPostitoimipaikka", postitoimipaikka.getValue());

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = paikkaJson.toJson().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                Notification.show("Paikka tallennettu onnistuneesti!");
                close(); // Sulje lomake
            } else {
                Notification.show("Tallennus epäonnistui. Virhekoodi: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Notification.show("Virhe tallennettaessa paikkaa: " + e.getMessage());
        }
    }


}