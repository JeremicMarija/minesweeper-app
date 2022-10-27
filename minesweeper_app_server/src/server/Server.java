/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Marija Jeremic
 */
public class Server extends Thread{
    
    private boolean active = true;
    private int i;
    private List<ClientThread> clients;
    private List<Socket> clientSocket;
    private ServerSocket ss;
    
    private static Server instance;
    
    public List<ClientThread> getClients() {
        return clients;
    }

    public void setClients(List<ClientThread> clients) {
        this.clients = clients;
    }
    
    @Override
    public void run(){
        try {
            ss = new ServerSocket(9000);
            System.out.println("Server is up and running.");
            clients = new LinkedList<>();
            clientSocket = new LinkedList<>();
            this.i = 0;
            while(active){
                Socket socket = ss.accept();
                System.out.println("IP: " + socket.getInetAddress());
                System.out.println("Socket port: " + socket.getPort());
                ClientThread clientThread = new ClientThread(socket, this, i);
                clientThread.start();
                System.out.println("New Client");
                i++;
                clients.add(clientThread);
                clientSocket.add(socket);
            }
        } catch (IOException ex) {
            System.out.println("Server is stop working!");
        } 
    }
    
            public void stopServer() {
        try {
            active = false;
            ss.close();
            for (Socket socket : clientSocket) {
                socket.close();
            }
            
            System.out.println("Server socket is closed");
        } catch (IOException ex) {

        }
    }
    
    
}
