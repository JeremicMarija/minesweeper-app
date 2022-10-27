/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Marija Jeremic
 */
public class FXMLDocumentController implements Initializable{
    
    AuthController lcontroller;
    Stage stage;
    
    @FXML
    public AnchorPane loginPane;
    
    @FXML
    public Label windowLabel;
    
    @FXML
    public Label dontHaveAccText;
    
    @FXML
    public Label haveAccText;
    
    @FXML
    public Button loginBtn;
    
    @FXML
    public Button regBtn;
    
    @FXML
    public Button showRegBtn;
    
    @FXML
    public Button showLoginBtn;
    
    @FXML
    public TextField usernameField;
    
    @FXML
    public PasswordField passwordField;
    
    @FXML
    public TextField emailField;
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
        
        lcontroller = new AuthController(this);
    }
    
    @FXML
    private void handlePressedLoginBtn(ActionEvent event){
        
        emailField.setVisible(false);
        regBtn.setVisible(false);
        haveAccText.setVisible(false);
        showLoginBtn.setVisible(false);
        
        windowLabel.setText("Login");
        
        loginBtn.setVisible(true);
        dontHaveAccText.setVisible(true);
        showRegBtn.setVisible(true);
        
    }
    
    @FXML
    private void handlePressedRegBtn(ActionEvent event) {
        emailField.setVisible(true);
        regBtn.setVisible(true);
        haveAccText.setVisible(true);
        showLoginBtn.setVisible(true);
        
        windowLabel.setText("Registration");
        
        loginBtn.setVisible(false);
        dontHaveAccText.setVisible(false);
        showRegBtn.setVisible(false);
    }
    
    void setStage(Stage stage) {
        this.stage = stage;
    }
    
    void closeForm() {
        this.stage.close();
    }
}
