package com.logni.loginwithoutauth.service;

import com.logni.loginwithoutauth.dto.ReqRes;

public interface UserServiceInterface {
    ReqRes getAllUsers();
    ReqRes createNewUser(ReqRes UserDetails);
    ReqRes updateUser(ReqRes UserDetails);
    ReqRes login(ReqRes UserDetails);
    ReqRes logout(ReqRes UserDetails);
}
