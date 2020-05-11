
package shop.shop_project.rest.model.interfaces;
import shop.shop_project.rest.dto.Product;
import shop.shop_project.rest.dto.Category;
import shop.shop_project.rest.dto.User;
import java.util.ArrayList;
import java.util.List;

public interface IModel_Favorite {
    ArrayList<Product> GetProductsFavorite(String id) throws Exception;
    void AddProductFav(Product product) throws Exception;
    void DeleteProductFav(Product product) throws Exception;
    
}
