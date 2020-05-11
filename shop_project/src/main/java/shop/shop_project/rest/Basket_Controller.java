
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
import shop.shop_project.rest.model.IModel_Basket;

@Path("/")
public class Basket_Controller {
    @Inject
    IModel_Basket model;
    
    //Получение категории с БД
  @POST   
  @Path("/GetProductsBasket")
  @Consumes("application/json")
  @Produces("application/json")
  public Response GetProductsBasket(String id) 
  {            

    Jsonb jsonb = JsonbBuilder.create(); 
    ArrayList<Product> products= new ArrayList<>();      

    try {  
      products = model.GetProductsBasket(id);	  	   	 
    }
    catch (Exception e) {
      return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();	             
    }    
    return Response.ok(jsonb.toJson(products)).build(); 

  }
  
    //Добавление данных в БД
 @POST   
 @Path("/AddProductBasket")
 @Consumes("application/json")
 @Produces("application/json")
 public Response AddProductBasket(String productJSON) 
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

      model.AddProductBasket(product);

      
      
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
 @Path("/DeleteProductBasket")
 @Consumes("application/json")
 @Produces("application/json")
 public Response DeleteProductBasket(String productJSON) 
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

      model.DeleteProductBasket(product);

      
      
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
 @Path("/DeleteAllProductBasket")
 @Consumes("application/json")
 @Produces("application/json")
 public Response DeleteAllProductBasket(String productJSON) 
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

      model.DeleteAllProductBasket(product);

      
      
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
