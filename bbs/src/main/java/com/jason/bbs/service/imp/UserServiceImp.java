package com.jason.bbs.service.imp;

import com.jason.bbs.common.SysEnum.ResultMsg;
import com.jason.bbs.common.util.MD5Util;
import com.jason.bbs.dao.interf.UserDao;
import com.jason.bbs.pojo.bo.CommonBo;
import com.jason.bbs.pojo.bo.UserBo;
import com.jason.bbs.pojo.entity.User;
import com.jason.bbs.exception.BaseSystemException;
import com.jason.bbs.exception.BbsErrorEnum;
import com.jason.bbs.service.interf.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public CommonBo userSave(User user) {
        if (user == null) {
            throw new BaseSystemException(BbsErrorEnum.BBS_PARAM_NULL);
        }
        if (this.isOccupied("email", user.getEmail())) {
            throw new BaseSystemException(BbsErrorEnum.BBS_EMAIL_EXISTED);
        }
        if (this.isOccupied("username", user.getUsername())) {
            throw new BaseSystemException(BbsErrorEnum.BBS_NAME_EXISTED);
        }
        user = (User) userDao.save(user);
        if (user != null) {
            return CommonBo.success().message(ResultMsg.USER_REGISTER_OK.getMsg());
        } else {
            return CommonBo.fail().message(ResultMsg.USER_REGISTER_ERROR.getMsg());
        }
    }

    @Transactional
    public UserBo userLogin(User user) {
        if (user == null) {
            throw new BaseSystemException(BbsErrorEnum.BBS_PARAM_NULL);
        }
        User userQuery = (User) userDao.getByField(User.class, "email", user.getEmail());
        if (userQuery != null) {
            log.info(userQuery.getSalt());
            if (MD5Util.GetMD5Code(user.getPassword() + userQuery.getSalt()).equals(userQuery.getPassword())) {
                return (UserBo)UserBo.success(userQuery).message(ResultMsg.USER_LOGIN_OK.getMsg());
            } else {
                return (UserBo)UserBo.fail(userQuery).message(ResultMsg.USER_PASSWORD_ERROR.getMsg());
            }
        } else {
            log.error("用户不存在");
            return (UserBo)UserBo.fail(userQuery).message(ResultMsg.USER_NOT_FOUND.getMsg());
        }
    }

    @Transactional
    public Boolean validateUserEmail(String email) {
        if (email == null || email.isEmpty())
            throw new BaseSystemException(BbsErrorEnum.BBS_PARAM_NULL);
        User user = (User) userDao.getByField(User.class, "email", email);
        if (user != null) {
            log.info("邮箱已经被注册！");
            return false;
        } else {
           return true;
        }
    }

    @Transactional
    public Boolean validateUserName(String username) {
        log.info(username);
        if (username == null || username.isEmpty())
            throw new BaseSystemException(BbsErrorEnum.BBS_PARAM_NULL);
        User user = (User) userDao.getByField(User.class, "username", username);
        if (user != null) {
            log.info("昵称已经被使用！");
            return false;
        } else {
            return true;
        }
    }


   /* public static void main(String[] args) {

        User user = new User();

        user.setUsername("chenjie");

        UserLoginBo userLoginBo=(UserLoginBo)UserLoginBo.success(user).message("登录成功！");

        System.out.println(userLoginBo.getUser().getUsername());

    }*/
}
