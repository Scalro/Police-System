package com.police.policesystem.Models;

public class AddUser {
    int id;
    String firstName,lastName,userName,password,category;

    public AddUser(int id, String firstName, String lastName, String userName, String password, String category) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.category = category;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getCategory() {return category;}

    public void setCategory(String category) {this.category = category;}


}
