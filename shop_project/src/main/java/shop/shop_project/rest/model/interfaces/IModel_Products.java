package shop.shop_project.rest.model.interfaces;

import shop.shop_project.rest.dto.Product;
import shop.shop_project.rest.dto.Category;
import shop.shop_project.rest.dto.User;
import java.util.ArrayList;
import java.util.List;

public interface IModel_Products  {
  ArrayList<Category> GetCategoryes() throws Exception;
  ArrayList<Product> GetCategory(String id) throws Exception;
  Product GetProduct(String id) throws Exception;
  ArrayList<Product> SearchProducts(String search) throws Exception;
  
}