package com.jason.bbs.pojo.bo;

import com.jason.bbs.pojo.entity.User;

/**
 * Created by jason on 2016/8/19.
 */
public class UserBo extends CommonBo{
    private User user;

    public UserBo() {
    }

    public UserBo(Boolean result, User user) {
        super(result);
        this.user = user;
    }

    public static UserBo success(User user) {
        return new UserBo(true, user);
    }

    public static UserBo fail(User user){
        return new UserBo(false,user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
