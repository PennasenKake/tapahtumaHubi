package com.example.application.views;

import com.example.application.ui.Footer;
import com.example.application.ui.Header;
import com.example.application.ui.Navbar;
import com.example.application.views.koti.KotiView;
import com.example.application.views.jarjestajat.JarjestajaView;
import com.example.application.views.paikat.PaikatView;
import com.example.application.views.tapahtumat.TapahtumaView;
import com.example.application.views.LoginView;
import com.example.application.views.filter.GridwithFiltersView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.component.dependency.CssImport;

@CssImport("themes/tapahtumahubi/custom.css")
public class MainView extends AppLayout {

    private Div content;


    public MainView() {
        // Create header and navbar
        Header header = new Header("Event Hub");
        Navbar navbar = new Navbar();

        // Add navigation links to navbar
        navbar.add(new RouterLink("Home", KotiView.class));
        navbar.add(new RouterLink("Places", PaikatView.class));
        navbar.add(new RouterLink("Organizers", JarjestajaView.class));
        navbar.add(new RouterLink("Events", TapahtumaView.class));
        navbar.add(new RouterLink("Filter", GridwithFiltersView.class));
        navbar.add(new RouterLink("Login", LoginView.class));


        // Add header and navbar to navigation bar
        VerticalLayout navbarWrapper = new VerticalLayout(header, navbar);
        navbarWrapper.setPadding(false);
        navbarWrapper.setSpacing(false);
        addToNavbar(navbarWrapper);

        // Sisältöalue
        content = new Div();
        content.addClassName("content");
        content.addClassName("tyylit");
        content.setSizeFull();
        setContent(content);

        // Footer
        Footer footer = new Footer("© 2025 Event Hub");
        footer.addClassName("footer");
        setFooter(footer);

        // Varmistetaan, että AppLayout venyy
        addClassName("full-height");
    }
    

        private void setFooter(Footer footer) {
        getElement().appendChild(footer.getElement());
    }
}