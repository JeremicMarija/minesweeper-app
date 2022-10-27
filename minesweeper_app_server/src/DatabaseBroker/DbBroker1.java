/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseBroker;

import domain.GeneralDomainObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marija Jeremic
 */
public class DbBroker1 extends DbBroker{
    
    Connection conn = null;

    @Override
    public boolean makeConnection() {
        String Url;
                try {
            Class.forName("com.mysql.jdbc.Driver");
            Url = "jdbc:mysql://127.0.0.1:3306/minesweeperdb";
            conn = DriverManager.getConnection(Url, "root", "");
            conn.setAutoCommit(false); // Ako se ovo ne uradi nece moci da se radi roolback.
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DbBroker1.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean insertRecord(GeneralDomainObject gdo) {
        String query = "INSERT INTO " + gdo.getClassName() + gdo.getColumnNames() + " VALUES (" + gdo.getAttributeValue() + ")";
        System.out.println(query);
        return executeUpdate(query);
    }

    @Override
    public boolean updateRecord(GeneralDomainObject gdo) {
        String query = "UPDATE " + gdo.getClassName() + " SET " + gdo.setAttributeValue() + " WHERE " + gdo.getWhereCondition();
        return executeUpdate(query);
    }

    @Override
    public boolean updateRecord(GeneralDomainObject gdo, GeneralDomainObject gdoid) {
        String query = "UPDATE " + gdo.getClassName() + " SET " + gdo.setAttributeValue() + " WHERE " + gdoid.getWhereCondition();
        return executeUpdate(query);
    }

    @Override
    public boolean deleteRecord(GeneralDomainObject gdo) {
        String query = "DELETE FROM " + gdo.getClassName() + " WHERE " + gdo.getWhereCondition();
        return executeUpdate(query);
    }

    @Override
    public boolean deleteRecords(GeneralDomainObject gdo, String where) {
         String query = "DELETE FROM " + gdo.getClassName() + " " + where;
         return executeUpdate(query);
    }

    @Override
    public GeneralDomainObject findRecord(GeneralDomainObject gdo) {
       
        ResultSet rs = null;
        Statement st = null;
        String query = "SELECT * FROM " + gdo.getClassName() + " WHERE " + gdo.getWhereCondition();
        boolean signal;
        try{
            st = conn.prepareStatement(query);
            rs = st.executeQuery(query);
            signal = rs.next();
            if(signal == true){
                gdo = gdo.getNewRecord(rs);
            }else{
                gdo = null;
            }
        }catch (SQLException ex){
            Logger.getLogger(DbBroker1.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            close(null, st, rs);
        }
        return gdo;
    }

    @Override
    public List<GeneralDomainObject> findRecord(GeneralDomainObject gdo, String where) {
       ResultSet rs = null;
       Statement st = null;
       String query = "SELECT * FROM " + gdo.getClassName() + " " + where;
       List<GeneralDomainObject> list = new ArrayList<>();
       boolean signal;
       try{
           st = conn.prepareStatement(query);
           rs = st.executeQuery(query);
           while (rs.next()){
               list.add(gdo.getNewRecord(rs));
           }
       }catch(SQLException ex){
            Logger.getLogger(DbBroker1.class.getName()).log(Level.SEVERE, null, ex);
       }finally{
            close(null, st, rs);
        }
       return list;
    }

    @Override
    public boolean commitTransation() {
        try{
            conn.commit();
        }catch(SQLException ex){
            return false;
        }
        return true;
    }

    @Override
    public boolean rollbackTransation() {
        try{
            conn.rollback();
        }catch(SQLException ex){
            return false;
        }
        return true;
    }

    @Override
    public boolean increaseCounter(GeneralDomainObject gdo, AtomicInteger counter) {
        String query = "UPDATE Counter SET Counter =" + counter.get() + " WHERE TableName = '" + gdo.getClassName() + "'";
        return executeUpdate(query);
    }

    @Override
    public void closeConnection() {
        close(conn, null, null);
    }

    @Override
    public GeneralDomainObject getRecord(GeneralDomainObject gdo, int index) {
        ResultSet rs = null;
        Statement st = null;
        String query = "SELECT * FROM " + gdo.getClassName();
        query = query + " order by " + gdo.getNameByColumn(0) + " ASC LIMIT " + index + ", 1";
        boolean signal;
        try{
            st = conn.prepareCall(query);
            rs = st.executeQuery(query);
            signal = rs.next();
            if(signal == true){
                gdo = gdo.getNewRecord(rs);
            }else{
                gdo = null;
            }
        }catch(SQLException ex){
            gdo = null;
            Logger.getLogger(DbBroker1.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            close(null, st, rs);
        }
        return gdo;
    }

    @Override
    public int getRecordsNumber(GeneralDomainObject gdo) {
        ResultSet rs = null;
        Statement st = null;
        int recordsNum = 0;
        String query = "SELECT * FROM " + gdo.getClassName();
        boolean signal;
        try{
            st = conn.prepareStatement(query);
            rs = st.executeQuery(query);
            if(rs.last()){
                recordsNum = rs.getRow();
            }
        }catch(SQLException ex){
            Logger.getLogger(DbBroker1.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            close(null, st, rs);
        }
        return recordsNum;
    }

    private boolean executeUpdate(String query) {
        Statement st = null;
        boolean signal = false;
        try {
            st = conn.prepareStatement(query);
            int rowcount = st.executeUpdate(query);
            if (rowcount > 0) {
                signal = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker1.class.getName()).log(Level.SEVERE, null, ex);
            signal = false;
        } finally {
            close(null, st, null);
        }
        return signal;
    }

    private void close(Connection conn, Statement st, ResultSet rs) {
         if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(DbBroker1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(DbBroker1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DbBroker1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
