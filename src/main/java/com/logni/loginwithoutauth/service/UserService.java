package com.logni.loginwithoutauth.service;

import com.logni.loginwithoutauth.dto.ReqRes;
import com.logni.loginwithoutauth.model.User;
import com.logni.loginwithoutauth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ReqRes createNewUser(ReqRes userDetails){
        ReqRes response =new ReqRes();

        try {
            User user =new User();
            var existingUser =repository.findAll().stream()
                    .filter(user1 -> user.getUserEmail().toLowerCase().contains(userDetails.getUserEmail()))
                    .findAny().orElse(null);
            if (existingUser ==null){
                user.setUserName(userDetails.getUsername());
                user.setPassword(userDetails.getPassword());
                user.setPassword(userDetails.getPassword());
                user.setIsLoggedIn(false);
                repository.save(user);
                response.setMessage("new user created");
                response.setStatusCode(200);
                response.setUser(user);
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
    public ReqRes updateUser(ReqRes userDetails){
        ReqRes response =new ReqRes();
        try {
            User user =new User();
            var existingUser =repository.findAll().stream()
                    .filter(user1 -> user.getUserEmail().toLowerCase().contains(userDetails.getUserEmail()))
                    .findAny().orElse(null);
            if (existingUser !=null) {
                user.setUserName(userDetails.getUsername());
                user.setPassword(userDetails.getPassword());
                user.setPassword(userDetails.getPassword());
                repository.save(user);
                response.setMessage("new user created");
                response.setStatusCode(200);
                response.setUser(user);
            }
        } catch (Exception e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        }
        return response;

    }
    @Override
    public ReqRes login(ReqRes userDetails){
        ReqRes response =new ReqRes();
        try {
            var findUserEmail = repository.findAll().stream()
                    .filter(user -> user.getUserEmail().toLowerCase().contains(userDetails.getUserEmail()))
                    .findAny().orElse(null);
            var findUserPassword = repository.findAll().stream()
                    .filter(user -> user.getPassword().toLowerCase().contains(userDetails.getPassword()))
                    .findAny().orElse(null);
            if(findUserEmail !=null && findUserPassword ==null){
                findUserEmail.setIsLoggedIn(true);
                response.setMessage("new successfully logged in");
                response.setStatusCode(200);

            }

        } catch (Exception e) {
            response.setStatusCode(404);
            response.setMessage("could not log you in");
        }
        return response;

    }
}
