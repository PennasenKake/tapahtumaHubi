package com.example.application.views.tapahtumat;

import com.example.application.model.Tapahtuma;
import com.example.application.model.Paikat;
import com.example.application.model.Jarjestaja;
import com.example.application.repository.TapahtumaRepository;
import com.example.application.views.MainView;
import com.example.application.repository.PaikatRepository;
import com.example.application.repository.JarjestajaRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.time.LocalDateTime;
import java.util.List;

@Route(value = "tapahtumat", layout = MainView.class)
@PageTitle("Tapahtumat | TapahtumaHubi")
public class TapahtumaView extends VerticalLayout {

    private final TapahtumaRepository tapahtumaRepository;
    private final PaikatRepository paikatRepository;
    private final JarjestajaRepository jarjestajaRepository;
    private Grid<Tapahtuma> grid;
    private Dialog dialog;
    private Binder<Tapahtuma> binder;

    public TapahtumaView(TapahtumaRepository tapahtumaRepository, PaikatRepository paikatRepository, JarjestajaRepository jarjestajaRepository) {
        this.tapahtumaRepository = tapahtumaRepository;
        this.paikatRepository = paikatRepository;
        this.jarjestajaRepository = jarjestajaRepository;

        setSizeFull();
        addClassName("gridwith-filters-view");

        // Header
        add(new H2("Tapahtumat"));

        // Grid
        grid = new Grid<>(Tapahtuma.class);
        grid.setColumns("nimi", "aika", "osoite", "postinumero", "postitoimipaikka", "puhelinnumero", "kuvaus");
        grid.addColumn(tapahtuma -> tapahtuma.getPaikka() != null ? tapahtuma.getPaikka().getNimi() : "").setHeader("Paikka");
        grid.addColumn(tapahtuma -> tapahtuma.getJarjestaja() != null ? tapahtuma.getJarjestaja().getNimi() : "").setHeader("Järjestäjä");
        grid.addComponentColumn(tapahtuma -> {
            Button editButton = new Button("Muokkaa", e -> openEditor(tapahtuma));
            editButton.addClassName("button-primary");
            Button deleteButton = new Button("Poista", e -> deleteTapahtuma(tapahtuma));
            deleteButton.addClassName("button-danger");
            return new HorizontalLayout(editButton, deleteButton);
        }).setHeader("Toiminnot");
        grid.setItems(tapahtumaRepository.findAll());
        add(grid);

        // Add Button
        Button addButton = new Button("Lisää Tapahtuma", e -> openEditor(new Tapahtuma()));
        addButton.addClassName("button-primary");
        add(addButton);

        // Dialog for Add/Edit
        createEditorDialog();
    }

    private void createEditorDialog() {
        dialog = new Dialog();
        dialog.setHeaderTitle("Tapahtuma");

        FormLayout formLayout = new FormLayout();
        TextField nimiField = new TextField("Nimi");
        DatePicker aikaField = new DatePicker("Aika");
        TextField osoiteField = new TextField("Osoite");
        TextField postinumeroField = new TextField("Postinumero");
        TextField postitoimipaikkaField = new TextField("Postitoimipaikka");
        TextField puhelinnumeroField = new TextField("Puhelinnumero");
        TextField kuvausField = new TextField("Kuvaus");
        ComboBox<Paikat> paikkaField = new ComboBox<>("Paikka");
        ComboBox<Jarjestaja> jarjestajaField = new ComboBox<>("Järjestäjä");

        List<Paikat> paikat = paikatRepository.findAll();
        paikkaField.setItems(paikat);
        paikkaField.setItemLabelGenerator(Paikat::getNimi);

        List<Jarjestaja> jarjestajat = jarjestajaRepository.findAll();
        jarjestajaField.setItems(jarjestajat);
        jarjestajaField.setItemLabelGenerator(Jarjestaja::getNimi);

        formLayout.add(nimiField, aikaField, osoiteField, postinumeroField, postitoimipaikkaField, puhelinnumeroField, kuvausField, paikkaField, jarjestajaField);

        binder = new Binder<>(Tapahtuma.class);
        binder.bind(nimiField, Tapahtuma::getNimi, Tapahtuma::setNimi);
        binder.bind(aikaField, tapahtuma -> tapahtuma.getAika() != null ? tapahtuma.getAika().toLocalDate() : null, (tapahtuma, date) -> tapahtuma.setAika(date != null ? date.atStartOfDay() : null));
        binder.bind(osoiteField, Tapahtuma::getOsoite, Tapahtuma::setOsoite);
        binder.bind(postinumeroField, tapahtuma -> String.valueOf(tapahtuma.getPostinumero()), (tapahtuma, value) -> tapahtuma.setPostinumero(Integer.parseInt(value)));
        binder.bind(postitoimipaikkaField, Tapahtuma::getPostitoimipaikka, Tapahtuma::setPostitoimipaikka);
        binder.bind(puhelinnumeroField, Tapahtuma::getPuhelinnumero, Tapahtuma::setPuhelinnumero);
        binder.bind(kuvausField, Tapahtuma::getKuvaus, Tapahtuma::setKuvaus);
        binder.bind(paikkaField, Tapahtuma::getPaikka, Tapahtuma::setPaikka);
        binder.bind(jarjestajaField, Tapahtuma::getJarjestaja, Tapahtuma::setJarjestaja);

        Button saveButton = new Button("Tallenna", e -> saveTapahtuma());
        saveButton.addClassName("button-primary");
        Button cancelButton = new Button("Peruuta", e -> dialog.close());
        dialog.getFooter().add(cancelButton, saveButton);

        dialog.add(formLayout);
    }

    private void openEditor(Tapahtuma tapahtuma) {
        binder.setBean(tapahtuma);
        dialog.open();
    }

    private void saveTapahtuma() {
        if (binder.validate().isOk()) {
            Tapahtuma tapahtuma = binder.getBean();
            tapahtumaRepository.save(tapahtuma);
            grid.setItems(tapahtumaRepository.findAll());
            dialog.close();
        }
    }

    private void deleteTapahtuma(Tapahtuma tapahtuma) {
        tapahtumaRepository.delete(tapahtuma);
        grid.setItems(tapahtumaRepository.findAll());
    }
}