package com.example.application.ui;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.component.tabs.Tab;

public class Navbar extends HorizontalLayout {
    
    public Navbar() {
        addClassName("navbar");
        setWidthFull();
        setPadding(true);
        setSpacing(true);

        Tabs tabs = new Tabs();
        tabs.addClassName("navbar-tabs");
        tabs.setFlexGrowForEnclosedTabs(1); // Jakaa tabit tasaisesti
        add(tabs);
    }

    public void add(RouterLink link) {
        Tab tab = new Tab(link);
        ((Tabs) getComponentAt(0)).add(tab);
    }

    public void getStyle(String string, String string2, Alignment end) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStyle'");
    }
}