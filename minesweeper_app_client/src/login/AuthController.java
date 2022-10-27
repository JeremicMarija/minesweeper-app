/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import communication.SocketCommunication;
import domain.User;
import game.MainMenuFx;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import transfer.Request;
import transfer.Response;

import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author Marija Jeremic
 */
public class AuthController {
    
    FXMLDocumentController fxdc;
    
    public AuthController(FXMLDocumentController fxmldoc) {
        this.fxdc = fxmldoc;
        this.fxdc.loginBtn.setOnAction(new LoginListener(this));
        this.fxdc.regBtn.setOnAction(new RegistrationListener(this));
    }
    
    void login(){
        
        String username = fxdc.usernameField.getText();
        String password = fxdc.passwordField.getText();
        

        if(username.isEmpty() || password.isEmpty()){
            alertMessage("Registration unsuccessful!", Alert.AlertType.ERROR);
        }else{
            transfer.Request request = new transfer.Request();
            request.setOperation(Operation.OPERATION_LOGIN);
            
            domain.User user = new domain.User();
            user.setUsername(username);
            user.setPassword(password);
 
            
            request.setUser(user);
            SocketCommunication.getInstance().sendRequest(request);
            
            transfer.Response response = SocketCommunication.getInstance().readResponse();
            
            if(response != null){
                if (response.getStatus() == ResponseStatus.ERROR) {
                    alertMessage("Login unsuccessful!", Alert.AlertType.ERROR);
                    return;
                } else {
                    alertMessage("Login successful!", Alert.AlertType.INFORMATION);
                }
                
                MainMenuFx main = new MainMenuFx();
                Stage s = new Stage();

                try {
                    main.setUser(response.getUser());
                    main.start(s);

                    fxdc.closeForm();
                } catch (Exception ex) {
                    Logger.getLogger(AuthController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                 alertMessage("Login unsuccessful!", Alert.AlertType.ERROR);
            }
        }

    }
    
    void register(){
        String username = fxdc.usernameField.getText();
        String password = fxdc.passwordField.getText();
        String email = fxdc.emailField.getText();
        
        if(username.isEmpty() || password.isEmpty() || email.isEmpty()){
            alertMessage("Registration unsuccessful!", Alert.AlertType.ERROR);
        }else{
            transfer.Request request = new transfer.Request();
            request.setOperation(Operation.OPERATION_REGISTER);
            
            domain.User user = new domain.User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setHighscore(10000);
            
            request.setUser(user);
            SocketCommunication.getInstance().sendRequest(request);
            
            transfer.Response response = SocketCommunication.getInstance().readResponse();
            
            if(response != null){
                if (response.getStatus() == ResponseStatus.ERROR) {
                    alertMessage("Registration unsuccessful!", Alert.AlertType.ERROR);
                    return;
                } else {
                    alertMessage("Registration successful!", Alert.AlertType.INFORMATION);
                }
                
                MainMenuFx main = new MainMenuFx();
                Stage s = new Stage();

                try {
                    main.setUser(response.getUser());
                    main.start(s);

                    fxdc.closeForm();
                } catch (Exception ex) {
                    Logger.getLogger(AuthController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                 alertMessage("Registration unsuccessful!", Alert.AlertType.ERROR);
            }
        }
    }
    
    public void alertMessage(String message, Alert.AlertType alertType) {
        Alert infoAlert = new Alert(alertType);
        
        switch (alertType) {
            case INFORMATION:
                infoAlert.setTitle("Success");
                break;
            case ERROR:
                infoAlert.setTitle("Error:");
                break;
            default:
                infoAlert.setTitle("Information");
                break;
        }
        
        infoAlert.setHeaderText(null);
        infoAlert.setContentText(message);
        infoAlert.showAndWait();
    }
}
