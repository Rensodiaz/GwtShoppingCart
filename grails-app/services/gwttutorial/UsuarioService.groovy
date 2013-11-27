package gwttutorial

import usuario.Usuario

class UsuarioService {

    boolean transactional = true

    static expose = ["gwt:org.grails.gwttutorial.client"]

    String user(String userName, String pass){

        return Usuario.findByUserNameAndPassword(userName, pass).encodeAsJSON();
    }
}
