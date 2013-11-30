package org.grails.gwttutorial.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BookServiceAsync {
    void user(java.lang.String arg0, java.lang.String arg1, AsyncCallback callback);
    void listaProductos(AsyncCallback callback);
    void createUser(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, AsyncCallback callback);
    void checkOut(java.util.HashMap arg0, java.lang.String arg1, AsyncCallback callback);
    void historial(AsyncCallback callback);
    void getAuthor(int arg0, AsyncCallback callback);
}
