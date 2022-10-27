/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import domain.Game;
import domain.Level;
import domain.User;
import java.io.Serializable;
import transfer.util.ResponseStatus;

/**
 *
 * @author Marija Jeremic
 */
public class Response implements Serializable{
    
    private Game game;
    private User user;
    private Level level;
    private Object error;
    private ResponseStatus status;
    
    public Response() {
    }
    
    
    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
