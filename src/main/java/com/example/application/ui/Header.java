package com.example.application.ui;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;

public class Header extends Div {
    public Header(String text) {
        addClassName("header");

        // Lataa taustakuva resurssista
        StreamResource backgroundResource = new StreamResource("tausta.jpg",
                () -> getClass().getResourceAsStream("/META-INF/resources/icons/tausta.jpg"));
        Image image = new Image(backgroundResource, "Taustakuva");
        image.addClassName("header-image");

        // Otsikko
        H1 title = new H1(text);
        title.addClassName("header-title");

        // Lisää komponentit
        add(image, title);
    }
}