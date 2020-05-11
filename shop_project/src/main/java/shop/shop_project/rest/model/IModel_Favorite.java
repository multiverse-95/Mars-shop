
package shop.shop_project.rest.model;
import rest.Product;
import rest.Category;
import rest.User;
import java.util.ArrayList;
import java.util.List;

public interface IModel_Favorite {
    ArrayList<Product> GetProductsFavorite(String id) throws Exception;
    void AddProductFav(Product product) throws Exception;
    void DeleteProductFav(Product product) throws Exception;
    
}
