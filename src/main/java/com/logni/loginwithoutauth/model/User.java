package com.logni.loginwithoutauth.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    @Column(unique = true)
    private String userEmail;
    private String password;
    public boolean isLoggedIn;
    public User(){}
    public User(String userName,String userEmail,String password){
        this.userName=userName;
        this.userEmail=userEmail;
        this.password=password;
    }
    public void setIsLoggedIn(boolean isLoggedIn){
        this.isLoggedIn =isLoggedIn;
    }
    public boolean getIsLoggedIn(){
        return isLoggedIn;
    }
    public void setId(Long id){
        this.id =id;
    }
    public Long getId(){
        return id;
    }
    public void setUserName(String userName){
        this.userName =userName;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserEmail(String userEmail){
        this.userEmail =userEmail;
    }
    public String getUserEmail(){
        return userEmail;
    }
    public void setPassword(String password){
        this.password =password;
    }
    public String getPassword(){
        return password;
    }
}
