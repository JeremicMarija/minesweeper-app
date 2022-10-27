/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import domain.GeneralDomainObject;
import game.FXMLDocumentController;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marija Jeremic
 */
public class ConverterGUIDK {
    
    public static boolean convertGUIDK(FXMLDocumentController fxcon, GeneralDomainObject gdo){
        
        for(Field f : fxcon.getClass().getDeclaredFields()){
            for(Field dk : gdo.getClass().getDeclaredFields()){
                if(dk.getName().equals(f.getName())){
                    if(f.getType().getName().equals("javafx.scene.control.TextField") && dk.getType().getName().equals("int")){
                        try {
                            Integer broj = Integer.valueOf(((javafx.scene.control.TextField) f.get(fxcon)).getText());
                            dk.set(gdo, broj);

                        } catch (IllegalArgumentException | IllegalAccessException ex) {
                            Logger.getLogger(ConverterGUIDK.class.getName()).log(Level.SEVERE, null, ex);
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public static boolean convertDKUGUI(GeneralDomainObject gdo, FXMLDocumentController fxcon){
        
        for(Field f : fxcon.getClass().getDeclaredFields()){
            for(Field dk : gdo.getClass().getDeclaredFields()){
                if(dk.getName().equals(f.getName())){
                    if(f.getType().getName().equals("javafx.scene.control.TextField") && dk.getType().getName().equals("int")){
                        try {
                            Integer number = (Integer) dk.get(gdo);
                            ((javafx.scene.control.TextField) f.get(fxcon)).setText(String.valueOf(number));

                        } catch (IllegalArgumentException | IllegalAccessException ex) {
                            Logger.getLogger(ConverterGUIDK.class.getName()).log(Level.SEVERE, null, ex);
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    
    
}
