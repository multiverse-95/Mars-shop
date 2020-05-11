
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
import shop.shop_project.rest.model.IModel_Favorite;

@Path("/")
public class Favorite_Controller {
    @Inject
    IModel_Favorite model;
    
  @POST   
  @Path("/GetProductsFavorite")
  @Consumes("application/json")
  @Produces("application/json")
  public Response GetProductsFavorite(String id) 
  {            

    Jsonb jsonb = JsonbBuilder.create(); 
    ArrayList<Product> products= new ArrayList<>();      

    try {  
      products = model.GetProductsFavorite(id);	  	   	 
    }
    catch (Exception e) {
      return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();	             
    }    
    return Response.ok(jsonb.toJson(products)).build(); 

  }
 
 
 @POST   
 @Path("/AddProductFav")
 @Consumes("application/json")
 @Produces("application/json")
 public Response AddProductFav(String productJSON) 
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

      model.AddProductFav(product);

      
      
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
 
 
 @POST   
 @Path("/DeleteProductFav")
 @Consumes("application/json")
 @Produces("application/json")
 public Response DeleteProductFav(String productJSON) 
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

      model.DeleteProductFav(product);

      
      
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
