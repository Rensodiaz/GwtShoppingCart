import gwttutorial.Author
import gwttutorial.Book
import producto.Producto
import usuario.Usuario

class BootStrap {

    def init = { servletContext ->

        def author1 = new Author(firstName: "Jan", lastName: "Ehrhardt")
        def book1 = new Book(title: "GwtTutorial")
        author1.addToBooks(book1)

        author1.save()

        def user = new Usuario(userName: 'admin', password: 'admin', nombre: 'admin', apellido: 'admin').save(failOnError: true);

        def producto = new Producto(nombre: "pantalon", descripcion:"pantalon negro", precioVenta: 550.0, cantidad: 40).save(failOnError: true);
        def producto2 = new Producto(nombre: "Camisa", descripcion: "Camisa blanca", precioVenta: 750.0, cantidad: 60).save(failOnError: true);
        def producto3 = new Producto(nombre: "Tenis", descripcion: "Tenis Sports", precioVenta: 1850.0, cantidad: 100).save(failOnError: true);

    }
    def destroy = {
    }
}
