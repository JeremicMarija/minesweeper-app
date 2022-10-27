/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.User;
import transfer.Request;
import transfer.Response;

/**
 *
 * @author Marija Jeremic
 */
public class LoginOperation extends GenericOperations{
    
    Request request;
    Response response;
    
    public Response login(Request request){
        this.request = request;
        this.response = new Response();
        generalOperation();
        return response;
    }

    @Override
    public boolean executeOperation() {
        System.out.println(request.getUser().getAttributeValue());
        User user = (User) dbb.findRecord(request.getUser());
        response.setUser(user);
        return true;
    }
    
}
