package com.example.application.ui;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility.FontWeight;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;

public class Footer extends Div {
    public Footer(String text) {
        addClassName("footer");
        setWidthFull();
        getStyle().set("padding", "10px");

        
        // Luo HorizontalLayout footerin sisällölle
        HorizontalLayout footerLayout = new HorizontalLayout();
        footerLayout.setWidthFull();
        footerLayout.setJustifyContentMode(com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode.CENTER);
        // Tekijänoikeusteksti
        Div copyrightText = new Div();
        copyrightText.setText(text);

        // Yhteystietolinkki
        Anchor contactLink = new Anchor("mailto:info@tapahtumahubi.fi", "Ota yhteyttä");
        contactLink.addClassNames(TextColor.PRIMARY, FontWeight.MEDIUM);

        // Lisää elementit HorizontalLayoutiin
        footerLayout.add(copyrightText, contactLink);

        // Lisää HorizontalLayout Footer-komponenttiin
        add(footerLayout);
    }
}