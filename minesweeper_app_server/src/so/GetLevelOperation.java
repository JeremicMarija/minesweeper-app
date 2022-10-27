/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.Level;
import transfer.Request;
import transfer.Response;

/**
 *
 * @author Marija Jeremic
 */
public class GetLevelOperation extends GenericOperations{
    
    Request request;
    Response response;
    
    public Response getLevel(Request request){
        this.request = request;
        this.response = new Response();
        generalOperation();
        return response;
    }

    @Override
    public boolean executeOperation() {
        System.out.println(request.getLevel().getWhereCondition());
        Level level = (Level) dbb.findRecord(request.getLevel());
        response.setLevel(level);
        return true;
    }
    
}
