package com.jason.bbs.pojo.entity;

import javax.persistence.*;
import java.util.Date;
import javax.persistence.Column;

/**
 * Created by jason on 2016/8/10.
 */
@Entity
@Table(name = "T_USER")
public class User {
    /**
     * id
     */
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;
    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 盐
     */
    @Column(name = "salt")
    private String salt;

    @Column(name = "create_time")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createTime = new Date();

    public User() {
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
