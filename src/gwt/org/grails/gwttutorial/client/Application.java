package org.grails.gwttutorial.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint {
    /**
     * This is the entry point method.
     */
   // private UsuarioServiceAsync uService;
    private BookServiceAsync bookService;

    /*
        Login Campos =====================================================
     */
    Object user = new Object();
    TextBox textBoxUsername;
    PasswordTextBox textBoxPassword;
    /*
       Fin Login Campos =====================================================
     */

    /*
        load prodcutos Campos ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
    private FlexTable t = new FlexTable();

    HorizontalPanel topLabel;
    VerticalPanel DialogBoxContents;
    HTML messageP;
    HTML topMensajeP;
    private TextBox cantidadAgregar;
    /*
       Fin load productos  Campos =====================================================
     */

    /*
        Crear cuenta Campos ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */


    private FlexTable flexCampos = new FlexTable();
    private FlexTable flexBotones = new FlexTable();
    HorizontalPanel DialogBoxContentsCrear;
    TextBox userName;
    TextBox nombre;
    TextBox apellido;
    TextBox password;
    HTML messageC;
    /*
       FIN Crear cuenta Campos ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */

    /*
        variables carrito
     */
    HashMap<String,String> pCarrito = new HashMap<String, String>();

    //PANEL PRINCIPAL PARA TRABAJAR
    RootPanel rootPanel = RootPanel.get();
    VerticalPanel verticalPanelC = new VerticalPanel();
    String uID;
    String pIdReady;
    Boolean checkOut = false;
    List<String[]> verArticulos = new ArrayList<String[]>();

    //INICIALIZACION DE LA APPLICACION
    public void onModuleLoad() {

        verticalPanelC.setBorderWidth(2);
        verticalPanelC.setStyleName("paneles");
        verticalPanelC.setSize("700px", "400px");
        rootPanel.add(verticalPanelC);
        Login();
    }

    public void Login() {

        verticalPanelC.clear();
        verticalPanelC.setSize("300px","150px");
        //final HLayout horizontalCenter = new HLayout();
        final VerticalPanel DialogBoxContents;
        final HTML message1 = new HTML("El userName or Password no pueden estar vacios!!!");
        final HTML message2 = new HTML("UserName or Password incorrectos!!");

        //horizontalCenter.setAlign(Alignment.CENTER);
        //Window.alert(""+user);
        //VerticalPanel verticalPanel = new VerticalPanel();
        //verticalPanel.setBorderWidth(2);
        //verticalPanel.setStyleName("paneles");

        DialogBoxContents = new VerticalPanel();
        DialogBoxContents.setSize("350px", "50px");
        verticalPanelC.add(DialogBoxContents);

        Label lblLoginToYour = new Label("Login Menu");
        lblLoginToYour.setStyleName("gwt-Label-Login");
        verticalPanelC.add(lblLoginToYour);

        FlexTable flexTableL = new FlexTable();
        flexTableL.setWidth("400px");


        Label lblUsername = new Label("Username:");
        lblUsername.setStyleName("gwt-Label-Login");
        flexTableL.setWidget(0, 0, lblUsername);
        textBoxUsername = new TextBox();
        flexTableL.setWidget(0, 1, textBoxUsername);


        Label lblPassword = new Label("Password:");
        lblPassword.setStyleName("gwt-Label-Login");
        flexTableL.setWidget(1, 0, lblPassword);
        textBoxPassword = new PasswordTextBox();
        //textBoxPassword.setDirection(Direction.RTL);
        flexTableL.setWidget(1, 1, textBoxPassword);


        CheckBox chckbxRememberMeOn = new CheckBox("Recordarme en esta PC");
        chckbxRememberMeOn.setStyleName("gwt-Login-CheckBox");
        flexTableL.setWidget(2, 1, chckbxRememberMeOn);

        /*
            Boton para ir a creacion cuenta
         */
        Button crearUserbtn = new Button("Crear cuenta");
        crearUserbtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                crearCuenta();
            }
        });

        /*
            Boton para log in en mi cuenta
         */
        Button btnSignIn = new Button("Log in");
        btnSignIn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (textBoxUsername.getText().length() == 0
                        || textBoxPassword.getText().length() == 0) {
                    DialogBoxContents.clear();
                    message1.setStyleName("gwt-Login-CheckBox");
                    DialogBoxContents.add(message1);
                }else {
                    bookService = (BookServiceAsync) GWT.create(BookService.class);
                    ServiceDefTarget endpoint = (ServiceDefTarget) bookService;
                    String moduleRelativeUrl = GWT.getModuleBaseURL() + "rpc";
                    endpoint.setServiceEntryPoint(moduleRelativeUrl);

                    // Call a method on the service!
                    bookService.user(textBoxUsername.getText(), textBoxPassword.getText(), new AsyncCallback() {
                        public void onSuccess(Object result) {
                            // It's always safe to downcast to the known return type.
                            if (result.equals("{}")){
                                DialogBoxContents.clear();
                                message2.setStyleName("gwt-Login-CheckBox");
                                DialogBoxContents.add(message2);
                            }else{
                                user = result;
                                DialogBoxContents.clear();

                                JSONValue userArray = JSONParser.parseStrict((String)user);

                                //id usuario
                                uID = userArray.isObject().get("id").toString();
                                loadProdutos();
                                //String resultString = (String) result;
                                //Window.alert(resultString);
                                //panel para la nueva ventana
                            }

                        }
                        public void onFailure(Throwable caught) {
                        }

                    });
                }
            }
        });
        flexTableL.setWidget(3, 1, btnSignIn);
        flexTableL.setWidget(3, 2, crearUserbtn);
        verticalPanelC.add(flexTableL);
