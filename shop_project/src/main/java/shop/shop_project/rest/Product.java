package rest;

import java.util.ArrayList;

public class Product
{
    private long id;
    private String name;
    private ArrayList<String> description;
    private String image;
    private Integer price;

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
}