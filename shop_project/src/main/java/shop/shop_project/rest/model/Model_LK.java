package shop.shop_project.rest.model;
import rest.User;

import java.sql.*;
  
import javax.sql.DataSource;
import javax.naming.InitialContext;   

public class Model_LK implements IModel_LK {

    private DataSource ds;
    
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
    
}
