//package org.grails.gwttutorial.client;
//
///**
// * Created with IntelliJ IDEA.
// * User: Renso&Kenny
// * Date: 11/20/13
// * Time: 11:03 PM
// * To change this template use File | Settings | File Templates.
// */
//import com.google.gwt.user.client.rpc.ServiceDefTarget;
//import com.google.gwt.user.client.ui.*;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.user.client.Window;
////import com.smartgwt.client.types.Alignment;
////import com.smartgwt.client.widgets.layout.HLayout;
//
//
//
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//
//public class Login extends Composite {
//
//    private UsuarioServiceAsync uService;
//    private BookServiceAsync bookService;
//
//
//
//    MainPage page = new MainPage();
//
//    public void Login() {
//
//        final TextBox textBoxUsername;
//        final PasswordTextBox textBoxPassword;
//
//        //final HLayout horizontalCenter = new HLayout();
//        final VerticalPanel DialogBoxContents;
//        HTML message = new HTML();
//        Object user = new Object();
//
//        if (user==null){
//            //horizontalCenter.setAlign(Alignment.CENTER);
//            //Window.alert(""+user);
//            VerticalPanel verticalPanel = new VerticalPanel();
//            verticalPanel.setBorderWidth(2);
//            //verticalPanel.setStyleName("paneles");
//            initWidget(verticalPanel);
//
//            DialogBoxContents = new VerticalPanel();
//            DialogBoxContents.setSize("350px", "50px");
//            verticalPanel.add(DialogBoxContents);
//
//            Label lblLoginToYour = new Label("Login Menu");
//            lblLoginToYour.setStyleName("gwt-Label-Login");
//            verticalPanel.add(lblLoginToYour);
//
//            FlexTable flexTable = new FlexTable();
//            verticalPanel.add(flexTable);
//            flexTable.setWidth("400px");
//
//
//            Label lblUsername = new Label("Username:");
//            lblUsername.setStyleName("gwt-Label-Login");
//            flexTable.setWidget(0, 0, lblUsername);
//            textBoxUsername = new TextBox();
//            flexTable.setWidget(0, 1, textBoxUsername);
//
//
//            Label lblPassword = new Label("Password:");
//            lblPassword.setStyleName("gwt-Label-Login");
//            flexTable.setWidget(1, 0, lblPassword);
//            textBoxPassword = new PasswordTextBox();
//            //textBoxPassword.setDirection(Direction.RTL);
//            flexTable.setWidget(1, 1, textBoxPassword);
//
//
//            CheckBox chckbxRememberMeOn = new CheckBox("Recordarme en esta PC");
//            chckbxRememberMeOn.setStyleName("gwt-Login-CheckBox");
//            flexTable.setWidget(2, 1, chckbxRememberMeOn);
//
//            /*
//                Boton para ir a creacion cuenta
//             */
//            Button crearUserbtn = new Button("Crear cuenta");
//            crearUserbtn.addClickHandler(new ClickHandler() {
//                @Override
//                public void onClick(ClickEvent clickEvent) {
//                    CrearCuenta crearCuenta = new CrearCuenta();
//                    crearCuenta.onModuleLoad();
//                }
//            });
//
//            /*
//                Boton para log in en mi cuenta
//             */
//            Button btnSignIn = new Button("Log in");
//            btnSignIn.addClickHandler(new ClickHandler() {
//                public void onClick(ClickEvent event) {
//                    if (textBoxUsername.getText().length() == 0
//                            || textBoxPassword.getText().length() == 0) {
//                        DialogBoxContents.clear();
//                        message = new HTML("El userName or Password no pueden estar vacios!!!");
//                        message.setStyleName("gwt-Login-CheckBox");
//                        DialogBoxContents.add(message);
//                    }else {
//                        bookService = (BookServiceAsync) GWT.create(BookService.class);
//                        ServiceDefTarget endpoint = (ServiceDefTarget) bookService;
//                        String moduleRelativeUrl = GWT.getModuleBaseURL() + "rpc";
//                        endpoint.setServiceEntryPoint(moduleRelativeUrl);
//
//                        // Call a method on the service!
//                        bookService.user(textBoxUsername.getText(), textBoxPassword.getText(), new AsyncCallback() {
//                            public void onSuccess(Object result) {
//                                // It's always safe to downcast to the known return type.
//                                if (result.equals("{}")){
//                                    DialogBoxContents.clear();
//                                    message = new HTML("UserName or Password incorrectos!!");
//                                    message.setStyleName("gwt-Login-CheckBox");
//                                    DialogBoxContents.add(message);
//                                }else{
//                                    user = result;
//                                    DialogBoxContents.clear();
//                                    //String resultString = (String) result;
//                                    //Window.alert(resultString);
//                                    //panel para la nueva ventana
//                                    page.loadProdutos(result);
//                                }
//
//                            }
//                            public void onFailure(Throwable caught) {
//                            }
//
//                        });
//                    }
//                }
//            });
//            flexTable.setWidget(3, 1, btnSignIn);
//            flexTable.setWidget(3, 2, crearUserbtn);
//            //horizontalCenter.addChild(verticalPanel);
//        }else {
//            Window.alert("loading : "+user);
//            page.loadProdutos(user);
//        }
//
//    }
//
//}
