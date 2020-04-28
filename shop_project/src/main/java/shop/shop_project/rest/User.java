package rest;

public class User {
    private long id;
    private String first_name;
    private String second_name;
    private String third_name;
    private String country;
    private String city;
    private String street;
    private String login;
    private String password;
    private long phone_number;
    private String imail;
    private String avatar;
    private int mail_index;
    private boolean flag;

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }
    public long getPhone_number() {
        return phone_number;
    }
    
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public long getId() {
        return id;
    }
    public String getFirst_name() {
        return first_name;
    }
    public String getSecond_name() {
        return second_name;
    }
    public String getThird_name() {
        return third_name;
    }
    public String getCountry() {
        return country;
    }
    public String getCity() {
        return city;
    }
    public String getStreet() {
        return street;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public String getImail() {
        return imail;
    }
    public String getAvatar() {
        return avatar;
    }
    public int getMail_index() {
        return mail_index;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }
    public void setThird_name(String third_name) {
        this.third_name = third_name;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setImail(String imail) {
        this.imail = imail;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public void setMail_index(int mail_index) {
        this.mail_index = mail_index;
    } 
} 
