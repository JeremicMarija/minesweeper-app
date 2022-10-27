/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.Socket;
import java.util.List;
import so.GenericOperations;
import so.GetLevelOperation;
import so.LoginOperation;
import so.RegistrationOperation;
import so.SaveGameOperation;
import so.SaveHighscoreOperation;
import transfer.Request;
import transfer.Response;

/**
 *
 * @author Marija Jeremic
 */
public class Controller {
    
    private static Controller instance;
    private GenericOperations go;
    private List<Socket> clients;
    
    private Controller() {
    }
    
    public static Controller getInstance() {
        
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    
    public Response login(Request request)throws Exception{
        go = new LoginOperation();
        return ((LoginOperation) go).login(request);
    }
    
    public Response registration(Request request) throws Exception{
        go = new RegistrationOperation();
        return ((RegistrationOperation) go).registration(request);
    }
    
    public Response getLevel(Request request){
        go = new GetLevelOperation();
        return ((GetLevelOperation) go).getLevel(request);
    }
    
    public Response saveGame(Request request) throws Exception{
        go = new SaveGameOperation();
        return ((SaveGameOperation) go).saveGame(request);
    }
    
    public Response saveHighscore(Request request) throws Exception{
        go = new SaveHighscoreOperation();
        return ((SaveHighscoreOperation) go).saveHighscore(request);
        
    }
    
}
