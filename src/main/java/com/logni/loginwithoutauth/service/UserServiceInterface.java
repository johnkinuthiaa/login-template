package com.logni.loginwithoutauth.service;

import com.logni.loginwithoutauth.dto.ReqRes;

import java.util.List;

public interface UserServiceInterface {
    ReqRes getAllUsers();
    ReqRes createNewUser(ReqRes userDetails);
    ReqRes updateUser(ReqRes userDetails);
    ReqRes login(ReqRes userDetails);
}
