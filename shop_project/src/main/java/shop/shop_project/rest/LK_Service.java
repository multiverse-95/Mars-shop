package shop.shop_project.rest;

import javax.ws.rs.Path;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import javax.ws.rs.core.Response;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbException;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import rest.User;
import shop.shop_project.rest.model.IModel_LK;

@Path("/lk")
public class LK_Service {
    @Inject
    IModel_LK model;
    
    @POST   
    @Path("/upd-name")
    @Consumes("application/json")
    @Produces("application/json")
    public Response UpdateName(String json) 
    {            
        Jsonb jsonb = JsonbBuilder.create(); 
        User user = new User();      
        
        try {user = jsonb.fromJson(json, User.class);} 
        catch (Exception e) {return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();}

        try {user = model.Upd_Name(user);}
        catch (Exception e) {return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();}    
        
        return Response.ok(jsonb.toJson(user)).build(); 
    }
    
    @POST   
    @Path("/upd-address")
    @Consumes("application/json")
    @Produces("application/json")
    public Response UpdateAddress(String json) 
    {            
        Jsonb jsonb = JsonbBuilder.create(); 
        User user = new User();      
        
        try {user = jsonb.fromJson(json, User.class);} 
        catch (Exception e) {return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();}

        try {user = model.Upd_Address(user);}
        catch (Exception e) {return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();}    
        
        return Response.ok(jsonb.toJson(user)).build(); 
    }
}