//        verticalPanelC.add(verticalPanel);
        //rootPanel.add(verticalPanelC);
        //horizontalCenter.addChild(verticalPanel);
    }

    /*
        MODULO PARA CARGAR LOS PRODUCTOS
     */
    public void loadProdutos() {


        verticalPanelC.clear();
        t.clear();
        VerticalPanel v = new VerticalPanel();
        v.setBorderWidth(2);
        v.setStyleName("paneles");

        HorizontalPanel menu = new HorizontalPanel();
        menu.setSize("600px", "30px");

        Tree acciones = new Tree();
        acciones.setStyleName("gwt-Label-Login");

        TreeItem accion = new TreeItem("Menu");
        accion.addItem(new Button("Ver Cart", new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                verCarrito();
            }
        }));
       // Window.alert(uID);
        //if (uID.equals(1));{
            accion.addItem(new Button("Historial", new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {
                    historial();
                }
            }));
       // }

        acciones.addItem(accion);
        menu.add(acciones);
        /*
            Accciones del menu
         */

        topLabel = new HorizontalPanel();
        topLabel.setSize("600px", "30px");

        //panel para agregar mensaje de agregar al carro
        DialogBoxContents = new VerticalPanel();
        DialogBoxContents.setSize("350px", "50px");
        v.add(menu);
        v.add(topLabel);
        v.add(DialogBoxContents);

        t.getRowFormatter().addStyleName(0, "FlexTable-Header");

        addColumn("Articulo", 0, 0);
        addColumn("Descripcion", 0, 1);
        addColumn("Precio", 0, 2);
        addColumn("Cantidad Existente", 0, 3);
        //addColumn("Cantidad Agregar", 0, 4);
        addColumn("Accion", 0, 4);

        // ...and set it's column span so that it takes up the whole row.
        //t.getFlexCellFormatter().setColSpan(1, 0, 3);
        t.setCellSpacing(0);
        t.addStyleName("FlexTable");

        topMensajeP = new HTML("Listado de Productos: ");
        topMensajeP.setStyleName("gwt-Label-Login");
        topLabel.add(topMensajeP);

        verticalPanelC.add(v);
        verticalPanelC.add(t);

        /*
            Extraccion de productos
         */
        bookService = (BookServiceAsync) GWT.create(BookService.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) bookService;
        String moduleRelativeUrl = GWT.getModuleBaseURL() + "rpc";
        endpoint.setServiceEntryPoint(moduleRelativeUrl);

        // Call a method on the service!
        bookService.listaProductos(new AsyncCallback() {
            public void onSuccess(Object result) {
                String r = (String) result;
                JSONValue p = JSONParser.parseStrict(r);


                for (int i = 0; i < p.isArray().size(); i++) {
                    //Window.alert("valor: "+p.isArray().get(i));
                    JSONObject obj = (JSONObject) p.isArray().get(i);
                    //t.addStyleName("FlexTable-OddRow");

                    //agregando item a su posicion correspondiente
                    final String pID = obj.get("id").toString().replace('"', ' ');

                    final String n = obj.get("nombre").toString().replace('"', ' ');
                    addColumn(n, i + 1, 0);

                    final String d = obj.get("descripcion").toString().replace('"', ' ');
                    addColumn(d, i + 1, 1);

                    final String precio = obj.get("precioVenta").toString().replace('"', ' ');
                    addColumn(precio, i + 1, 2);

                    String c = obj.get("cantidad").toString().replace('"', ' ');
                    addColumn(c, i + 1, 3);

//                    cantidadAgregar = new TextBox();
//                    addColumn(cantidadAgregar, i+1, 4);

                    Button boton = new Button("Add to Cart", new ClickHandler() {
                        public void onClick(ClickEvent event) {
                            cantidadAgregar = new TextBox();
                            //Window.alert("producto: "+id);
                            DialogBoxContents.clear();
                            messageP = new HTML("Agregue la cantidad que desea de ese articulo!!!");
                            messageP.setStyleName("gwt-Label-Login");

                            DialogBoxContents.add(messageP);
                            DialogBoxContents.add(cantidadAgregar);
                            Button agregar = new Button("Agregar cantidad", new ClickHandler() {
                                @Override
                                public void onClick(ClickEvent clickEvent) {

                                    if (cantidadAgregar.getText().length()==0){
                                        DialogBoxContents.clear();
                                        messageP = new HTML("Cantidad no puede estar vacia!!!");
                                        messageP.setStyleName("gwt-Login-CheckBox");
                                        DialogBoxContents.add(messageP);
                                    }else {

                                        String cantidad = cantidadAgregar.getText();
                                        pCarrito.put(pID,cantidad);

                                        String[] addToCart = {pID, n, d, precio, cantidad};
                                        verArticulos.add(addToCart);

                                        DialogBoxContents.clear();
                                        messageP = new HTML("Articulo agregado a carrito!!!");
                                        messageP.setStyleName("gwt-Login-CheckBox");
                                        DialogBoxContents.add(messageP);
                                    }
                                }
                            });
                            DialogBoxContents.add(agregar);
                        }
                    });
                    addColumn(boton, i+1, 4);
                }

            }

            public void onFailure(Throwable caught) {
            }

        });
        /*
            Fin de extraccion
         */
    }

    /*
        Formatting the table
     */
    private void addColumn(Object columnHeading, int row, int col) {
        Widget widget = createCellWidget(columnHeading);
        //int cell = t.getCellCount(0);

        widget.setWidth("100%");
        widget.addStyleName("FlexTable-ColumnLabel");

        t.setWidget(row, col, widget);

        t.getCellFormatter().addStyleName(row, col, "FlexTable-ColumnLabelCell");
    }

    private Widget createCellWidget(Object cellObject) {
        Widget widget = null;

        if (cellObject instanceof Widget)
            widget = (Widget) cellObject;
        else
            widget = new Label(cellObject.toString());

        return widget;
    }


    /*
        metodo para creacion de usuarios
     */
    public void crearCuenta() {

        //Window.alert("mierdaaaaaaaaaa1111111111111");

        verticalPanelC.clear();

        //Window.alert("mierdaaaaaaaaaa22222222222");
        DialogBoxContentsCrear = new HorizontalPanel();
        DialogBoxContentsCrear.setSize("350px", "50px");
        DialogBoxContentsCrear.clear();
        //verticalPanelC.add(DialogBoxContentsCrear);
        //Window.alert("mierdaaaaaaaaaa33333333333");

        Label lblCrear = new Label("Menu Crear Usuario");
        lblCrear.setStyleName("gwt-Label-Login");
        verticalPanelC.add(lblCrear);

        //Window.alert("mierdaaaaaaaaaa444444444444");
        Label lblUsername = new Label("Username");
        lblUsername.setStyleName("gwt-Label-Login");
        userName = new TextBox();
        flexCampos.setWidget(0,0,lblUsername);
        flexCampos.setWidget(0,1,userName);


        Label lblNombre = new Label("Nombre");
        lblNombre.setStyleName("gwt-Label-Login");
        nombre = new TextBox();
        flexCampos.setWidget(1,0,lblNombre);
        flexCampos.setWidget(1,1, nombre);


        Label lblApellido = new Label("Apellido");
        lblApellido.setStyleName("gwt-Label-Login");
        apellido = new TextBox();
        flexCampos.setWidget(2,0,lblApellido);
        flexCampos.setWidget(2,1,apellido);

        Label lblPassword = new Label("Password");
        lblPassword.setStyleName("gwt-Label-Login");
        password = new TextBox();
        flexCampos.setWidget(3,0,lblPassword);
        flexCampos.setWidget(3,1,password);

        Button crear = new Button("Crear", new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if(userName.getText().length()==0 || password.getText().length()==0 || nombre.getText().length()==0 ||apellido.getText().length()==0){
                    DialogBoxContentsCrear.clear();
                    messageC = new HTML("Debe llenar todos los campos!!");
                    messageC.setStyleName("gwt-Login-CheckBox");
                    DialogBoxContentsCrear.add(messageC);

                }else{
                    bookService = (BookServiceAsync) GWT.create(BookService.class);
                    ServiceDefTarget endpoint = (ServiceDefTarget) bookService;
                    String moduleRelativeUrl = GWT.getModuleBaseURL() + "rpc";
                    endpoint.setServiceEntryPoint(moduleRelativeUrl);

                    // Call a method on the service!
                    bookService.createUser(userName.getText(), password.getText(),nombre.getText(),apellido.getText(), new AsyncCallback() {
                        public void onSuccess(Object result) {
                            // It's always safe to downcast to the known return type.
                            if (result.equals("{}")){
                                DialogBoxContentsCrear.clear();
                                messageC = new HTML("Error al procesar el formulario");
                                messageC.setStyleName("gwt-Login-CheckBox");
                                DialogBoxContentsCrear.add(messageC);
                            }else{
                                DialogBoxContentsCrear.clear();
                                user = result;
                                JSONValue userArray = JSONParser.parseStrict((String)user);

                                //id usuario
                                uID = userArray.isObject().get("id").toString();
                                loadProdutos();
                            }

                        }
                        public void onFailure(Throwable caught) {
                        }

                    });
                }
            }
        });

        Button cancelar = new Button("Cancel", new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                onModuleLoad();
            }
        });

        //Adding Button to a flexBotones
        flexBotones.setWidget(0,0,crear);
        flexBotones.setWidget(0,1,cancelar);

        /*
            Adding elements to the panel
         */
        verticalPanelC.add(DialogBoxContentsCrear);
        verticalPanelC.add(flexCampos);
        verticalPanelC.add(flexBotones);
