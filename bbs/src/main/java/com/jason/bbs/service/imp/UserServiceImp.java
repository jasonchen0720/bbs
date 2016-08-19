package com.jason.bbs.service.imp;

import com.jason.bbs.common.SysConstant;
import com.jason.bbs.common.SysEnum;
import com.jason.bbs.common.util.MD5Util;
import com.jason.bbs.dao.interf.UserDao;
import com.jason.bbs.pojo.entity.User;
import com.jason.bbs.error.BaseSystemException;
import com.jason.bbs.error.BbsErrorEnum;
import com.jason.bbs.service.interf.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by jason on 2016/8/10.
 */
@Service
public class UserServiceImp implements UserService {

    private static Logger log = Logger.getLogger(UserServiceImp.class);

    @Autowired
    private UserDao userDao;

    private Boolean isOccupied(String field, String value) {
        if (value == null || value.isEmpty() || field == null || field.isEmpty())
            throw new BaseSystemException(BbsErrorEnum.BBS_PARAM_NULL);
        return userDao.getByField(User.class, field, value) != null;
    }

    @Override
    @Transactional
    public User register(User user) {
        if (user == null)
            throw new BaseSystemException(BbsErrorEnum.BBS_PARAM_NULL);
        if (this.isOccupied("email", user.getEmail()))
            throw new BaseSystemException(BbsErrorEnum.BBS_EMAIL_EXISTED);
        if (this.isOccupied("username", user.getUsername()))
            throw new BaseSystemException(BbsErrorEnum.BBS_NAME_EXISTED);
        user.setSalt(UUID.randomUUID().toString());
        user.setPassword(MD5Util.GetMD5Code(user.getSalt() + user.getPassword()));
        return (User) userDao.save(user);
    }

    @Transactional
    public Map<String, Object> userSave(User user) {
        if (user == null) {
            throw new BaseSystemException(BbsErrorEnum.BBS_PARAM_NULL);
        }
        if (this.isOccupied("email", user.getEmail())) {
            throw new BaseSystemException(BbsErrorEnum.BBS_EMAIL_EXISTED);
        }
        if (this.isOccupied("username", user.getUsername())) {
            throw new BaseSystemException(BbsErrorEnum.BBS_NAME_EXISTED);
        }
        Map<String, Object> map = new HashMap<>();
        user = (User) userDao.save(user);
        if (user != null) {
            map.put(SysConstant.RESP_CODE, SysEnum.ResultCode.OK.getCode());
        } else {
            log.error("保存用户信息异常");
            map.put(SysConstant.RESP_CODE, SysEnum.ResultCode.ERROR.getCode());
        }
        return map;
    }

    @Transactional
    public Map<String, Object> userLogin(User user) {
        if (user == null) {
            throw new BaseSystemException(BbsErrorEnum.BBS_PARAM_NULL);
        }
        Map<String, Object> map = new HashMap<>();
        User userQuery = (User) userDao.getByField(User.class, "email", user.getEmail());
        if (userQuery != null) {
            log.info(userQuery.getSalt());
            if (MD5Util.GetMD5Code(user.getPassword() + userQuery.getSalt()).equals(userQuery.getPassword())) {
                map.put(SysConstant.RESP_CODE, SysEnum.ResultCode.OK.getCode());
                map.put(SysConstant.RESP_DATA, userQuery);
            } else {
                map.put(SysConstant.RESP_CODE, SysEnum.ResultCode.ERROR.getCode());
                map.put(SysConstant.RESP_MSG, SysEnum.ResultMsg.USER_PASSWORD_ERROR.getMsg());
            }
        } else {
            log.error("用户不存在");
            map.put(SysConstant.RESP_CODE, SysEnum.ResultCode.ERROR.getCode());
            map.put(SysConstant.RESP_MSG, SysEnum.ResultMsg.USER_NOT_FOUND.getMsg());
        }
        return map;
    }

    @Transactional
    public Map<String, Object> validateUserEmail(String email) {
        if (email == null || email.isEmpty())
            throw new BaseSystemException(BbsErrorEnum.BBS_PARAM_NULL);
        Map<String, Object> map = new HashMap<>();
        User user = (User) userDao.getByField(User.class, "email", email);
        if (user != null) {
            log.info("邮箱已经被注册！");
            map.put(SysConstant.RESP_CODE, SysEnum.ResultCode.ERROR.getCode());
        } else {
            map.put(SysConstant.RESP_CODE, SysEnum.ResultCode.OK.getCode());
        }
        return map;
    }

    @Transactional
    public Map<String, Object> validateUserName(String username) {
        log.info(username);
        if (username == null || username.isEmpty())
            throw new BaseSystemException(BbsErrorEnum.BBS_PARAM_NULL);
        Map<String, Object> map = new HashMap<>();
        User user = (User) userDao.getByField(User.class, "username", username);
        if (user != null) {
            log.info("昵称已经被使用！");
            map.put(SysConstant.RESP_CODE, SysEnum.ResultCode.ERROR.getCode());
            map.put(SysConstant.RESP_MSG, "邮箱已经被注册");
        } else {
            map.put(SysConstant.RESP_CODE, SysEnum.ResultCode.OK.getCode());
        }
        return map;
    }
}
