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
  void InsertDBProduct(Product product) throws Exception;
  void UpdateDBProduct(Product product) throws Exception;
  void DeleteDBProduct(Product product) throws Exception;
  ArrayList<Product> GetProductsBasket(String id) throws Exception;
  void AddProductBasket(Product product) throws Exception;
}