/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseBroker;

import domain.GeneralDomainObject;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Marija Jeremic
 */
public abstract class DbBroker {
    
    public abstract boolean makeConnection();
    public abstract boolean insertRecord(GeneralDomainObject gdo);
    public abstract boolean updateRecord(GeneralDomainObject gdo);
    public abstract boolean updateRecord(GeneralDomainObject gdo, GeneralDomainObject gdoid);
    public abstract boolean deleteRecord(GeneralDomainObject gdo);
    public abstract boolean deleteRecords(GeneralDomainObject gdo, String where);
    public abstract GeneralDomainObject findRecord(GeneralDomainObject gdo);
    public abstract List<GeneralDomainObject> findRecord(GeneralDomainObject gdo, String where);
    public abstract boolean commitTransation();
    public abstract boolean rollbackTransation();
    public abstract boolean increaseCounter(GeneralDomainObject gdo, AtomicInteger counter);
    public abstract void closeConnection();
    public abstract GeneralDomainObject getRecord(GeneralDomainObject gdo,int index);
    public abstract int getRecordsNumber(GeneralDomainObject gdo);
    
    
    
    
}
