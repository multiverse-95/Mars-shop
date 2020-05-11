package shop.shop_project.rest.model;
import rest.User;
import rest.Category;
import rest.Product;

import java.sql.*;
import java.util.ArrayList;
  
import javax.sql.DataSource;
import javax.naming.InitialContext;   


public class Model_LK implements IModel_LK {

    private DataSource ds;
    
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
                        if (rs.getString("first_name")==null){user.setFirst_name("");} else{user.setFirst_name(rs.getString("first_name"));}
                        if (rs.getString("second_name")==null){user.setSecond_name("");} else{user.setSecond_name(rs.getString("second_name"));}
                        if (rs.getString("third_name")==null){user.setThird_name("");} else{user.setThird_name(rs.getString("third_name"));}
                        if (rs.getString("country")==null){user.setCountry("");} else{user.setCountry(rs.getString("country"));}
                        if (rs.getString("city")==null){user.setCity("");} else{user.setCity(rs.getString("city"));}
                        if (rs.getString("street")==null){user.setStreet("");} else{user.setStreet(rs.getString("street"));}
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
    
    public User Upd_Name(User user) throws Exception {
        try {	        
                InitialContext initialContext = new InitialContext();
                ds = (DataSource) initialContext.lookup("jdbc/local_shop");
            }   catch(Exception e) {throw new Exception("Error while Data Source initializing: " + e.getMessage());}
        
        Connection con = ds.getConnection();
        try{
            PreparedStatement st = con.prepareStatement("UPDATE \"Sch_Shop\".\"User\" set first_name = ?, second_name = ?, third_name = ? where id = ?");
            st.setString(1, user.getFirst_name());
            st.setString(2, user.getSecond_name());
            st.setString(3, user.getThird_name());
            st.setLong(4, user.getId());
            st.executeUpdate();
            st.close();
        }   catch(Exception e) {throw new Exception("Error while Upd user name: " + e.getMessage());}
        
        try {
            try {
                Statement stmt = con.createStatement();                			
                ResultSet rs = stmt.executeQuery("SELECT * FROM \"Sch_Shop\".\"User\" where id = "+user.getId());								

                while (rs.next()){
                    if (rs.getString("first_name")==null){user.setFirst_name("не указано");} else{user.setFirst_name(rs.getString("first_name"));}
                    if (rs.getString("second_name")==null){user.setSecond_name("не указано");} else{user.setSecond_name(rs.getString("second_name"));}
                    if (rs.getString("third_name")==null){user.setThird_name("не указано");} else{user.setThird_name(rs.getString("third_name"));}
                }

                rs.close();
                stmt.close();
            } finally {con.close();}
        } catch(Exception e) {throw new Exception("Error while Select user name: " + e.getMessage());}
        return user;
    }
    public User Upd_Address(User user) throws Exception {
        try {	        
                InitialContext initialContext = new InitialContext();
                ds = (DataSource) initialContext.lookup("jdbc/local_shop");
            }   catch(Exception e) {throw new Exception("Error while Data Source initializing: " + e.getMessage());}
        
        Connection con = ds.getConnection();
        try{
            PreparedStatement st = con.prepareStatement("UPDATE \"Sch_Shop\".\"User\" set country = ?, city = ?, street = ?, mail_index = ?, phone_number = ?, imail = ? where id = ?");
            st.setString(1, user.getCountry());
            st.setString(2, user.getCity());
            st.setString(3, user.getStreet());
            st.setInt(4, user.getMail_index());
            st.setLong(5, user.getPhone_number());
            st.setString(6, user.getImail());
            st.setLong(7, user.getId());
            st.executeUpdate();
            st.close();
        }   catch(Exception e) {throw new Exception("Error while Upd user name: " + e.getMessage());}
        
        try {
            try {
                Statement stmt = con.createStatement();                			
                ResultSet rs = stmt.executeQuery("SELECT * FROM \"Sch_Shop\".\"User\" where id = "+user.getId());								

                while (rs.next()){
                    if (rs.getString("mail_index")==null){user.setMail_index(000000);} else{user.setMail_index(rs.getInt("mail_index"));}
                    if (rs.getString("country")==null){user.setCountry("");} else{user.setCountry(rs.getString("country"));}
                    if (rs.getString("city")==null){user.setCity("");} else{user.setCity(rs.getString("city"));}
                    if (rs.getString("street")==null){user.setStreet("");} else{user.setStreet(rs.getString("street"));}
                    if (rs.getString("phone_number")==null){user.setPhone_number(00000000000);} else{user.setPhone_number(rs.getLong("phone_number"));}
                    user.setImail(rs.getString("imail"));
                }

                rs.close();
                stmt.close();
            } finally {con.close();}
        } catch(Exception e) {throw new Exception("Error while Select user name: " + e.getMessage());}
        return user;
    }

