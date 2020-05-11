
package shop.shop_project.rest.model;
import rest.Product;
import rest.Category;
import rest.User;
import java.util.ArrayList;
import java.util.List;

public interface IModel_Basket {
    ArrayList<Product> GetProductsBasket(String id) throws Exception;
    void AddProductBasket(Product product) throws Exception;
    void DeleteProductBasket(Product product) throws Exception;
    void DeleteAllProductBasket(Product product) throws Exception;
    
}
