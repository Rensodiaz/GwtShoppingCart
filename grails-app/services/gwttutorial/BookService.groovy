package gwttutorial

import carrito.Carrito
import carrito.ItemCompra
import usuario.Usuario
import producto.Producto;

class BookService {

    boolean transactional = true

    static expose = ["gwt:org.grails.gwttutorial.client"]

    String getAuthor(int id) {
        def author = Author.get(id)
        return author.encodeAsJSON()
    }

    //method to find the user for login
    String user(String userName, String pass){

        return Usuario.findByUserNameAndPassword(userName, pass).encodeAsJSON();
    }

    //method to register de user
   String createUser(String userName, String password, String nombre, String apellido){
       def u = new Usuario(userName: userName, password: password, nombre: nombre, apellido: apellido);

       if (u.save(failOnError: true)){
           return u.encodeAsJSON();

       }else{

           return u.encodeAsJSON();
       }


    }

    //method to get all product
    String listaProductos(){
        return Producto.findAll().encodeAsJSON();
    }

    String agregarACarrito(String userID, String productoID, String cantidad){

        int uID = Integer.parseInt(userID);
        int pID = Integer.parseInt(productoID);

        Usuario u = Usuario.get(uID);
        Producto p = Producto.get(pID);

        println("usuario: "+u.nombre+" producto: "+p.nombre+"cantidad: "+cantidad)
        return "Bien";
    }

//    boolean checkOut(List<ItemCompra> items, String usuario){
//
//        def montoTotal = 0;
//
//        List<ItemCompra> list = new ArrayList<>();
//
//
////        for(ItemAcomprar i in items){
////            ItemCompras ic = new ItemCompras();
////            montoTotal = montoTotal+(i.item.cantidad*i.item.producto.precioVenta);
////            Producto p = Producto.findById(i.item.producto.id);
////            p.cantidadExistencia = p.cantidadExistencia-i.item.cantidad;
////            p.save(failOnError: true);
////
////            ic.producto = p;
////            ic.cantidad = i.item.cantidad;
////            ic.save(failOnError: true);
////            list.add(ic);
////        }
//        Carrito carro = new Carrito(usuario: usuario,
//                total: montoTotal,
//                fechaVenta: new Date(),
//                listaItems: list);
//        if (carro.save(failOnError: true)){
////           for (ItemCompras c in list){
////               println("guardado des: "+c.producto.nombre)
////           }
//            return true;
//        }else{
//            return false;
//        }
//
//    }
}
