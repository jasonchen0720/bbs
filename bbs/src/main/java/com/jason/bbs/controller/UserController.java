package com.jason.bbs.controller;

import com.jason.bbs.form.UserLoginForm;
import com.jason.bbs.pojo.bo.CommonBo;
import com.jason.bbs.exception.BaseSystemException;
import com.jason.bbs.pojo.bo.UserBo;
import com.jason.bbs.pojo.vo.ResponseModel;
import com.jason.bbs.pojo.vo.UserVo;
import com.jason.bbs.service.interf.UserService;
import com.jason.bbs.form.UserRegisterForm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.jason.bbs.common.SysConstant;

/**
 * Created by jason on 2016/8/10.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 进入主页
     */
    @RequestMapping(value = "/toMain")
    public String toMainPage() {
        return "main";
    }

    /**
     * 进入登录页面
     */
    @RequestMapping(value = "/toLogin")
    public String toLogin(HttpServletRequest request) {
        request.getSession().removeAttribute(SysConstant.CURRENT_USER);
        return "user/login";
    }

    /**
     * 进入注册界面
     */
    @RequestMapping(value = "/toRegister")
    public String toRegister(HttpServletRequest request) {
        request.getSession().removeAttribute(SysConstant.CURRENT_USER);
        return "user/register";
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(SysConstant.CURRENT_USER);
        return "welcome";
    }

    /**
     * 显示个人信息
     */
    @RequestMapping(value = "/userInfo")
    public String userInfo(Model model, HttpServletRequest request) {

        UserVo userVo = (UserVo) request.getSession().getAttribute(SysConstant.CURRENT_USER);
        if (null == userVo) {
            model.addAttribute("data", new ResponseModel(-1, "未登录或登录超时"));
            return "user/login";
        } else {
            model.addAttribute("user", userVo);
        }
        return "user/userInfo";
    }

    /**
     * 处理登录操作
     */
    @RequestMapping(value = "/login")
    public String login(@Valid UserLoginForm userLoginForm, BindingResult bindingResult, Model model, HttpServletRequest req) {
        if (bindingResult.hasErrors()) {
            logger.error("用户登录参数校验异常:" + bindingResult.getAllErrors().stream().findFirst().toString());
            model.addAttribute("data", ResponseModel.error().message("用户登录表单验证错误"));
            return "user/login";
        }
        try {
            UserBo userLoginBo = userService.userLogin(userLoginForm.asUser());
            if (userLoginBo.isSuccess()) {
                logger.info("登录成功");
                UserVo userVo = new UserVo(userLoginBo.getUser());
                req.getSession().setMaxInactiveInterval(1200);
                req.getSession().setAttribute(SysConstant.CURRENT_USER, userVo);
                return "main";
            } else {
                logger.error(userLoginBo.getMessage());
                model.addAttribute("data", ResponseModel.error().message(userLoginBo.getMessage()));
                return "user/login";
            }
        } catch (BaseSystemException e) {
            logger.error(e.getErrorMessage());
            model.addAttribute("data", ResponseModel.error().message(e.getErrorMessage()));
            return "user/login";
        } catch (Exception e) {
            model.addAttribute("data", ResponseModel.error().message(e.getMessage()));
            return "user/login";
        }
    }

    /**
     * 处理注册操作
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseModel register(@Valid UserRegisterForm userRegisterForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("用户注册表单验证异常：" + bindingResult.getAllErrors().stream().findFirst().toString());
            return ResponseModel.error().message("用户注册表单验证错误");
        }
        try {
            CommonBo commonBo = userService.userSave(userRegisterForm.asUser());
            if (commonBo.isSuccess()) {
                logger.info("注册成功");
                return ResponseModel.ok().message(commonBo.getMessage());
            } else {
                logger.error("注册失败");
                return ResponseModel.ok().message(commonBo.getMessage());
            }
        } catch (BaseSystemException e) {
            logger.error(e.getErrorMessage());
            return ResponseModel.error().message(e.getErrorMessage());
        } catch (Exception e) {
            return ResponseModel.error().message(e.getMessage());
        }
    }

    /**
     * 邮箱校验
     */
    @ResponseBody
    @RequestMapping(value = "/validateEmail", produces = "application/json;charset=UTF-8")
    public ResponseModel validateUserEmail(@RequestParam(value = "email", defaultValue = "") String email) {
        try {
            if (userService.validateUserEmail(email)) {
                return ResponseModel.ok();
            } else {
                return ResponseModel.error().message("邮箱已经被注册");
            }
        } catch (BaseSystemException e) {
            logger.error(e.getErrorMessage());
            return ResponseModel.error().message(e.getErrorMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseModel.error().message(e.getMessage());
        }
    }

    /**
     * 用户名校验
     */
    @ResponseBody
    @RequestMapping(value = "/validateName", produces = "application/json;charset=UTF-8")
    public ResponseModel validateUserName(@RequestParam(value = "username", defaultValue = "") String username) {
        try {
            if (userService.validateUserName(username)) {
                return ResponseModel.ok();
            } else {
                return ResponseModel.error().message("昵称已经被使用");
            }
        } catch (BaseSystemException e) {
            logger.error(e.getErrorMessage());
            return ResponseModel.error().message(e.getErrorMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseModel.error().message(e.getMessage());
        }
    }
}
