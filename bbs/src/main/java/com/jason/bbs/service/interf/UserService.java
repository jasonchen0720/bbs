package com.jason.bbs.service.interf;


import com.jason.bbs.pojo.entity.User;

import java.util.Map;

/**
 * Created by jason on 2016/8/10.
 */
public interface UserService {

//    Boolean isOccupied(String field,String value);

    User register(User user);

    Map<String, Object> userSave(User user);

    Map<String, Object> userLogin(User user);

    Map<String, Object> validateUserEmail(String email);

    Map<String, Object> validateUserName(String username);

}
