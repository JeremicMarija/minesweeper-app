/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import DatabaseBroker.DbBroker;
import DatabaseBroker.DbBroker1;
import domain.GeneralDomainObject;

/**
 *
 * @author Marija Jeremic
 */
public abstract class GenericOperations {
     
    static public DbBroker dbb = new DbBroker1();
    GeneralDomainObject gdo;
    synchronized public boolean generalOperation(){
        dbb.makeConnection();
        boolean signal = executeOperation();
        if (signal == true){
            dbb.commitTransation();
        }else{
            dbb.rollbackTransation();
        }
        dbb.closeConnection();
        return signal;
    }
    
    abstract public boolean executeOperation();
    
    
}
