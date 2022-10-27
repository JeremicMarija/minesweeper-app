/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import server.Server;

/**
 *
 * @author Marija Jeremic
 */
public class Main {
    public static void main(String[] args){
        try {
            Server server = new Server();
            server.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
