package com.jason.bbs.controller;

import com.jason.bbs.common.SysConstant;
import com.jason.bbs.common.SysEnum;
import com.jason.bbs.common.util.JsonUtil;
import com.jason.bbs.error.BaseSystemException;
import com.jason.bbs.error.BbsErrorEnum;
import com.jason.bbs.form.CommentPublishForm;
import com.jason.bbs.pojo.vo.ResponseModel;
import com.jason.bbs.pojo.vo.UserVo;
import com.jason.bbs.service.interf.CommentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by jason on 2016/8/12.
 */
@Controller
@RequestMapping(value = "/comment")
public class CommentController {

    private static Logger log = Logger.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/publish")
    public ResponseModel publishComment(@Valid CommentPublishForm commentPublishForm, BindingResult bindingResult,
                                        HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.error("评论表单验证异常：" + bindingResult.getAllErrors().stream().findFirst().toString());
            return ResponseModel.error().message("发表评论参数异常");
        }
        UserVo author = (UserVo) request.getSession().getAttribute(SysConstant.CURRENT_USER);
        try {
            Map<String, Object> resMap = commentService.saveComment(commentPublishForm.asComment(author, commentPublishForm.getIssueId()));
            if (SysEnum.ResultCode.OK.getCode().equals(resMap.get(SysConstant.RESP_CODE))) {
                return ResponseModel.ok().message("评论成功！");
            } else {
                log.error(resMap.get(SysConstant.RESP_MSG).toString());
                return ResponseModel.error().message(resMap.get(SysConstant.RESP_MSG).toString());
            }
        } catch (BaseSystemException e) {
            log.error(e.getErrorMessage());
            return ResponseModel.error().message(e.getErrorMessage());

        } catch (Exception e) {
            return ResponseModel.error().message(e.getMessage());
        }

    }

}
