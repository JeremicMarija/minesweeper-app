/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;

/**
 *
 * @author Marja Jeremic
 */
public class SaveGameOperation extends GenericOperations{
    
    Request request;
    Response response;
    
    public Response saveGame(Request request){
        this.request = request;
        this.response = new Response();
        generalOperation();
        return response;
    }

    @Override
    public boolean executeOperation() {
        System.out.println(request.getGame().getAttributeValue());
        System.out.println(request.getUser().getAttributeValue());
        
        boolean success = dbb.insertRecord(request.getGame());
        
        if(success){
            response.setStatus(ResponseStatus.OK);
            return true;
        }
        response.setStatus(ResponseStatus.ERROR);
        return false;
    }
    
}
