package com.jason.bbs.service.interf;


import com.jason.bbs.pojo.bo.CommonBo;
import com.jason.bbs.pojo.bo.UserBo;
import com.jason.bbs.pojo.entity.User;

import java.util.Map;

/**
 * Created by jason on 2016/8/10.
 */
public interface UserService {

    CommonBo userSave(User user);

    UserBo userLogin(User user);

    Boolean validateUserEmail(String email);

    Boolean validateUserName(String username);

}
