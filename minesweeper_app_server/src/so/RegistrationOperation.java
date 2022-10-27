/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.User;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;

/**
 *
 * @author Marija Jeremic
 */
public class RegistrationOperation extends GenericOperations{
    
    Request request;
    Response response;
    
    public Response registration(Request request){
        this.request = request;
        this.response = new Response();
        generalOperation();
        return response;
    }
    
   
    @Override
    public boolean executeOperation() {
         System.out.println(request.getUser().setAttributeValue());
         boolean success = dbb.insertRecord(request.getUser());
         if(success){
             response.setStatus(ResponseStatus.OK);
             response.setUser((User) dbb.findRecord(request.getUser()));
             return true;
         }
         response.setStatus(ResponseStatus.ERROR);
         return false;
    }
    
}
