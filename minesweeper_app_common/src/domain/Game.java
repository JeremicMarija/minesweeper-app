/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Marija Jeremic
 */
public class Game extends GeneralDomainObject implements Serializable{
    
    private int id;
    private Date date;
    private int score;
    private int levelId;
    private int userId;
    
    public Game(){
        
      id = 0;
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      date = new Date();
      date = java.sql.Date.valueOf(sdf.format(date));
      score = 0;
      levelId = 0;
      userId = 0;
    }
    
    public Game(int id, Date date, int score, int levelId, int userId){
      
        this.id = id;
        this.date = date;
        this.score = score;
        this.levelId = levelId;
        this.userId = userId;
    }
    
    public Game( int id){
 
        this.id = id;
    }
    
    public void setId(int id){
    
        this.id = id;
    }
    
    public int getPrimaryKey(){
    
        return this.id;
    }
    
    public Date getDate(Date date){
    
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.date = java.sql.Date.valueOf(sdf.format(date));
        return this.date;
    }
    
    public void setDate(Date date){
    
        this.date = date;
    }
    
    public void setScore(int score){
    
        this.score = score;
    }
    
    public int getLevel(){
    
        return levelId;
    }
    
    public void setLevel(int level){
    
        this.levelId = level;
    }
    
    public int getUserId(){
    
        return userId;
    }
    
    public void setUserId(int userId){
    
        this.userId = userId;
    }
    


    @Override
    public String getAttributeValue() {
        return score + ", " + "'" + date + "'" + ", " + levelId + ", " + userId;
    }

    @Override
    public String setAttributeValue() {
       return "GameID="+ id + ", " + "Score=" + score + ", " + "Date=" + "'" + getDate(date) + "'" + ", " + "LevelId=" + levelId + ", " + "UserId=" + userId;
    }

    @Override
    public String getClassName() {
        return "Game";
    }

    @Override
    public String getWhereCondition() {
       return "GameID = " + id;
    }

    @Override
    public String getNameByColumn(int col) {
       String names[] = {"id", "score", "date", "levelId", "userId"};
       return names[col];
    }

    @Override
    public String getColumnNames() {
       return " (score, date, levelId, userId) ";
    }

    @Override
    public GeneralDomainObject getNewRecord(ResultSet rs) throws SQLException {
       return new Game(rs.getInt("id"),rs.getDate("date"), rs.getInt("score"), rs.getInt("levelId"), rs.getInt("userId"));
    }
    
    
    
    
}
