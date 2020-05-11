package shop.shop_project.rest.model.interfaces;

import java.util.ArrayList;
import shop.shop_project.rest.dto.Category;
import shop.shop_project.rest.dto.Product;
import shop.shop_project.rest.dto.User;


public interface IModel_LK {
    User Auth(User user) throws Exception;
    User Upd_Name(User user) throws Exception;
    User Upd_Address(User user) throws Exception;
    User Add_User(User user) throws Exception;
    ArrayList<Product> Get_Orders(User user) throws Exception;
    void Order_Ok(Product prod) throws Exception;
}
