package org.grails.gwttutorial.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: Renso&Kenny
 * Date: 11/26/13
 * Time: 6:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Sesion extends RemoteServiceServlet {


    public void crearSesion(String userId){

        // create session and store userid
        HttpServletRequest request = this.getThreadLocalRequest();
        //true will create a new session if it not yet exists
        HttpSession session = request.getSession(true);
        session.setAttribute("UserID", userId);
    }

    public String returnSesion(){
        HttpServletRequest request = this.getThreadLocalRequest();
        // dont create a new one -> false
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("UserID") == null) {
            return "";
        }
        //do something with the value
        String userID = (String)session.getAttribute("UserID");
        Window.alert("Usuario id: " + userID);
        return userID;
    }
}