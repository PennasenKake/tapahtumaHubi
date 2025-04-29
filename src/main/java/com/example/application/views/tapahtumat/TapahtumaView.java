package com.example.application.views.tapahtumat;

import com.example.application.model.Tapahtuma;
import com.example.application.model.Paikat;
import com.example.application.model.Jarjestaja;
import com.example.application.service.TapahtumaService;
import com.example.application.service.PaikkaService;
import com.example.application.service.JarjestajaService;
import com.example.application.views.MainView;
import com.example.application.views.forms.TapahtumaForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Route(value = "tapahtumat", layout = MainView.class)
@PageTitle("Tapahtumat | TapahtumaHubi")
public class TapahtumaView extends VerticalLayout {

    private final TapahtumaService tapahtumaService;
    private final PaikkaService paikkaService;
    private final JarjestajaService jarjestajaService;
    private Grid<Tapahtuma> grid;
    private TextField nimiFilter = new TextField("Suodata nimellä...");
    private DateTimePicker aikaFilter = new DateTimePicker("Suodata ajalta...");
    private ComboBox<Long> paikkaFilter = new ComboBox<>("Suodata paikalla...");
    private ComboBox<Long> jarjestajaFilter = new ComboBox<>("Suodata järjestäjällä...");
    private TextField postinumeroFilter = new TextField("Suodata postinumerolla...");

    public TapahtumaView(TapahtumaService tapahtumaService, PaikkaService paikkaService, JarjestajaService jarjestajaService) {
        this.tapahtumaService = tapahtumaService;
        this.paikkaService = paikkaService;
        this.jarjestajaService = jarjestajaService;

        addClassName("tapahtuma-view");
        setSizeFull();
        add(new H2("Tapahtumat"));

        // Grid setup
        grid = new Grid<>(Tapahtuma.class);
        grid.addClassName("tapahtuma-grid");
        grid.setColumns("nimi", "aika", "puhelinnumero", "kuvaus");
        grid.addColumn(tapahtuma -> tapahtuma.getPaikka() != null ? tapahtuma.getPaikka().getNimi() : "").setHeader("Paikka");
        grid.addColumn(tapahtuma -> tapahtuma.getJarjestaja() != null ? tapahtuma.getJarjestaja().getNimi() : "").setHeader("Järjestäjä");
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                try {
                    TapahtumaForm form = new TapahtumaForm(tapahtumaService, paikkaService, jarjestajaService, (Void v) -> updateGrid());
                    form.setTapahtuma(event.getValue());
                    form.open();
                } catch (Exception e) {
                    Notification.show("Tapahtuman lomakkeen avaus epäonnistui: " + e.getMessage(), 3000, Notification.Position.TOP_CENTER);
                }
            }
        });

        // Filters
        paikkaFilter.setItems(paikkaService.findAll().stream().map(Paikat::getId).collect(Collectors.toList()));
        paikkaFilter.setItemLabelGenerator(id -> {
            Paikat paikka = paikkaService.findById(id);
            return paikka != null ? paikka.getNimi() : "Tuntematon paikka";
        });
        jarjestajaFilter.setItems(jarjestajaService.findAll().stream().map(Jarjestaja::getId).collect(Collectors.toList()));
        jarjestajaFilter.setItemLabelGenerator(id -> {
            Jarjestaja jarjestaja = jarjestajaService.findById(id);
            return jarjestaja != null ? jarjestaja.getNimi() : "Tuntematon järjestäjä";
        });

        nimiFilter.addValueChangeListener(e -> updateGrid());
        aikaFilter.addValueChangeListener(e -> updateGrid());
        paikkaFilter.addValueChangeListener(e -> updateGrid());
        jarjestajaFilter.addValueChangeListener(e -> updateGrid());
        postinumeroFilter.addValueChangeListener(e -> updateGrid());

        // Add button
        Button addButton = new Button("Lisää Tapahtuma", e -> {
            try {
                TapahtumaForm form = new TapahtumaForm(tapahtumaService, paikkaService, jarjestajaService, (Void v) -> updateGrid());
                form.setTapahtuma(new Tapahtuma());
                form.open();
            } catch (Exception ex) {
                Notification.show("Tapahtuman lomakkeen avaus epäonnistui: " + ex.getMessage(), 3000, Notification.Position.TOP_CENTER);
            }
        });
        addButton.addClassName("button-primary");

        // Layout
        HorizontalLayout filterLayout = new HorizontalLayout(addButton, nimiFilter, aikaFilter, paikkaFilter, jarjestajaFilter, postinumeroFilter);
        filterLayout.addClassName("filter-container");
        add(filterLayout, grid);
        updateGrid();
    }

    private void updateGrid() {
        List<Tapahtuma> tapahtumat = tapahtumaService.findAll();

        // Yhdistetyt suodattimet
        if (!nimiFilter.isEmpty()) {
            String nimi = nimiFilter.getValue();
            tapahtumat = tapahtumat.stream()
                    .filter(t -> t.getNimi() != null && t.getNimi().toLowerCase().contains(nimi.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (aikaFilter.getValue() != null) {
            LocalDateTime alku = aikaFilter.getValue().toLocalDate().atStartOfDay();
            LocalDateTime loppu = alku.plusDays(1);
            tapahtumat = tapahtumat.stream()
                    .filter(t -> t.getAika() != null && !t.getAika().isBefore(alku) && !t.getAika().isAfter(loppu))
                    .collect(Collectors.toList());
        }
        if (paikkaFilter.getValue() != null) {
            Long paikkaId = paikkaFilter.getValue();
            tapahtumat = tapahtumat.stream()
                    .filter(t -> t.getPaikka() != null && t.getPaikka().getId().equals(paikkaId))
                    .collect(Collectors.toList());
        }
        if (jarjestajaFilter.getValue() != null) {
            Long jarjestajaId = jarjestajaFilter.getValue();
            tapahtumat = tapahtumat.stream()
                    .filter(t -> t.getJarjestaja() != null && t.getJarjestaja().getId().equals(jarjestajaId))
                    .collect(Collectors.toList());
        }
        if (!postinumeroFilter.isEmpty()) {
            try {
                int postinumero = Integer.parseInt(postinumeroFilter.getValue());
                tapahtumat = tapahtumat.stream()
                        .filter(t -> t.getPostinumero() == postinumero)
                        .collect(Collectors.toList());
            } catch (NumberFormatException e) {
                Notification.show("Postinumeron on oltava kelvollinen numero!", 3000, Notification.Position.TOP_CENTER);
                tapahtumat = tapahtumaService.findAll(); // Reset to all items
            }
        }

        grid.setItems(tapahtumat);
        if (tapahtumat.isEmpty()) {
            Notification.show("Ei tuloksia annetuilla suodattimilla.", 3000, Notification.Position.TOP_CENTER);
        }
    }
}