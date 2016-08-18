package com.jason.bbs.form;


import com.jason.bbs.pojo.entity.User;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by jason on 2016/8/13.
 */
public class UserLoginForm {

    @NotBlank(message = "{bbs.user.email.null}")
    @Pattern(regexp = "^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$", message = "{bbs.register.email.format.error}")
    private String email;

    @Size(min = 8, max = 20, message = "{bbs.user.password.size.error}")
    @NotBlank(message = "{bbs.user.password.null}")
    private String password;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User asUser(){

        User user = new User();

        user.setEmail(this.email);

        user.setPassword(this.password);

        return user;

    }
}
