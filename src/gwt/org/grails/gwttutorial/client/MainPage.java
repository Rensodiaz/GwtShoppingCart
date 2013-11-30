//package org.grails.gwttutorial.client;
//
//import com.google.gwt.cell.client.ButtonCell;
//import com.google.gwt.core.client.EntryPoint;
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.rpc.ServiceDefTarget;
//import com.google.gwt.user.client.ui.*;
//import com.google.gwt.user.client.Window;
//
//import com.google.gwt.json.client.JSONArray;
//import com.google.gwt.json.client.JSONObject;
//import com.google.gwt.json.client.JSONParser;
//import com.google.gwt.json.client.JSONString;
//import com.google.gwt.json.client.JSONValue;
//import org.apache.http.util.EntityUtils;
//import producto.Producto;
//
//import java.util.List;
//
///**
// * Created with IntelliJ IDEA.
// * User: Renso&Kenny
// * Date: 11/21/13
// * Time: 1:57 AM
// * To change this template use File | Settings | File Templates.
// */
//public class MainPage  extends Composite{
//
//    private BookServiceAsync bookService;
//    private FlexTable t = new FlexTable();
//
//    HorizontalPanel topLabel;
//    VerticalPanel DialogBoxContents;
//    HTML message;
//    HTML topMensaje;
//    private TextBox cantidadAgregar;
//
//
//    public void loadProdutos(final Object user) {
//
//
//        RootPanel.get().clear();
//        HorizontalPanel hp = new HorizontalPanel();
//
//        VerticalPanel v = new VerticalPanel();
//        v.setBorderWidth(2);
//        v.setStyleName("gwt-StackPanel");
//
//        topLabel = new HorizontalPanel();
//        topLabel.setSize("600px", "30px");
//
//        //panel para agregar mensaje de agregar al carro
//        DialogBoxContents = new VerticalPanel();
//        DialogBoxContents.setSize("350px", "50px");
//
//        v.add(topLabel);
//        v.add(DialogBoxContents);
//
//        t.getRowFormatter().addStyleName(0, "FlexTable-Header");
//
//        addColumn("Articulo", 0, 0);
//        addColumn("Descripcion", 0, 1);
//        addColumn("Precio", 0, 2);
//        addColumn("Cantidad Existente", 0, 3);
//        //addColumn("Cantidad Agregar", 0, 4);
//        addColumn("Accion", 0, 4);
//
//        // ...and set it's column span so that it takes up the whole row.
//        //t.getFlexCellFormatter().setColSpan(1, 0, 3);
//        t.setCellSpacing(0);
//        t.addStyleName("FlexTable");
//
//        topMensaje = new HTML("Listado de Productos para "+user);
//        topMensaje.setStyleName("gwt-Label-Login");
//        topLabel.add(topMensaje);
//
//        RootPanel.get().add(v);
//        RootPanel.get().add(t);
//
//        /*
//            Extraccion de productos
//         */
//        bookService = (BookServiceAsync) GWT.create(BookService.class);
//        ServiceDefTarget endpoint = (ServiceDefTarget) bookService;
//        String moduleRelativeUrl = GWT.getModuleBaseURL() + "rpc";
//        endpoint.setServiceEntryPoint(moduleRelativeUrl);
//
//        // Call a method on the service!
//        bookService.listaProductos(new AsyncCallback() {
//            public void onSuccess(Object result) {
//                String r = (String) result;
//                JSONValue p = JSONParser.parseStrict(r);
//
//
//                for (int i = 0; i < p.isArray().size(); i++) {
//                    //Window.alert("valor: "+p.isArray().get(i));
//                    JSONObject obj = (JSONObject) p.isArray().get(i);
//                    //t.addStyleName("FlexTable-OddRow");
//
//                    //agregando item a su posicion correspondiente
//                    final String pID = obj.get("id").toString().replace('"', ' ');
//
//                    final String n = obj.get("nombre").toString().replace('"', ' ');
//                    addColumn(n, i + 1, 0);
//
//                    String d = obj.get("descripcion").toString().replace('"', ' ');
//                    addColumn(d, i + 1, 1);
//
//                    String precio = obj.get("precioVenta").toString().replace('"', ' ');
//                    addColumn(precio, i + 1, 2);
//
//                    String c = obj.get("cantidad").toString().replace('"', ' ');
//                    addColumn(c, i + 1, 3);
//
////                    cantidadAgregar = new TextBox();
////                    addColumn(cantidadAgregar, i+1, 4);
//
//                    Button boton = new Button("Add to Cart", new ClickHandler() {
//                        public void onClick(ClickEvent event) {
//                            cantidadAgregar = new TextBox();
//                            //Window.alert("producto: "+id);
//                            DialogBoxContents.clear();
//                            message = new HTML("Agregue la cantidad que desea de ese articulo!!!");
//                            message.setStyleName("gwt-Label-Login");
//
//                            DialogBoxContents.add(message);
//                            DialogBoxContents.add(cantidadAgregar);
//                            Button agregar = new Button("cantidad", new ClickHandler() {
//                                @Override
//                                public void onClick(ClickEvent clickEvent) {
//
//                                    if (cantidadAgregar.getText().length()==0){
//                                        DialogBoxContents.clear();
//                                        message = new HTML("Cantidad no puede estar vacia!!!");
//                                        message.setStyleName("gwt-Login-CheckBox");
//                                        DialogBoxContents.add(message);
//                                    }else {
//
//                                        String cantidad = cantidadAgregar.getText();
//                                        //Window.alert("aqui: " +cantidad);
//                                        JSONValue userArray = JSONParser.parseStrict((String)user);
//                                        //Window.alert("aqui: "+userArray.isObject().get("nombre"));
//
//                                        String uID = userArray.isObject().get("id").toString();
//
//                                        bookService.agregarACarrito(uID, pID, cantidad,new AsyncCallback() {
//
//                                            @Override
//                                            public void onFailure(Throwable throwable) {
//                                                //To change body of implemented methods use File | Settings | File Templates.
//                                            }
//
//                                            @Override
//                                            public void onSuccess(Object o) {
//                                                DialogBoxContents.clear();
//                                                message = new HTML("Articulo agregado a carrito!!!");
//                                                message.setStyleName("gwt-Login-CheckBox");
//                                                message.setStyleName("gwt-Login-CheckBox");
//                                                message.setStyleName("gwt-Login-CheckBox");
//                                                DialogBoxContents.add(message);
//                                            }
//                                        });
//                                    }
//                                }
//                            });
//                            DialogBoxContents.add(agregar);
//                        }
//                    });
//                    addColumn(boton, i+1, 4);
//
//
//                }
//
//            }
//
//            public void onFailure(Throwable caught) {
//            }
//
//        });
//        /*
//            Fin de extraccion
//         */
//    }
//
//    /*
//        Formatting the table
//     */
//    private void addColumn(Object columnHeading, int row, int col) {
//        Widget widget = createCellWidget(columnHeading);
//        //int cell = t.getCellCount(0);
//
//        widget.setWidth("100%");
//        widget.addStyleName("FlexTable-ColumnLabel");
//
//        t.setWidget(row, col, widget);
//
//        t.getCellFormatter().addStyleName(row, col, "FlexTable-ColumnLabelCell");
//    }
//
//    private Widget createCellWidget(Object cellObject) {
//        Widget widget = null;
//
//        if (cellObject instanceof Widget)
//            widget = (Widget) cellObject;
//        else
//            widget = new Label(cellObject.toString());
//
//        return widget;
//    }
//}
