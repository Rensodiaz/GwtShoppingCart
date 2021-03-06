package carrito

import producto.Producto

class ItemCompra {

    Producto producto;
    int cantidad;

    String creadoPor = "";
    String modificadoPor = "";
    Date dateCreated;
    Date lastUpdated;

    static constraints = {
        producto(nullable: false);
        cantidad(nullable: false);
    }

    static mapping = {
        table 'cart_items_compra'
    }

}
