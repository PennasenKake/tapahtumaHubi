package com.example.application.views.jarjestajat;

import com.example.application.model.Jarjestaja;
import com.example.application.service.JarjestajaService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.views.MainView;

@Route(value = "jarjestajat", layout = MainView.class)
@PageTitle("Järjestäjät | TapahtumaHubi")
public class JarjestajaView extends VerticalLayout {

    private final JarjestajaService jarjestajaService;
    private Grid<Jarjestaja> grid;
    private Dialog dialog;
    private Binder<Jarjestaja> binder;

    public JarjestajaView(JarjestajaService jarjestajaService) {
        this.jarjestajaService = jarjestajaService;

        // Header
        add(new H2("Järjestäjät"));

        // Grid setup
        grid = new Grid<>(Jarjestaja.class);
        grid.setColumns("nimi", "yhteystiedot");
        grid.setItems(jarjestajaService.findAll());
        grid.asSingleSelect().addValueChangeListener(event -> openEditDialog(event.getValue()));

        // Add button
        Button addButton = new Button("Lisää järjestäjä", e -> openEditDialog(new Jarjestaja()));
        addButton.getElement().getStyle().set("margin-bottom", "10px");

        // Filter field
        TextField filterField = new TextField();
        filterField.setPlaceholder("Suodata nimellä...");
        filterField.addValueChangeListener(e -> updateGrid(filterField.getValue()));

        // Layout
        add(new HorizontalLayout(addButton, filterField), grid);

        // Initialize dialog and binder
        setupDialog();
    }

    private void setupDialog() {
        dialog = new Dialog();
        binder = new Binder<>(Jarjestaja.class);

        // Form fields
        TextField nimiField = new TextField("Nimi");
        TextField yhteystiedotField = new TextField("Yhteystiedot");

        // Bind fields to binder
        binder.bind(nimiField, Jarjestaja::getNimi, Jarjestaja::setNimi);
        binder.bind(yhteystiedotField, Jarjestaja::getYhteystiedot, Jarjestaja::setYhteystiedot);

        // Buttons
        Button saveButton = new Button("Tallenna", e -> saveJarjestaja());
        Button cancelButton = new Button("Peruuta", e -> dialog.close());
        Button deleteButton = new Button("Poista", e -> deleteJarjestaja());
        deleteButton.getElement().getStyle().set("margin-left", "auto").set("color", "red");

        // Dialog layout
        dialog.add(new VerticalLayout(nimiField, yhteystiedotField, new HorizontalLayout(saveButton, cancelButton, deleteButton)));
    }

    private void openEditDialog(Jarjestaja jarjestaja) {
        binder.setBean(jarjestaja);
        dialog.open();
        if (jarjestaja.getId() == null) {
            dialog.getFooter().getElement().setProperty("innerHTML", "");
        } else {
            dialog.getFooter().getElement().setProperty("innerHTML", "<vaadin-button theme='error'>Poista</vaadin-button>");
        }
    }

    private void saveJarjestaja() {
        if (binder.validate().isOk()) {
            Jarjestaja jarjestaja = binder.getBean();
            jarjestajaService.save(jarjestaja);
            Notification.show("Järjestäjä tallennettu!");
            dialog.close();
            updateGrid(null);
        }
    }

    private void deleteJarjestaja() {
        Jarjestaja jarjestaja = binder.getBean();
        if (jarjestaja.getId() != null) {
            jarjestajaService.delete(jarjestaja.getId());
            Notification.show("Järjestäjä poistettu!");
            dialog.close();
            updateGrid(null);
        }
    }

    private void updateGrid(String filter) {
        if (filter == null || filter.isEmpty()) {
            grid.setItems(jarjestajaService.findAll());
        } else {
            grid.setItems(jarjestajaService.findAll().stream()
                    .filter(j -> j.getNimi().toLowerCase().contains(filter.toLowerCase()))
                    .toList());
        }
    }
}