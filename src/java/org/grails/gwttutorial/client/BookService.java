package org.grails.gwttutorial.client;

import com.google.gwt.user.client.rpc.RemoteService;

public interface BookService extends RemoteService {
    java.lang.String user(java.lang.String arg0, java.lang.String arg1);
    java.lang.String createUser(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3);
    java.lang.String listaProductos();
    java.lang.String agregarACarrito(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2);
    java.lang.String getAuthor(int arg0);
}
