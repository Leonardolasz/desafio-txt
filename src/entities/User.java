package entities;

import java.util.List;

public class User {

    private String name;
    private String email;
    private Integer age;
    private  Double height;
    private List<String> users;



    public User() {
    }

    public User(String name, String email, Integer age, Double height) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.height = height;
    }

    public List<String> getUsers() {
        return users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "entities.User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
