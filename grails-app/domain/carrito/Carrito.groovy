package carrito

import usuario.Usuario

class Carrito {

    Usuario usuario;
    Date fechaVenta;
    BigDecimal montoTotal;
    int cantidadItems;

    static hasMany = [itemsCompra: ItemCompra];

    String creadoPor = "";
    String modificadoPor = "";
    Date dateCreated;
    Date lastUpdated;

    static constraints = {

        usuario(nullable: false);
        fechaVenta(nullable: false);
        montoTotal(nullable: false);

    }

    static mapping = {
        table 'cart_carritos'
    }

}
