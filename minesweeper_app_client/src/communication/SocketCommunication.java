/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Request;
import transfer.Response;

/**
 *
 * @author Marija Jeremic
 */
public class SocketCommunication {
    
    private Socket socket;
    private static SocketCommunication instance;
    
    private SocketCommunication (){
                try {
            socket = new Socket("localhost", 9000);
        } catch (IOException ex) {
            Logger.getLogger(SocketCommunication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static SocketCommunication getInstance(){
        if(instance == null){
            instance = new SocketCommunication();
        }
        return instance;
    }
    
    public void sendRequest(Request request){
        try{
           ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
           output.writeObject(request);
        }catch(IOException ex){
            Logger.getLogger(SocketCommunication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Response readResponse(){
        ObjectInputStream in = null;
        try{
            in = new ObjectInputStream(socket.getInputStream());
            return (Response) in.readObject();
        }catch(Exception ex){
            Logger.getLogger(SocketCommunication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
