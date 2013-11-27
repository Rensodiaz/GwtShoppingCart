package producto

class Producto {

    String nombre;
    String descripcion;
    BigDecimal precioVenta;
    int cantidad;

    static constraints = {

        descripcion(nullable: false);
        precioVenta(nullable: false);
    }

    static mapping = {
        table 'prod_productos'
    }

}
