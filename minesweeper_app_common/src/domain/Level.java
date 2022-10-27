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

public class Level extends GeneralDomainObject implements Serializable {

   public int id;
   public String difficulty;
   public double percentageOfBomb;
   
   public Level(){
      
        id = 0;
        difficulty = "";
        percentageOfBomb = 0;
  }

   public Level(int id, String difficulty, double percentageOfBomb){
   
    this.id = id;
    this.difficulty = difficulty;
    this.percentageOfBomb = percentageOfBomb;
   }

    public Level(int id){
    
        this.id = id;
    }
    
    public void setId(int id){

        this.id = id;
    }
    
    public int getPrimaryKey(){
    
        return this.id;
    }

    @Override
    public String getAttributeValue() {
        return  id + ", " + (difficulty == null ? null : "'" + difficulty + "'") + ", " + percentageOfBomb;
     }

    @Override
    public String setAttributeValue() {
        return "LevelID=" + id + ", " + "difficulty=" + (difficulty == null ? null : "'" + difficulty + "'") + ", " + "percentageOfBomb=" + percentageOfBomb;
    }

    @Override
    public String getClassName() {
        return "Level";
    }

    @Override
    public String getWhereCondition() {
       return "difficulty = '" + difficulty + "'";
    }

    @Override
    public String getNameByColumn(int col) {
        String names[] = {"id", "difficulty", "percentageOfBomb"};
        return names[col];
    }

    @Override
    public String getColumnNames() {
        return " (difficulty,percentageOfBomb) ";
    }

    @Override
    public GeneralDomainObject getNewRecord(ResultSet rs) throws SQLException {
        return new Level(rs.getInt("id"), rs.getString("difficulty"),rs.getDouble("percentageOfBomb"));
    }
    
    
}
