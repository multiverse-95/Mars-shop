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


public class Model implements IModel  {


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
                                ResultSet rs = stmt.executeQuery("SELECT product_id FROM \"Sch_Shop\".\"Basket\" WHERE customer_id=1;");
				//ResultSet rs = stmt.executeQuery("SELECT * FROM \"Sch_Shop\".\"Products\" where \"category_id\" = " + id);								
				while (rs.next()) {

					Product product = new Product();
					product.setId(rs.getInt("product_id"));
					//product.setName(rs.getString("name"));
					//product.setPrice(rs.getInt("price"));

					Statement stmt2 = con.createStatement();
					ResultSet rs2 = stmt2.executeQuery("SELECT * FROM \"Sch_Shop\".\"Products\" WHERE id="+rs.getInt("product_id")+";");
					while (rs2.next()) { 
                                            product.setName(rs2.getString("name"));
                                            product.setPrice(rs2.getInt("price"));
                                            //product.setImage(rs2.getString("path")); 
                                            
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


            try 
			{
                                Statement stmt = con.createStatement();                			
				stmt.execute("INSERT INTO \"Sch_Shop\".\"Basket\" (customer_id, product_id,number,price)\n" +
                                                                    " VALUES("+User_id+","+Product_id+",1,12323);");								
				
				
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

	public User Auth(User user) throws Exception{
            try {	        
                InitialContext initialContext = new InitialContext();
                ds = (DataSource) initialContext.lookup("jdbc/local_shop");
            }	
            catch(Exception e) {	        		      
                throw new Exception("Error while Data Source initializing: " + e.getMessage());
            }

            try{
                Connection con = ds.getConnection();
                try 
                {
                    Statement stmt = con.createStatement();                			
                    ResultSet rs = stmt.executeQuery("SELECT * FROM \"Sch_Shop\".\"User\" where login = '"+user.getLogin()+"' and password = '"+user.getPassword()+"'" );								
                    
                    if (rs.next()){
                        user.setFlag(true);
                        if (rs.getString("mail_index")==null){user.setMail_index(000000);} else{user.setMail_index(rs.getInt("mail_index"));}
                        if (rs.getString("avatar")==null){user.setAvatar("images/user.png");} else{user.setAvatar(rs.getString("avatar"));}
                        if (rs.getString("first_name")==null){user.setFirst_name("не указано");} else{user.setFirst_name(rs.getString("first_name"));}
                        if (rs.getString("second_name")==null){user.setSecond_name("не указано");} else{user.setSecond_name(rs.getString("second_name"));}
                        if (rs.getString("third_name")==null){user.setThird_name("не указано");} else{user.setThird_name(rs.getString("third_name"));}
                        if (rs.getString("country")==null){user.setCountry("не указано");} else{user.setCountry(rs.getString("country"));}
                        if (rs.getString("city")==null){user.setCity("не указано");} else{user.setCity(rs.getString("city"));}
                        if (rs.getString("street")==null){user.setStreet("не указано");} else{user.setStreet(rs.getString("street"));}
                        if (rs.getString("phone_number")==null){user.setPhone_number(00000000000);} else{user.setPhone_number(rs.getLong("phone_number"));}
                        user.setId(rs.getInt("id"));
                        user.setImail(rs.getString("imail")); 
                        user.setRole_number(rs.getInt("role_number"));
                    } else{
                        user.setFlag(false);
                    }
                    rs.close();
                    stmt.close();
                } finally {
                    con.close();
                }
            } catch(Exception e) {	        		      
                throw new Exception("Error while Auth: " + e.getMessage());
            }
            return user;
        }
        
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
