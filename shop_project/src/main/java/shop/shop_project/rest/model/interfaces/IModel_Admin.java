
package shop.shop_project.rest.model.interfaces;

import shop.shop_project.rest.dto.Product;
import java.util.ArrayList;
import java.util.List;


public interface IModel_Admin {
    void InsertDBProduct(Product product) throws Exception;
    void UpdateDBProduct(Product product) throws Exception;
    void DeleteDBProduct(Product product) throws Exception;
    
}
