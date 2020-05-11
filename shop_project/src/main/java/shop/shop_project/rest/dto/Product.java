package shop.shop_project.rest.dto;

import java.util.ArrayList;

public class Product
{
    private long id;
    private String name;
    private ArrayList<String> description;
    private String image;
    private Integer price;
    private Integer category_id;
    private Integer image_id;
    private ArrayList<String> feature_name;
    private ArrayList<String> feature_value;
    private long user_id;
    private Integer count_products;
    private int order_id;
    private int status;

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }	
    
    public ArrayList<String> getDescription() {
        return description;
    }

    public void setDescription(ArrayList<String> description) {
        this.description = description;
    }	
    
     public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }	
    
     public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }	
    
    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }	
    public Integer getImage_id() {
        return image_id;
    }

    public void setImage_id(Integer image_id) {
        this.image_id = image_id;
    }	
    
    public ArrayList<String> getFeature_name() {
        return feature_name;
    }

    public void setFeature_name(ArrayList<String> feature_name) {
        this.feature_name = feature_name;
    }	
    
    public ArrayList<String> getFeature_value() {
        return feature_value;
    }

    public void setFeature_value(ArrayList<String> feature_value) {
        this.feature_value = feature_value;
    }
    
    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }	
    
    public Integer getCount_products() {
        return count_products;
    }

    public void setCount_products(Integer count_products) {
        this.count_products = count_products;
    }	
}