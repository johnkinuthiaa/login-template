package com.logni.loginwithoutauth.service;

import com.logni.loginwithoutauth.dto.ReqRes;
import com.logni.loginwithoutauth.model.User;
import com.logni.loginwithoutauth.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface{
    private final UserRepository repository;
    public UserService(UserRepository repository){
        this.repository=repository;
    }

    @Override
    public ReqRes getAllUsers(){
        ReqRes response =new ReqRes();
        try {
            response.setMessage("all the users present");
            response.setStatusCode(200);
            response.setUsers(repository.findAll());

        }catch (Exception e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }
        return response;

    }
    @Override
    public ReqRes createNewUser(ReqRes UserDetails){
        ReqRes response =new ReqRes();
        try {
            User user1 =new User();
            User existingUser =repository.findUserByUserEmail(UserDetails.getUserEmail());
            if (existingUser ==null){
                user1.setUserName(UserDetails.getUserName());
                user1.setUserEmail(UserDetails.getUserEmail());
                user1.setPassword(UserDetails.getPassword());

                user1.setIsLoggedIn(false);
                repository.save(user1);
                response.setMessage("new user created");
                response.setStatusCode(200);
                response.setUser(user1);
            }else{
                response.setStatusCode(400);
                response.setMessage("user already exists");
            }

        } catch (Exception e) {
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        }
        return response;
    }
    @Override
    public ReqRes updateUser(ReqRes UserDetails){
        ReqRes response =new ReqRes();
        try {
            var existingUser =repository.findAll().stream()
                    .filter(user -> user.getUserEmail().toLowerCase().contains(UserDetails.getUserEmail()))
                    .findAny().orElse(null);
            if (existingUser !=null) {
                existingUser.setUserName(UserDetails.getUserName());
                existingUser.setUserEmail(UserDetails.getUserEmail());
                existingUser.setPassword(UserDetails.getPassword());

                repository.save(existingUser);
                response.setMessage("new user created");
                response.setStatusCode(200);
                response.setUser(existingUser);
            }
        } catch (Exception e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        }
        return response;

    }
    @Override
    public ReqRes login(ReqRes UserDetails){
        ReqRes response =new ReqRes();
        User user = repository.findUserByUserEmail(UserDetails.getUserEmail());

        if (user !=null &&UserDetails.getPassword().equals(user.getPassword())) {
            if(user.isLoggedIn){
                throw new RuntimeException("user was already logged in");
            }
            response.setStatusCode(200);
            response.setMessage("logged in successfully");
            user.setIsLoggedIn(true);
            response.setUser(user );

        }else{
            response.setStatusCode(500);
            response.setMessage("user not logged in successfully ");
        }
        return response;
    }
    @Override
    public ReqRes logout(ReqRes UserDetails){
        ReqRes response =new ReqRes();
        User user = repository.findUserByUserEmail(UserDetails.getUserEmail());
        if(user !=null && user.isLoggedIn){
            user.setIsLoggedIn(false);
            response.setMessage("user logged out successfully");
            response.setStatusCode(200);
        }
        return response;
    }
}
