package org.grails.gwttutorial.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint {
    /**
     * This is the entry point method.
     */
    MainPage page = new MainPage();
    //Sesion s = new Sesion();
    public void onModuleLoad() {

        /*
        if (s.returnSesion()!=""){
            page.loadProdutos(s.returnSesion());
        } */

        RootPanel.get().clear();
        DecoratorPanel panel = new DecoratorPanel();
        panel.getElement().getStyle().setBackgroundColor("#ffffff");

        RootPanel rootPanel = RootPanel.get();

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        rootPanel.add(horizontalPanel, 10, 10);
        horizontalPanel.setSize("470px", "212px");
        rootPanel.add(panel);
        //panel.add(rootPanel);

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setStyleName("paneles");
        horizontalPanel.add(verticalPanel);

        Label lblWelcomeToThe = new Label("Cyber Shop");
        lblWelcomeToThe.setStyleName("gwt-Label-Login");
        verticalPanel.add(lblWelcomeToThe);
        lblWelcomeToThe.setSize("206px", "124px");

        Login login = new Login();
        horizontalPanel.add(login);
    }
}