package com.logni.loginwithoutauth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.logni.loginwithoutauth.model.User;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqRes {
    private int statusCode;
    private String message;
    private String error;
    private String username;
    private String userEmail;
    private String password;
    private List<User> users;
    private User user;
}
