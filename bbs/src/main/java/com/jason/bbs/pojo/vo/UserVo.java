package com.jason.bbs.pojo.vo;

import com.jason.bbs.pojo.entity.User;

import java.util.Date;

/**
 * Created by jason on 2016/8/16.
 */
public class UserVo {

    private Long userId;

    private String email;

    private String username;

    private Date createTime;

    public UserVo(){
    }
    public UserVo(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.createTime = user.getCreateTime();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
