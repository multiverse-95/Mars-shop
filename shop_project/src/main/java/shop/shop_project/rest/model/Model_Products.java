package rest.model;

import rest.model.entity.EStudent;
import rest.Product;
import rest.Category;
import rest.User;


import java.util.ArrayList;
import java.util.List;

//JDBC (Begin)
import java.sql.*;
//JDBC (End)

//Pool (Begin)
import javax.annotation.Resource;   
import javax.sql.DataSource;
   
import javax.naming.InitialContext;   
//Pool (End)   


//JPA (Begin)
import javax.persistence.*;
import javax.transaction.*;
//JPA (End)   


public class Model_Products implements IModel_Products  {


  private DataSource ds; 

	public ArrayList<Category> GetCategoryes() throws Exception {	
		ArrayList<Category> categoryes = new ArrayList<>();			
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
				ResultSet rs = stmt.executeQuery("SELECT * FROM \"Sch_Shop\".\"Categoryes\"");								
				while (rs.next()) {

					Category category = new Category();
					category.setId(rs.getInt("id"));
					category.setCategory_name(rs.getString("category_name"));

					Statement stmt2 = con.createStatement();
					ResultSet rs2 = stmt2.executeQuery("SELECT \"path\" FROM \"Sch_Shop\".\"Images\" where \"id\" = " + rs.getString("image_id"));
					while (rs2.next()) { category.setImage(rs2.getString("path")); }
					rs2.close();
		
					categoryes.add(category);
	
				}
				rs.close();
				stmt.close();
				return categoryes;
			} finally {
				con.close();
			}			
		} 
		catch (Exception e) {
			throw new Exception("Error while JDBC operating: " + e.getMessage());
		}
	}

	public ArrayList<Product> GetCategory(String id) throws Exception {	
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
				ResultSet rs = stmt.executeQuery("SELECT * FROM \"Sch_Shop\".\"Products\" where \"category_id\" = " + id);								
				while (rs.next()) {

					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setPrice(rs.getInt("price"));

					Statement stmt2 = con.createStatement();
					ResultSet rs2 = stmt2.executeQuery("SELECT \"path\" FROM \"Sch_Shop\".\"Images\" where \"id\" = " + rs.getString("image_id"));
					while (rs2.next()) { product.setImage(rs2.getString("path")); }
					rs2.close();
					stmt2.close();

					ArrayList<String> desc = new ArrayList<>();
					Statement stmt3 = con.createStatement();
					ResultSet rs3 = stmt3.executeQuery("SELECT * FROM \"Sch_Shop\".\"Features\" where \"product_id\" = " + rs.getInt("id") + " order by \"feature_name\"");
					while (rs3.next()) { 
						desc.add(rs3.getString("feature_name")); 
						desc.add(rs3.getString("feature_value")); 
					}
					rs3.close();
					stmt3.close();
					
					product.setDescription(desc);
					
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

	public Product GetProduct(String id) throws Exception {	
		Product product = new Product();
                ArrayList<String> feature_name=new ArrayList<>();
                ArrayList<String> feature_value=new ArrayList<>();
                
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
				ResultSet rs = stmt.executeQuery("SELECT * FROM \"Sch_Shop\".\"Products\" where \"id\" = " + id);								
				while (rs.next()) {

					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setPrice(rs.getInt("price"));

					Statement stmt2 = con.createStatement();
					ResultSet rs2 = stmt2.executeQuery("SELECT \"path\" FROM \"Sch_Shop\".\"Images\" where \"id\" = " + rs.getString("image_id"));
					while (rs2.next()) { product.setImage(rs2.getString("path")); }
					rs2.close();
					stmt2.close();

					ArrayList<String> desc = new ArrayList<>();
					Statement stmt3 = con.createStatement();
					ResultSet rs3 = stmt3.executeQuery("SELECT * FROM \"Sch_Shop\".\"Features\" where \"product_id\" = " + rs.getInt("id") + " order by \"feature_name\"");
					while (rs3.next()) { 
						desc.add(rs3.getString("feature_name")); 
						desc.add(rs3.getString("feature_value")); 
                                                feature_name.add(rs3.getString("feature_name"));
                                                feature_value.add(rs3.getString("feature_value"));
					}
					rs3.close();
					stmt3.close();

					product.setDescription(desc);
                                        product.setFeature_name(feature_name);
                                        product.setFeature_value(feature_value);

				}
				rs.close();
				stmt.close();

				return product;
			} finally {
				con.close();
			}			
		} 
		catch (Exception e) {
			throw new Exception("Error while JDBC operating: " + e.getMessage());
		}
	}
        

        
        
        public ArrayList<Product> SearchProducts(String search) throws Exception {	
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
                        
                        if (search.equals("") || search.equals(" ")){
                            search="not_found404";
                        }
		
			Connection con = ds.getConnection();
			try 
			{
				Statement stmt = con.createStatement();                			
				ResultSet rs = stmt.executeQuery("SELECT * FROM \"Sch_Shop\".\"Products\" where lower(\"name\") like lower('%"+search+"%');");								
				while (rs.next()) {

					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setPrice(rs.getInt("price"));
                                        product.setCategory_id(rs.getInt("category_id"));

					Statement stmt2 = con.createStatement();
					ResultSet rs2 = stmt2.executeQuery("SELECT \"path\" FROM \"Sch_Shop\".\"Images\" where \"id\" = " + rs.getString("image_id"));
					while (rs2.next()) { product.setImage(rs2.getString("path")); }
					rs2.close();
					stmt2.close();

					ArrayList<String> desc = new ArrayList<>();
					Statement stmt3 = con.createStatement();
					ResultSet rs3 = stmt3.executeQuery("SELECT * FROM \"Sch_Shop\".\"Features\" where \"product_id\" = " + rs.getInt("id") + " order by \"feature_name\"");
					while (rs3.next()) { 
						desc.add(rs3.getString("feature_name")); 
						desc.add(rs3.getString("feature_value")); 
					}
					rs3.close();
					stmt3.close();
					
					product.setDescription(desc);
					
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

	
        
        
}
