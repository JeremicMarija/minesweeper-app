/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GUIListener;

import game.Controller;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 *
 * @author Marija Jeremic
 */
public class GUIListenerStartGame implements EventHandler{
    
    Controller controller;
    
    public GUIListenerStartGame(Controller controller) {
        this.controller = controller;
    }
    
        @Override
    public void handle(Event event) {

        controller.gameInitialization();
    }
    
}
