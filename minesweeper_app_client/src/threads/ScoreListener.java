/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import game.Controller;

/**
 *
 * @author Marija Jeremic
 */
public class ScoreListener extends Thread {
    
    boolean end = false;
    boolean start = false;
    Controller controller;
    int threadId;
    
    public ScoreListener(Controller controller) {
        this.controller = controller;
    }
    
    public int getThreadId() {
        return threadId;
    }
    
    @Override
    public synchronized void run() {
        int score = 0;
        threadId = this.getThreadId();
        
        while(!end) {
            
            try {
                score++;
                this.controller.fxdc.score.setText(String.valueOf(score));
                this.sleep(1000);
                
            } catch (Exception e) {
                end = true;
                System.out.println("Thread " + this.getName() + " stopped working!");
            }
            
        }
        
    }
}
