package com.example.application.views.tapahtumat;

import com.example.application.views.MainView;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "tapahtumat", layout = MainView.class)
public class TapahtumaView extends VerticalLayout {

    public TapahtumaView() {
        add(new H2("Tapahtumat"));
        // Lisää tapahtumien hallintaan liittyviä UI-komponentteja
    }
}