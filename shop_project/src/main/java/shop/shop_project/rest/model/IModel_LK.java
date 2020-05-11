package shop.shop_project.rest.model;

import java.util.ArrayList;
import rest.Category;
import rest.Product;
import rest.User;


public interface IModel_LK {
    User Auth(User user) throws Exception;
    User Upd_Name(User user) throws Exception;
    User Upd_Address(User user) throws Exception;
    User Add_User(User user) throws Exception;
    ArrayList<Product> Get_Orders(User user) throws Exception;
    void Order_Ok(Product prod) throws Exception;
}
