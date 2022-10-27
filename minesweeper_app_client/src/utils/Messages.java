/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.scene.control.Alert;

/**
 *
 * @author Marija Jeremic
 */
public class Messages {
    
    public static void startGameMsg(){
        
        Alert.AlertType errorAlert = Alert.AlertType.INFORMATION;
        String title = "Game started!";
        String message = "Your game has started!";
        
        alertTemplate(title, message, errorAlert);
    }
    
    public static void failedStartGameMsg(){
        
        Alert.AlertType errorAlert = Alert.AlertType.ERROR;
        String title = "Error!";
        String message = "Your game cannot start!";
        
        alertTemplate(title, message, errorAlert);
    }
    
    public static void failedOpenCellMsg(){
        
        Alert.AlertType errorAlert = Alert.AlertType.ERROR;
        String title = "Error!";
        String message = "Error while opening the chosen field.";
        
        alertTemplate(title, message, errorAlert);
    }
    
    public static void winErrorMsg(){
        
        Alert.AlertType errorAlert = Alert.AlertType.ERROR;
        String title = "Error!";
        String message = "Error while showing the result of your game.";
        
        alertTemplate(title, message, errorAlert);
    }
    
    public static void saveGameMsg(){
        
        Alert.AlertType errorAlert = Alert.AlertType.INFORMATION;
        String title = "INFORMATION";
        String message = "Your game record has been saved.";
        
        alertTemplate(title, message, errorAlert);
    }
    
    public static void saveGameErrorMsg(){
        
        Alert.AlertType errorAlert = Alert.AlertType.ERROR;
        String title = "Error";
        String message = "System cannot save the game record.";
        
        alertTemplate(title, message, errorAlert);
    }
    
    public static void saveHighscoreMsg(){
        
        Alert.AlertType errorAlert = Alert.AlertType.INFORMATION;
        String title = "INFORMATION";
        String message = "Your highscore has been saved.";
        
        alertTemplate(title, message, errorAlert);
    }
    
    public static void saveHighscoreErrorMsg(){
        
        Alert.AlertType errorAlert = Alert.AlertType.ERROR;
        String title = "Error";
        String message = "System cannot save your highscore.";
        
        alertTemplate(title, message, errorAlert);
    }
    
    public static void alertTemplate(String title, String message, Alert.AlertType alertType) {
        
        Alert infoAlert = new Alert(alertType);
        infoAlert.setTitle(title);
        infoAlert.setHeaderText(null);
        infoAlert.setContentText(message);
        infoAlert.showAndWait();
    }
}