    public User Add_User(User user) throws Exception {
        try {	        
                InitialContext initialContext = new InitialContext();
                ds = (DataSource) initialContext.lookup("jdbc/local_shop");
            }   catch(Exception e) {throw new Exception("Error while Data Source initializing: " + e.getMessage());}
        
        Connection con = ds.getConnection();
        try{
            try{
                Statement stmt = con.createStatement();                			
                ResultSet rs = stmt.executeQuery("SELECT * FROM \"Sch_Shop\".\"User\" where login = '"+user.getLogin()+"'");
                if (rs.next()) {user.setLogin(""); rs.close(); return user;}
            } catch (Exception e) {throw new Exception("Error while Select exiting users: " + e.getMessage());}

            try{
                PreparedStatement st = con.prepareStatement("INSERT INTO \"Sch_Shop\".\"User\" ( password, login, role_number, imail ) VALUES ( ?, ?, ?, ? )");
                st.setString(1, user.getPassword());
                st.setString(2, user.getLogin());
                st.setInt(3, user.getRole_number());
                st.setString(4, user.getImail());          
                st.executeUpdate();
                st.close();
            }   catch(Exception e) {throw new Exception("Error while Upd user name: " + e.getMessage());}
        } finally {con.close();}
        return user;
    }

    public ArrayList<Product> Get_Orders(User user) throws Exception {
        ArrayList<Product> orders = new ArrayList();
        try {	        
                InitialContext initialContext = new InitialContext();
                ds = (DataSource) initialContext.lookup("jdbc/local_shop");
            }   catch(Exception e) {throw new Exception("Error while Data Source initializing: " + e.getMessage());}
        
        Connection con = ds.getConnection();
        
        try{
            try{
                Statement stmt = con.createStatement();                			
                ResultSet rs = stmt.executeQuery(
                "SELECT \"o\".\"id\", \"o\".\"status\", \"o\".\"price\", \"o\".\"number\", \"p\".\"name\", \"p\".\"category_id\", \"i\".\"path\", \"o\".\"customer_id\", \"o\".\"product_id\"\n" +
                "from \"Sch_Shop\".\"Order\" \"o\", \"Sch_Shop\".\"Products\" \"p\", \"Sch_Shop\".\"Images\" \"i\"\n" +
                "where \"o\".\"product_id\" = \"p\".\"id\" and \"p\".\"image_id\" = \"i\".\"id\" and customer_id = "+user.getId());
                
                while(rs.next()){
                    Product prod = new Product();
                    prod.setPrice(rs.getInt("price"));
                    prod.setCount_products(rs.getInt("number"));
                    prod.setId(rs.getInt("product_id"));
                    prod.setName(rs.getString("name"));
                    prod.setCategory_id(rs.getInt("category_id"));
                    prod.setOrder_id(rs.getInt("id"));
                    prod.setImage(rs.getString("path"));
                    prod.setUser_id(rs.getInt("customer_id"));
                    prod.setStatus(rs.getInt("status"));
                    orders.add(prod); 
                }
                rs.close();
            } catch (Exception e) {throw new Exception("Error while Select orders: " + e.getMessage());}
        } finally {con.close();}
       
        return orders;
    }

    @Override
    public void Order_Ok(Product prod) throws Exception {
        try {	        
                InitialContext initialContext = new InitialContext();
                ds = (DataSource) initialContext.lookup("jdbc/local_shop");
            }   catch(Exception e) {throw new Exception("Error while Data Source initializing: " + e.getMessage());}
        
        Connection con = ds.getConnection();
        try{
            try{
                PreparedStatement st = con.prepareStatement("UPDATE \"Sch_Shop\".\"Order\" set status = ? where id = ?");
                st.setInt(1, 1);
                st.setLong(2, prod.getOrder_id());
                st.executeUpdate();
                st.close();
            }   catch(Exception e) {throw new Exception("Error while Upd order's status: " + e.getMessage());}
        } finally {con.close();}
    }
    
}
