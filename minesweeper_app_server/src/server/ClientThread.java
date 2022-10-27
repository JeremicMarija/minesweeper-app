/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import controller.Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author Marija Jeremic
 */
public class ClientThread extends Thread{
    
    private Socket clientSocket;
    private Server ss;
    int threadId;
    
    public ClientThread(Socket socket, Server server, int id) {
        this.clientSocket = socket;
        this.ss = server;
        this.threadId = id;
    }

    
    public int getThreadId() {
        
        return threadId;
    }
    
    @Override
    public void run(){
        try {
            System.out.println(ss.getClients().size() + " clients");
            communication();
        } catch (Exception ex) {
            changeClientList();
            try {
                this.clientSocket.close();
            } catch (IOException ex1) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.println("Client: end of work.");
        } 
    }

    private void communication() throws Exception{
        
        while (!isInterrupted()) {
            ObjectInputStream inSocket = new ObjectInputStream(clientSocket.getInputStream());
            Object object = inSocket.readObject();
            if (object instanceof Request) {
                Request request = (Request) object;
                Response response = handleRequest(request);
                sendResponse(clientSocket, response);
            }
        }
    }

    private Response handleRequest(Request request) {
                int operation = request.getOperation();
        Response response = new Response();

        switch (operation) {

            case Operation.OPERATION_LOGIN:
                try {
                    response = Controller.getInstance().login(request);
                    response.setStatus(ResponseStatus.OK);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex.getMessage());
                }
                break;

            case Operation.OPERATION_REGISTER:
                try {
                    response = Controller.getInstance().registration(request);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex.getMessage());
                }
                break;

            case Operation.OPERATION_GET_LEVEL_DIFFICULTY:
                try {
                    response = Controller.getInstance().getLevel(request);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex.getMessage());
                }
                break;

            case Operation.OPERATION_SAVE_GAME:
                try {
                    response = Controller.getInstance().saveGame(request);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex.getMessage());
                }
                break;

            case Operation.OPERATION_NEW_HIGHSCORE:
                try {
                    response = Controller.getInstance().saveHighscore(request);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex.getMessage());
                }
                break;
        }
        return response;
    }

    private void sendResponse(Socket socket, Response response) throws IOException{
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(response);
    }

    private void changeClientList() {
        List<ClientThread> clients = ss.getClients();

        for (ClientThread client : clients) {
            if (client.getThreadId() == this.threadId) {
                clients.remove(client);
            }
        }
        ss.setClients(clients);
        System.out.println(ss.getClients().size() + " clients");
    }
}