//        rootPanel.add(verticalPanelC);
    }

    /*
        Historial de ventas
    */

    public void historial(){

        verticalPanelC.clear();
        t.clear();
        VerticalPanel v = new VerticalPanel();
        v.setBorderWidth(2);
        v.setStyleName("paneles");

        HorizontalPanel menu = new HorizontalPanel();
        menu.setSize("600px", "30px");

        Tree acciones = new Tree();
        acciones.setStyleName("gwt-Label-Login");

        TreeItem accion = new TreeItem("Menu");
        accion.addItem(new Button("Ver Cart", new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                verCarrito();
            }
        }));
        // Window.alert(uID);
        //if (uID.equals(1));{
        accion.addItem(new Button("Productos", new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                loadProdutos();
            }
        }));

        acciones.addItem(accion);
        menu.add(acciones);
        /*
            Accciones del menu
         */
        topLabel = new HorizontalPanel();
        topLabel.setSize("600px", "30px");
        topMensajeP = new HTML("Historial de ventas: ");
        topMensajeP.setStyleName("gwt-Label-Login");
        topLabel.add(topMensajeP);

        //panel para agregar mensaje de agregar al carro
        DialogBoxContents = new VerticalPanel();
        DialogBoxContents.setSize("350px", "50px");

        //lenar listas del historial
        t.getRowFormatter().addStyleName(0, "FlexTable-Header");

        addColumn("Carrito", 0, 0);
        //addColumn("Usuario", 0, 1);
        addColumn("Fecha", 0, 1);
        addColumn("Monto total", 0, 2);

        // ...and set it's column span so that it takes up the whole row.
        //t.getFlexCellFormatter().setColSpan(1, 0, 3);
        t.setCellSpacing(0);
        t.addStyleName("FlexTable");

        v.add(menu);
        v.add(topLabel);
        v.add(DialogBoxContents);
        v.add(t);
        verticalPanelC.add(v);

        bookService = (BookServiceAsync) GWT.create(BookService.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) bookService;
        String moduleRelativeUrl = GWT.getModuleBaseURL() + "rpc";
        endpoint.setServiceEntryPoint(moduleRelativeUrl);

        // Call a method on the service!
        bookService.historial(new AsyncCallback() {
            public void onSuccess(Object result) {
                String r = (String) result;
                JSONValue p = JSONParser.parseStrict(r);


                for (int i = 0; i < p.isArray().size(); i++) {
                    //Window.alert("valor: "+p.isArray().get(i));
                    JSONObject obj = (JSONObject) p.isArray().get(i);
                    //t.addStyleName("FlexTable-OddRow");

                    //agregando item a su posicion correspondiente
                    final String pID = obj.get("id").toString().replace('"', ' ');
                    addColumn(pID, i + 1, 0);
//                    final String n = obj.get("nombre").toString().replace('"', ' ');
//                    addColumn(n, i + 1, 0);

                    String d = obj.get("fechaVenta").toString().replace('"', ' ');
                    addColumn(d, i + 1, 1);

                    String total = obj.get("montoTotal").toString().replace('"', ' ');
                    addColumn(total, i + 1, 2);

//                    String c = obj.get("cantidad").toString().replace('"', ' ');
//                    addColumn(c, i + 1, 3);
                }

            }

            public void onFailure(Throwable caught) {
            }

        });
    }

    /*
        Ver Carrito
     */
    public void verCarrito(){

        verticalPanelC.clear();
        t.clear();
        VerticalPanel v = new VerticalPanel();
        v.setBorderWidth(2);
        v.setStyleName("paneles");

        HorizontalPanel menu = new HorizontalPanel();
        menu.setSize("600px", "30px");

        Tree acciones = new Tree();
        acciones.setStyleName("gwt-Label-Login");

        TreeItem accion = new TreeItem("Menu");
//        accion.addItem(new Button("Ver Cart", new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent clickEvent) {
//                Window.alert("carrito");
//            }
//        }));
        // Window.alert(uID);
        //if (uID.equals(1));{
        accion.addItem(new Button("Productos", new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                loadProdutos();
            }
        }));


        acciones.addItem(accion);
        menu.add(acciones);
        /*
            Accciones del menu
         */
        topLabel = new HorizontalPanel();
        topLabel.setSize("600px", "30px");
        topMensajeP = new HTML("Productos en el carrito: ");
        topMensajeP.setStyleName("gwt-Label-Login");
        topLabel.add(topMensajeP);

        //Check Out
        Button check =  new Button("Check Out", new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {

                //Window.alert("valores: "+pCarrito);
                if (!pCarrito.isEmpty()){
                    bookService.checkOut(pCarrito, uID, new AsyncCallback() {

                        @Override
                        public void onFailure(Throwable throwable) {
                            DialogBoxContents.clear();
                            messageP = new HTML("Problema al hacer Check Out!!!");
                            messageP.setStyleName("gwt-Login-CheckBox");
                            DialogBoxContents.add(messageP);
                        }

                        @Override
                        public void onSuccess(Object o) {
                            verArticulos = new ArrayList<String[]>();
                            pCarrito  = new HashMap<String, String>();
                            DialogBoxContents.clear();
                            messageP = new HTML("Check Out Succefull!!!");
                            messageP.setStyleName("gwt-Login-CheckBox");
                            DialogBoxContents.add(messageP);
                            loadProdutos();
                        }
                    });
                }else{
                    DialogBoxContents.clear();
                    messageP = new HTML("Debe tener articulos para Check Out!!!");
                    messageP.setStyleName("gwt-Login-CheckBox");
                    DialogBoxContents.add(messageP);
                }
            }
        });
        topLabel.add(check);

        //panel para agregar mensaje de agregar al carro
        DialogBoxContents = new VerticalPanel();
        DialogBoxContents.setSize("350px", "50px");

        //lenar listas del historial
        t.getRowFormatter().addStyleName(0, "FlexTable-Header");
