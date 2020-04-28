package rest.model;

import rest.Product;
import rest.Category;
import rest.User;
import java.util.ArrayList;
import java.util.List;

public interface IModel  {
  ArrayList<Category> GetCategoryes() throws Exception;
  ArrayList<Product> GetCategory(String id) throws Exception;
  Product GetProduct(String id) throws Exception;
  ArrayList<Product> SearchProducts(String search) throws Exception;
  User Auth(User user) throws Exception;
}