package com.example.application.views.jarjestajat;

import java.util.List;

import com.example.application.model.Jarjestaja;
import com.example.application.service.JarjestajaService;
import com.example.application.views.MainView;
import com.example.application.views.forms.JarjestajaForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "jarjestajat", layout = MainView.class)
@PageTitle("Järjestäjät | TapahtumaHubi")
public class JarjestajaView extends VerticalLayout {

    private final JarjestajaService jarjestajaService;
    private Grid<Jarjestaja> grid;
    private TextField nimiFilter = new TextField("Suodata nimellä...");
    private TextField tyyppiFilter = new TextField("Suodata tyypillä...");

    public JarjestajaView(JarjestajaService jarjestajaService) {
        this.jarjestajaService = jarjestajaService;

        setSizeFull();
        add(new H2("Järjestäjät"));

        // Grid setup
        grid = new Grid<>(Jarjestaja.class);
        grid.setColumns("nimi", "yhteystiedot", "tyyppi");
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                JarjestajaForm form = new JarjestajaForm(jarjestajaService, (Void v) -> updateGrid());
                form.setJarjestaja(event.getValue());
                form.open();
            }
        });

        // Filters
        nimiFilter.addValueChangeListener(e -> updateGrid());
        tyyppiFilter.addValueChangeListener(e -> updateGrid());

        // Add button
        Button addButton = new Button("Lisää järjestäjä", e -> {
            JarjestajaForm form = new JarjestajaForm(jarjestajaService, (Void v) -> updateGrid());
            form.setJarjestaja(new Jarjestaja());
            form.open();
        });
        addButton.getElement().getStyle().set("margin-bottom", "10px");

        // Layout
        add(new HorizontalLayout(addButton, nimiFilter, tyyppiFilter), grid);
        updateGrid();
    }

    private void updateGrid() {
        List<Jarjestaja> jarjestajat = jarjestajaService.findAll();
        if (!nimiFilter.isEmpty()) {
            jarjestajat = jarjestajaService.findByNimi(nimiFilter.getValue());
        }
        if (!tyyppiFilter.isEmpty()) {
            jarjestajat = jarjestajaService.findByTyyppi(tyyppiFilter.getValue());
        }
        grid.setItems(jarjestajat);
        if (jarjestajat.isEmpty()) {
            Notification.show("Ei tuloksia annetuilla suodattimilla.", 3000, Notification.Position.TOP_CENTER);
        }
    }
}