/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Marija Jeremic
 */
public class User extends GeneralDomainObject implements Serializable {
    
    public int id;
    public String username;
    public String password;
    public String email;
    public int highscore;
    
    public User(){
    
        id = 0;
        username = "";
        password = "";
        email = "";
        highscore = 0;
    }
    
    public User(int id, String username, String password, String email, int highscore){
        
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.highscore = highscore;
        
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    @Override
    public String getAttributeValue() {
        return (username == null ? null : "'" + username + "'") + ", " + (password == null ? null : "'" + password + "'") + "," + (email == null ? null : "'" + email + "'") + "," + "'" + highscore + "'";
    }

    @Override
    public String setAttributeValue() {
       return "Username=" + (username == null ? null : "'" + username + "'") + ", " + "Password=" + (password == null ? null : "'" + password + "'") + ", " + "Email=" + (email == null ? null : "'" + email + "'") + ", " + "Highscore=" + highscore;
    }

    @Override
    public String getClassName() {
        return "User";
    }

    @Override
    public String getWhereCondition() {
        return "username='" + username + "' and password='" + password + "'";
    }

    @Override
    public String getNameByColumn(int col) {
        String names[] = {"id", "username", "password", "email", "highscore"};
        return names[col];
    }

    @Override
    public String getColumnNames() {
        return " (username, password, email, highscore) ";
    }

    @Override
    public GeneralDomainObject getNewRecord(ResultSet rs) throws SQLException {
        return new User(rs.getInt("id"), rs.getString("username"),rs.getString("password"), rs.getString("email"), rs.getInt("highscore"));
    }

    @Override
    public int getPrimaryKey() {
        return id;
    }

    @Override
    public void setId(int id) {
        id = id;
    }
    
    
}
