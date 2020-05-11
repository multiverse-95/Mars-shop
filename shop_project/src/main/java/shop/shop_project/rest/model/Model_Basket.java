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

public class Model_Basket implements IModel_Basket {

    private DataSource ds;
    
    public ArrayList<Product> GetProductsBasket(String id) throws Exception {	
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
                                ResultSet rs = stmt.executeQuery("SELECT product_id FROM \"Sch_Shop\".\"Basket\" WHERE customer_id="+id+";");
				//ResultSet rs = stmt.executeQuery("SELECT * FROM \"Sch_Shop\".\"Products\" where \"category_id\" = " + id);								
				while (rs.next()) {

					Product product = new Product();
					product.setId(rs.getInt("product_id"));
                                        Statement stmt0 = con.createStatement();   
                                        ResultSet rs0 = stmt0.executeQuery("SELECT * FROM \"Sch_Shop\".\"Basket\" WHERE product_id="+rs.getInt("product_id")+" AND customer_id="+id+";");
                                        while (rs0.next()) {product.setCount_products(rs0.getInt("number")); product.setPrice(rs0.getInt("price")); }
                                        rs0.close();
                                        stmt0.close();
					//product.setName(rs.getString("name"));
					//product.setPrice(rs.getInt("price"));

					Statement stmt2 = con.createStatement();
					ResultSet rs2 = stmt2.executeQuery("SELECT * FROM \"Sch_Shop\".\"Products\" WHERE id="+rs.getInt("product_id")+";");
					while (rs2.next()) { 
                                            product.setName(rs2.getString("name"));
                                            //product.setPrice(rs2.getInt("price"));
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
    
    public void AddProductBasket(Product product) throws Exception {	
            long Product_id;
            long User_id;
            Product_id=product.getId();
            User_id=product.getUser_id();
            
            	
            
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

            Integer count_prod=0;
            Integer price=0;
            try 
			{
                                Statement stmt2 = con.createStatement();
                                ResultSet rs2 = stmt2.executeQuery("SELECT * FROM \"Sch_Shop\".\"Products\" WHERE id="+Product_id+";");
                                while (rs2.next()) { price=rs2.getInt("price");}
                                
                                Statement stmt0 = con.createStatement();   
                                ResultSet rs0 = stmt0.executeQuery("SELECT number FROM \"Sch_Shop\".\"Basket\" WHERE product_id="+Product_id+" AND customer_id="+User_id+";");							
				while (rs0.next()) { count_prod=rs0.getInt("number"); }
                                rs0.close();
                                stmt0.close();
                                if (count_prod<1){
                                    Statement stmt = con.createStatement();                			
                                    stmt.execute("INSERT INTO \"Sch_Shop\".\"Basket\" (customer_id, product_id,number,price)\n" +
                                                                    " VALUES("+User_id+","+Product_id+",1,"+price+");");
                                    stmt.close();
                                } else{
                                    count_prod+=1;
                                    Statement stmt = con.createStatement();                			
                                    stmt.execute("UPDATE \"Sch_Shop\".\"Basket\" SET number = "+count_prod+", price="+price*count_prod+" WHERE product_id="+Product_id+" AND customer_id="+User_id+";");
                                    stmt.close();
                                }
                                								
				
				
				
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
    
    public void DeleteProductBasket(Product product) throws Exception {	
            long Product_id;
            long User_id;
            Product_id=product.getId();
            User_id=product.getUser_id();
            
            	
            
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
				stmt.execute("DELETE FROM \"Sch_Shop\".\"Basket\" WHERE product_id="+Product_id+" AND customer_id="+User_id+";");								
				
				
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
    
    public void DeleteAllProductBasket(Product product) throws Exception {	
            long User_id;
            User_id=product.getUser_id();
         
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
            
            try{
                Statement stmt = con.createStatement();                			
                ResultSet rs = stmt.executeQuery("SELECT * FROM \"Sch_Shop\".\"Basket\" where customer_id = "+ User_id);
                while(rs.next()){
                    PreparedStatement st = con.prepareStatement("INSERT INTO \"Sch_Shop\".\"Order\" ( customer_id, product_id, \"number\", price, status) VALUES ( ?, ?, ?, ?, ? )");
                    st.setLong(1, rs.getLong("customer_id"));
                    st.setLong(2, rs.getLong("product_id"));
                    st.setLong(3, rs.getInt("number"));
                    st.setInt(4, rs.getInt("price"));
                    st.setInt(5, 0);
                    st.executeUpdate();
                    st.close();
                }
                rs.close();
            } catch (Exception e) {throw new Exception("Error while Data Source initializing: " + e.getMessage());}

            try 
			{
                                Statement stmt = con.createStatement();                			
				stmt.execute("DELETE FROM \"Sch_Shop\".\"Basket\" WHERE customer_id="+User_id+";");								
				
				
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
