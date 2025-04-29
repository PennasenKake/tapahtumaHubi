package com.example.application.views.paikat;

import com.example.application.model.Paikat;
import com.example.application.service.PaikkaService;
import com.example.application.views.MainView;
import com.example.application.views.forms.PaikkaForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.List;

@PageTitle("Paikat")
@Route(value = "paikat", layout = MainView.class)
@Menu(order = 1, icon = "line-awesome:location-arrow-solid")
public class PaikatView extends VerticalLayout {

    private final PaikkaService paikkaService;
    private Grid<Paikat> grid;
    private TextField nimiFilter = new TextField("Suodata nimellä...");
    private TextField kapasiteettiFilter = new TextField("Suodata kapasiteetilla (väh.)...");

    public PaikatView(PaikkaService paikkaService) {
        this.paikkaService = paikkaService;

        setSizeFull();
        add(new H2("Paikat"));

        // Grid setup
        grid = new Grid<>(Paikat.class);
        grid.setColumns("nimi", "osoite", "kapasiteetti", "postinumero", "postitoimipaikka");
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                PaikkaForm form = new PaikkaForm(paikkaService, (Void v) -> updateGrid());
                form.setPaikka(event.getValue());
                form.open();
            }
        });

        // Filters
        nimiFilter.addValueChangeListener(e -> updateGrid());
        kapasiteettiFilter.addValueChangeListener(e -> updateGrid());

        // Add button
        Button addButton = new Button("Lisää paikka", e -> {
            PaikkaForm form = new PaikkaForm(paikkaService, (Void v) -> updateGrid());
            form.setPaikka(new Paikat());
            form.open();
        });

        // Layout
        add(new HorizontalLayout(addButton, nimiFilter, kapasiteettiFilter), grid);
        updateGrid();
    }

    private void updateGrid() {
        List<Paikat> paikat = paikkaService.findAll();
        if (!nimiFilter.isEmpty()) {
            paikat = paikkaService.findByNimi(nimiFilter.getValue());
        }
        if (!kapasiteettiFilter.isEmpty()) {
            try {
                int kapasiteetti = Integer.parseInt(kapasiteettiFilter.getValue());
                paikat = paikkaService.findByKapasiteettiGreaterThanEqual(kapasiteetti);
            } catch (NumberFormatException e) {
                Notification.show("Kapasiteetin on oltava kelvollinen numero!", 3000, Notification.Position.TOP_CENTER);
            }
        }
        grid.setItems(paikat);
        if (paikat.isEmpty()) {
            Notification.show("Ei tuloksia annetuilla suodattimilla.", 3000, Notification.Position.TOP_CENTER);
        }
    }
}