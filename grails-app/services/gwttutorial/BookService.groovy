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

//    String checkOut(String userID, String productoID, String cantidad){
//
//        int uID = Integer.parseInt(userID);
//        int pID = Integer.parseInt(productoID);
//
//        Usuario u = Usuario.get(uID);
//        Producto p = Producto.get(pID);
//
//        println("usuario: "+u.nombre+" producto: "+p.nombre+"cantidad: "+cantidad)
//        return "Bien";
//    }

    String checkOut(HashMap<String,String> items, String usuario){

        int montoTotal = 0;
        def p  = new Producto();
        int cantidad;
        def id  = Integer.parseInt(usuario);
        List<ItemCompra> list = new ArrayList<>();
        def u = Usuario.findById(id);



        for(def key : items){
            def iP = new ItemCompra();
            int k = Integer.parseInt(key.key)
            p = Producto.findById(k);
            cantidad = Integer.parseInt(key.value);
            iP.cantidad = cantidad;
            iP.producto = p;

            if (p.cantidad>cantidad){
                list.add(iP);
            }else {
                return "NoCantidad";
            }
        }


        for(ItemCompra i in list){
            println("producto nombre: "+i.producto.nombre)
            //ItemCompras ic = new ItemCompras();
            montoTotal = montoTotal+(i.cantidad*i.producto.precioVenta);
            i.producto.cantidad = i.producto.cantidad-i.cantidad;
            i.producto.save(failOnError: true);
            i.save(failOnError: true);
        }

        Carrito carro = new Carrito(usuario: u,
                montoTotal: montoTotal,
                fechaVenta: new Date(),
                itemsCompra: list);
        if (carro.save(failOnError: true)){
            return "good";
        }else{
            return "bad";
        }
    }
    String historial(){
        return Carrito.findAll().encodeAsJSON();
    }

}
