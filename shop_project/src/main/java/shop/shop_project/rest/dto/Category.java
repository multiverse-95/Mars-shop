package shop.shop_project.rest.dto;

public class Category
{
    private long id;
    private String category_name;
    private String image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getСategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }	
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }	
}