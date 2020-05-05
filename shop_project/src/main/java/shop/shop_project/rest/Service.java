package rest;


import javax.ws.rs.Path;

import java.sql.*;

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

import rest.model.entity.EStudent;
import rest.Product;

import javax.inject.Inject;
import rest.Product;
import rest.Category;
import rest.User;
import rest.model.IModel;



@Path("/")
public class Service {
  @Inject
  IModel model;

 
 @GET
 @Path("/")
 @Produces("text/plain")
 public String ping() {   
  return "OK";
 } 


//Получение категорий с БД
  @POST   
  @Path("/getCategoryes")
  @Consumes("application/json")
  @Produces("application/json")
  public Response GetCategoryesDb() 
  {            

    Jsonb jsonb = JsonbBuilder.create(); 
    ArrayList<Category> categoryes= new ArrayList<>();      

    try {  
      categoryes = model.GetCategoryes();	  	   	 
    }
    catch (Exception e) {
      return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();	             
    }    
    return Response.ok(jsonb.toJson(categoryes)).build(); 

  }

//Получение категории с БД
  @POST   
  @Path("/getCategory")
  @Consumes("application/json")
  @Produces("application/json")
  public Response GetFromDb(String id) 
  {            

    Jsonb jsonb = JsonbBuilder.create(); 
    ArrayList<Product> products= new ArrayList<>();      

    try {  
      products = model.GetCategory(id);	  	   	 
    }
    catch (Exception e) {
      return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();	             
    }    
    return Response.ok(jsonb.toJson(products)).build(); 

  }

//Продукта с БД
  @POST   
  @Path("/getProduct")
  @Consumes("application/json")
  @Produces("application/json")
  public Response GetProdDb(String id) 
  {            

    Jsonb jsonb = JsonbBuilder.create(); 
    Product product = new Product();      

    try {  
      product = model.GetProduct(id);	  	   	 
    }
    catch (Exception e) {
      return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();	             
    }    
    return Response.ok(jsonb.toJson(product)).build(); 

  }
  
  //Получение данных с БД
  @POST   
  @Path("/goSearch")
  @Consumes("application/json")
  @Produces("application/json")
  public Response GetSearchDb(String search) 
  {            

    Jsonb jsonb = JsonbBuilder.create(); 
    ArrayList<Product> products= new ArrayList<>();      
              
    String resultJSON;
    try {  
      products = model.SearchProducts(search);
    }
    catch (Exception e) {
      return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();	             
    }    
    return Response.ok(jsonb.toJson(products)).build(); 

  }

   @POST   
  @Path("/authoriz")
  @Consumes("application/json")
  @Produces("application/json")
  public Response GetAuth(String json) 
  {           
    Jsonb jsonb = JsonbBuilder.create(); 
    User user= new User();  
    User user_data= new User();      
              
    try {  
      user = jsonb.fromJson(json, User.class);
    }
    catch (Exception e) {
      return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();	             
    } 
    try {  
      user_data = model.Auth(user);
    }
    catch (Exception e) {
      return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();	             
    } 
    return Response.ok(jsonb.toJson(user_data)).build(); 

  }
  
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