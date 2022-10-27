/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import domain.User;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Marija Jeremic
 */
public class MainMenuFx extends Application{
    
    FXMLDocumentController con;
    static User user = new User();
    public static Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;

        String resourcePath = "FXMLDocument.fxml";
        URL location = getClass().getResource(resourcePath);
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = fxmlLoader.load();
        con = (FXMLDocumentController) fxmlLoader.getController();

        Scene scene = new Scene(root);

        scene.getStylesheets().add("assets/style/style.css");
        stage.setScene(scene);
        stage.setResizable(false);

        stage.setScene(scene);
        stage.setTitle("Minesweeper");
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public void setUser(String username, String password) {
        user.setUsername(username);
        user.setPassword(password);
    }

    public void setUser(User user) {
        this.user = user;
    }
}
