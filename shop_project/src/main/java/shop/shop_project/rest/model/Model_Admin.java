/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.shop_project.rest.model;

import rest.User;

import java.sql.*;
import java.util.ArrayList;
  
import javax.sql.DataSource;
import javax.naming.InitialContext;   
import rest.Product;
import rest.Category;
import rest.User;

public class Model_Admin implements IModel_Admin {

    private DataSource ds;
    
    public void InsertDBProduct(Product product) throws Exception {	
            String ProductName= product.getName();
            Integer ProductPrice=product.getPrice();
            Integer ProductCategory= product.getCategory_id();
            Integer ProductImage= product.getImage_id();
            Integer Product_id=0;
            ArrayList<String> feature_name=new ArrayList<>();
            ArrayList<String> feature_value=new ArrayList<>();
            feature_name=product.getFeature_name();
            feature_value=product.getFeature_value();
            	
            
            try 
		{			            
		    try {	        
	         InitialContext initialContext = new InitialContext();
             ds = (DataSource) initialContext.lookup("jdbc/local_shop");
	        }	
	        catch(Exception e) {	        		      
		      throw new Exception("Error while Data Source initializing: " + e.getMessage());
	        }
			
            Connection con = ds.getConnection();


            try 
			{
                                Statement stmt = con.createStatement();                			
				ResultSet rs = stmt.executeQuery("select get_product_id('"+ProductName+"',"+ProductPrice+","+ProductCategory+",+"+ProductImage+");");								
				while (rs.next()) {
					Product_id=rs.getInt(1);
                                        Statement stmt2 = con.createStatement();  
                                        for (int i=0; i<feature_name.size(); i++){
                                            stmt2.execute("INSERT INTO \"Sch_Shop\".\"Features\" (product_id,feature_name,feature_value)\n" +
                                                "VALUES("+Product_id+",'"+feature_name.get(i)+"','"+feature_value.get(i)+"');");	
                                        }
                                        
                                         
                                        stmt2.close();
				}
				rs.close();
				stmt.close();
            } finally {
                con.close();
                System.out.println("PRODUCT ID::: "+ Product_id);
            }			
        } 
		catch (Exception e) 
		{
            throw new Exception("Error while JDBC operating: " + e.getMessage());
        }
		
	}
        
      public void UpdateDBProduct(Product product) throws Exception {
            long Product_id=product.getId();
            String ProductName= product.getName();
            Integer ProductPrice=product.getPrice();
            Integer ProductCategory= product.getCategory_id();
            Integer ProductImage= product.getImage_id();
            ArrayList<String> feature_name=new ArrayList<>();
            ArrayList<String> feature_value=new ArrayList<>();
            feature_name=product.getFeature_name();
            feature_value=product.getFeature_value();
            	
            
            try 
		{			            
		    try {	        
	         InitialContext initialContext = new InitialContext();
             ds = (DataSource) initialContext.lookup("jdbc/local_shop");
	        }	
	        catch(Exception e) {	        		      
		      throw new Exception("Error while Data Source initializing: " + e.getMessage());
	        }
			
            Connection con = ds.getConnection();


            try 
			{
                                Statement stmt = con.createStatement();                			
                                stmt.execute("UPDATE \"Sch_Shop\".\"Products\" SET name = '"+ProductName+"', price="+ProductPrice+", category_id="+ProductCategory+", image_id="+ProductImage+" WHERE id = "+Product_id+";");	
                                stmt.execute("DELETE FROM \"Sch_Shop\".\"Features\" WHERE product_id ="+Product_id+";");
                                for (int i=0; i<feature_name.size(); i++){
                                            stmt.execute("INSERT INTO \"Sch_Shop\".\"Features\" (product_id,feature_name,feature_value)\n" +
                                                "VALUES("+Product_id+",'"+feature_name.get(i)+"','"+feature_value.get(i)+"');");	
                                        }
                               
				stmt.close();
            } finally {
                con.close();
                System.out.println("PRODUCT ID::: "+ Product_id);
            }			
        } 
		catch (Exception e) 
		{
            throw new Exception("Error while JDBC operating: " + e.getMessage());
        }
		
	}
      
      public void DeleteDBProduct(Product product) throws Exception {
          long Product_id=product.getId();
          
		try 
		{			            
		    try {	        
	         InitialContext initialContext = new InitialContext();
             ds = (DataSource) initialContext.lookup("jdbc/local_shop");
	        }	
	        catch(Exception e) {	        		      
		      throw new Exception("Error while Data Source initializing: " + e.getMessage());
	        }
			
            Connection con = ds.getConnection();


            try 
			{
                Statement stmt = con.createStatement();  
                                stmt.execute("DELETE FROM \"Sch_Shop\".\"Features\" WHERE product_id ="+Product_id+";");
				stmt.execute("DELETE FROM \"Sch_Shop\".\"Products\" WHERE id ="+Product_id+";");	
                             							
				stmt.close();
            } finally {
                con.close();
            }			
        } 
		catch (Exception e) 
		{
            throw new Exception("Error while JDBC operating: " + e.getMessage());
        }
	}
    
    
}
