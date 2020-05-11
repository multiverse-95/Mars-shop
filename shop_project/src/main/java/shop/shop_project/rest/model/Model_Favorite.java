/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.shop_project.rest.model;

import shop.shop_project.rest.model.interfaces.IModel_Favorite;
import shop.shop_project.rest.dto.User;

import java.sql.*;
import java.util.ArrayList;
  
import javax.sql.DataSource;
import javax.naming.InitialContext;   
import shop.shop_project.rest.dto.Product;
import shop.shop_project.rest.dto.Category;
import shop.shop_project.rest.dto.User;

public class Model_Favorite implements IModel_Favorite {

    private DataSource ds;
    
     public ArrayList<Product> GetProductsFavorite(String id) throws Exception {	
		ArrayList<Product> products = new ArrayList<>();			
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
        ResultSet rs = stmt.executeQuery("SELECT id, customer_id, product_id FROM \"Sch_Shop\".\"Favorite\" WHERE customer_id="+id);							
				while (rs.next()) {

					Product product = new Product();
					product.setId(rs.getInt("product_id"));

					Statement stmt2 = con.createStatement();
					ResultSet rs2 = stmt2.executeQuery("SELECT * FROM \"Sch_Shop\".\"Products\" WHERE id="+rs.getInt("product_id"));
					while (rs2.next()) { 

						product.setName(rs2.getString("name"));
						product.setPrice(rs2.getInt("price"));
						product.setCategory_id(rs2.getInt("category_id")); 
						
						Statement stmt3 = con.createStatement();
						ResultSet rs3 = stmt3.executeQuery("SELECT \"path\" FROM \"Sch_Shop\".\"Images\" where \"id\" = " + rs2.getString("image_id"));
						while (rs3.next()) { product.setImage(rs3.getString("path")); }
						rs3.close();
						stmt3.close();
						
					}
					rs2.close();
					stmt2.close();
	
					products.add(product);

				}
				rs.close();
				stmt.close();

				return products;
			} finally {
				con.close();
			}			
		} 
		catch (Exception e) {
			throw new Exception("Error while JDBC operating: " + e.getMessage());
		}
	}
        
        

	public void AddProductFav(Product product) throws Exception {	
		long Product_id;
		long User_id;
		Product_id=product.getId();
		User_id=product.getUser_id();
            
		try {			            
			try {	        
				InitialContext initialContext = new InitialContext();
					ds = (DataSource) initialContext.lookup("jdbc/local_shop");
			}	
			catch(Exception e) {	        		      
				throw new Exception("Error while Data Source initializing: " + e.getMessage());
			}

			Connection con = ds.getConnection();
      try {

				Statement stmt0 = con.createStatement();   
				ResultSet rs0 = stmt0.executeQuery("SELECT product_id, customer_id FROM \"Sch_Shop\".\"Favorite\" WHERE product_id="+Product_id+" AND customer_id="+User_id);							
				if (rs0.next() == false) { 
					Statement stmt = con.createStatement();                			
					stmt.execute("INSERT INTO \"Sch_Shop\".\"Favorite\" (customer_id, product_id) VALUES("+User_id+","+Product_id+");");
					stmt.close(); 
				}
				rs0.close();
				stmt0.close();
                               
			} finally {
				con.close();
				System.out.println("PRODUCT ID::: "+ Product_id);
			}			
		} 
		catch (Exception e) {
			throw new Exception("Error while JDBC operating: " + e.getMessage());
		}	
	}


	public void DeleteProductFav(Product product) throws Exception {	
		long Product_id;
		long User_id;
		Product_id=product.getId();
		User_id=product.getUser_id();
            
		try {			            
			try {	        
				InitialContext initialContext = new InitialContext();
					ds = (DataSource) initialContext.lookup("jdbc/local_shop");
			}	
			catch(Exception e) {	        		      
				throw new Exception("Error while Data Source initializing: " + e.getMessage());
			}

			Connection con = ds.getConnection();
      try {
				Statement stmt = con.createStatement();                			
				stmt.execute("delete from \"Sch_Shop\".\"Favorite\" WHERE product_id="+Product_id+" AND customer_id="+User_id);
				stmt.close(); 
                               
			} finally {
				con.close();
				System.out.println("PRODUCT ID::: "+ Product_id);
			}			
		} 
		catch (Exception e) {
			throw new Exception("Error while JDBC operating: " + e.getMessage());
		}	
	}
}
