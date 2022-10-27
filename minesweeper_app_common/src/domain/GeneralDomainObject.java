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
public abstract class GeneralDomainObject implements Serializable{
    
    abstract public String getAttributeValue();
    
    abstract public String setAttributeValue();
    
    abstract public String getClassName();
    
    abstract public String getWhereCondition();
    
    abstract public String getNameByColumn(int col);
    
    abstract public String getColumnNames();
    
    abstract public GeneralDomainObject getNewRecord(ResultSet rs) throws SQLException;
    
    abstract public int getPrimaryKey();
    
    abstract public void setId(int id);
    
}

