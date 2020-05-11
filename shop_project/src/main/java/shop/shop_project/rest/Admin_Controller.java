
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
import rest.Product;
import shop.shop_project.rest.model.IModel_Admin;

@Path("/")
public class Admin_Controller {
    @Inject
    IModel_Admin model;
    
    //Добавление данных в БД
 @POST   
 @Path("/addProduct")
 @Consumes("application/json")
 @Produces("application/json")
 public Response InsertfromDB(String productJSON) 
 {            
   Jsonb jsonb = JsonbBuilder.create();          
    Product product = new Product();
   String resultJSON;
   try {  
      try { 
       product = jsonb.fromJson(productJSON, Product.class);                    
      }
      catch (Exception e) {
        throw new Exception("Error while JSON transforming.");  
      }

      model.InsertDBProduct(product);

      
      
	  resultJSON = jsonb.toJson(product);	  	 
   }
   catch (JsonbException e) {
    return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();	             
   }
   catch (Exception e) {
    return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();	             
   }    
   return Response.ok(resultJSON).build();           
 }
 
  //Добавление данных в БД
 @POST   
 @Path("/UpdateProduct")
 @Consumes("application/json")
 @Produces("application/json")
 public Response UpdatefromDB(String productJSON) 
 {            
   Jsonb jsonb = JsonbBuilder.create();          
    Product product = new Product();
   String resultJSON;
   try {  
      try { 
       product = jsonb.fromJson(productJSON, Product.class);                    
      }
      catch (Exception e) {
        throw new Exception("Error while JSON transforming.");  
      }

      model.UpdateDBProduct(product);

      
      
	  resultJSON = jsonb.toJson(product);	  	 
   }
   catch (JsonbException e) {
    return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();	             
   }
   catch (Exception e) {
    return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();	             
   }    
   return Response.ok(resultJSON).build();           
 }
 
  //Добавление данных в БД
 @POST   
 @Path("/DeleteProduct")
 @Consumes("application/json")
 @Produces("application/json")
 public Response DeletefromDB(String productJSON) 
 {            
   Jsonb jsonb = JsonbBuilder.create();          
    Product product = new Product();
   String resultJSON;
   try {  
      try { 
       product = jsonb.fromJson(productJSON, Product.class);                    
      }
      catch (Exception e) {
        throw new Exception("Error while JSON transforming.");  
      }

      model.DeleteDBProduct(product);

      
      
	  resultJSON = jsonb.toJson(product);	  	 
   }
   catch (JsonbException e) {
    return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();	             
   }
   catch (Exception e) {
    return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();	             
   }    
   return Response.ok(resultJSON).build();           
 }
    
}
