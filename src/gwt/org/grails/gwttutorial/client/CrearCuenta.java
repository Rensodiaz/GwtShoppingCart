//package org.grails.gwttutorial.client;
//
//import com.google.gwt.core.client.EntryPoint;
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.rpc.ServiceDefTarget;
//import com.google.gwt.user.client.ui.*;
//
///**
// * Created with IntelliJ IDEA.
// * User: Renso&Kenny
// * Date: 11/23/13
// * Time: 1:30 AM
// * To change this template use File | Settings | File Templates.
// */
//public class CrearCuenta implements EntryPoint {
//
//    TextBox userName;
//    TextBox nombre;
//    TextBox apellido;
//    TextBox password;
//    HTML message;
//    HorizontalPanel DialogBoxContents;
//    Application ap = new Application();
//
//    private BookServiceAsync bookService;
//    public void CrearCuenta() {
//
//        RootPanel.get().clear();
//        FlexTable flexTable = new FlexTable();
//        FlexTable flexBotones = new FlexTable();
//
//
//        VerticalPanel verticalPanel = new VerticalPanel();
//        verticalPanel.setBorderWidth(2);
//        verticalPanel.setStyleName("paneles");
//        verticalPanel.setSize("700px", "400px");
//
//        DialogBoxContents = new HorizontalPanel();
//        DialogBoxContents.setSize("350px", "50px");
//        verticalPanel.add(DialogBoxContents);
//
//
//        Label lblCrear = new Label("Menu Crear Usuario");
//        lblCrear.setStyleName("gwt-Label-Login");
//        verticalPanel.add(lblCrear);
//
//        Label lblUsername = new Label("Username");
//        lblUsername.setStyleName("gwt-Label-Login");
//        userName = new TextBox();
//        flexTable.setWidget(0,0,lblUsername);
//        flexTable.setWidget(0,1,userName);
//
//
//        Label lblNombre = new Label("Nombre");
//        lblNombre.setStyleName("gwt-Label-Login");
//        nombre = new TextBox();
//        flexTable.setWidget(1,0,lblNombre);
//        flexTable.setWidget(1,1, nombre);
//
//
//        Label lblApellido = new Label("Apellido");
//        lblApellido.setStyleName("gwt-Label-Login");
//        apellido = new TextBox();
//        flexTable.setWidget(2,0,lblApellido);
//        flexTable.setWidget(2,1,apellido);
//
//        Label lblPassword = new Label("Password");
//        lblPassword.setStyleName("gwt-Label-Login");
//        password = new TextBox();
//        flexTable.setWidget(3,0,lblPassword);
//        flexTable.setWidget(3,1,password);
//
//        Button crear = new Button("Crear", new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent clickEvent) {
//                if(userName.getText().length()==0 || password.getText().length()==0 || nombre.getText().length()==0 ||apellido.getText().length()==0){
//                    DialogBoxContents.clear();
//                    message = new HTML("Debe llenar todos los campos!!");
//                    message.setStyleName("gwt-Login-CheckBox");
//                    DialogBoxContents.add(message);
//
//                }else{
//                    bookService = (BookServiceAsync) GWT.create(BookService.class);
//                    ServiceDefTarget endpoint = (ServiceDefTarget) bookService;
//                    String moduleRelativeUrl = GWT.getModuleBaseURL() + "rpc";
//                    endpoint.setServiceEntryPoint(moduleRelativeUrl);
//
//                    // Call a method on the service!
//                    bookService.createUser(userName.getText(), password.getText(),nombre.getText(),apellido.getText(), new AsyncCallback() {
//                        public void onSuccess(Object result) {
//                            // It's always safe to downcast to the known return type.
//                            if (result.equals("{}")){
//                                DialogBoxContents.clear();
//                                message = new HTML("Error al procesar el formulario");
//                                message.setStyleName("gwt-Login-CheckBox");
//                                DialogBoxContents.add(message);
//                            }else{
//                                DialogBoxContents.clear();
//                                //String resultString = (String) result;
//                                //Window.alert(resultString);
//                                //panel para la nueva ventana
//
//                            }
//
//                        }
//                        public void onFailure(Throwable caught) {
//                        }
//
//                    });
//                }
//            }
//        });
//
//        Button cancelar = new Button("Cancel", new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent clickEvent) {
//                Application p = new Application();
//                p.onModuleLoad();
//            }
//        });
//
//        //Adding Button to a flexBotones
//        flexBotones.setWidget(0,0,crear);
//        flexBotones.setWidget(0,1,cancelar);
//
//
//        /*
//            Adding elements to the panel
//         */
//        verticalPanel.add(DialogBoxContents);
//        verticalPanel.add(flexTable);
//        verticalPanel.add(flexBotones);
//        RootPanel.get().add(verticalPanel);
//    }
//}