//        String[] addToCart = {pID, n, d, precio, cantidad};
        addColumn("ID", 0, 0);
        addColumn("Producto", 0, 1);
        addColumn("Descripcion", 0, 2);
        addColumn("Precio", 0, 3);
        addColumn("Cantidad", 0, 4);
        addColumn("total", 0, 5);

        // ...and set it's column span so that it takes up the whole row.
        //t.getFlexCellFormatter().setColSpan(1, 0, 3);
        t.setCellSpacing(0);
        t.addStyleName("FlexTable");

        int total= 0;
        for (int j=0; j<verArticulos.size(); j++){
            String[] datos =  verArticulos.get(j);
            int cantidad = 0;
            int precio = 0;
            for(int x = 0;x<datos.length;x++){
                addColumn(datos[x], j + 1, x);
                if (x==3){
                    precio = Integer.parseInt(datos[x]);
                }
                if(x==4){
                    cantidad = Integer.parseInt(datos[x]);
                }
            }
            total = total+(cantidad*precio);
            addColumn((cantidad*precio),j+1,5);
        }
        TextBox monto = new TextBox();
        monto.setText(Integer.toString(total));
        monto.setReadOnly(true);
        Label montoTotal = new Label("Monto total: ");
        montoTotal.setStyleName("gwt-Label-Login");
        topLabel.add(montoTotal);
        topLabel.add(monto);


        v.add(menu);
        v.add(topLabel);
        v.add(DialogBoxContents);
        v.add(t);
        verticalPanelC.add(v);

    }
}