package com.abhi_ecommerce.service;

import com.abhi_ecommerce.entity.User;
import com.abhi_ecommerce.exception.UserException;
import jdk.jshell.spi.ExecutionControl;

public interface UserService{
    public User findUserById(int userid) throws UserException;
    public User findUserProfileByJwt(String jwt) throws UserException;
}
