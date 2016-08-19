package com.jason.bbs.form;

import com.jason.bbs.common.util.MD5Util;
import com.jason.bbs.pojo.entity.User;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * Created by jason on 2016/8/10.
 */
public class UserRegisterForm {
    @NotBlank(message = "{bbs.user.email.null}")
    @Pattern(regexp = "^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$", message = "{bbs.register.email.format.error}")
    @Email
    private String email;

    @Size(min = 1, max = 10, message = "{bbs.user.username.size.error}")
    @NotBlank(message = "{bbs.user.username.null}")
    private String username;

    @Size(min = 8, max = 20, message = "{bbs.user.password.size.error}")
    @NotBlank(message = "{bbs.user.password.null}")
    private String password;

    @Size(min = 8, max = 20, message = "{bbs.user.password.size.error}")
    @NotBlank(message = "{bbs.user.confirmedPassword.null}")
    private String confirmedPassword;

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

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }


    public User asUser() {
        User user = new User();
        String salt = UUID.randomUUID().toString().replace("-","");
        user.setEmail(this.email);
        user.setUsername(this.username);
        user.setSalt(salt);
        user.setPassword(MD5Util.GetMD5Code(this.password+salt));
        return user;
    }

}
