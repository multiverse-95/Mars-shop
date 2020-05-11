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
import rest.model.IModel_Products;



@Path("/")
public class Products_Controller {
  @Inject
  IModel_Products model;
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


 
}