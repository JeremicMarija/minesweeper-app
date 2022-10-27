/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import javafx.event.Event;
import javafx.event.EventHandler;

/**
 *
 * @author Marija Jeremic
 */
public class LoginListener implements EventHandler{
    
    AuthController controller;
    
    public LoginListener(AuthController controller) {
        this.controller = controller;
    }
    
    @Override
    public void handle(Event event) {
        controller.login();
    }
    
    
}
