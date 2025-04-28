package com.example.application.views.koti;

import com.example.application.views.MainView;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIcon;

@CssImport("themes/tapahtumahubi/custom.css")
@PageTitle("Koti")
@Route(value = "", layout = MainView.class)
@Menu(order = 0, icon = "line-awesome:home-solid", title = "Koti")
public class KotiView extends Composite<VerticalLayout> {

    public KotiView() {
        VerticalLayout layout = getContent();
        layout.setWidth("100%");
        layout.getStyle().set("flex-grow", "1");
        layout.setPadding(true);
        layout.setSpacing(true);
        layout.getStyle().set("box-sizing", "border-box");

        // Welcome message
        H2 welcome = new H2("Tervetuloa Tapahtumahubiin!");
        welcome.getElement().setAttribute("aria-label", "Welcome to the Event Hub");

        // Additional content
        Paragraph intro = new Paragraph("Löydä ja osallistu mielenkiintoisiin tapahtumiin!");
        intro.addClassName("intro");
        Button exploreButton = new Button("Tutustu tapahtumiin", LineAwesomeIcon.CALENDAR.create());
        exploreButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("tapahtumat")));
        
        // Add components to layout
        layout.add(welcome, intro, exploreButton);
    }
}