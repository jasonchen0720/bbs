package com.jason.bbs.controller;

import com.jason.bbs.common.SysConstant;
import com.jason.bbs.common.util.JsonUtil;
import com.jason.bbs.error.BaseSystemException;
import com.jason.bbs.error.BbsErrorEnum;
import com.jason.bbs.form.ReplySendForm;
import com.jason.bbs.pojo.vo.ResponseModel;
import com.jason.bbs.pojo.vo.UserVo;
import com.jason.bbs.service.interf.ReplyService;
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
@RequestMapping(value = "/reply")
public class ReplyController {

    private static Logger log = Logger.getLogger(ReplyController.class);

    @Autowired
    private ReplyService replyService;

    @ResponseBody
    @RequestMapping(value = "sendReply")
    public ResponseModel reply(@Valid ReplySendForm replySendForm, BindingResult bindingResult, HttpServletRequest request) {
        log.info(replySendForm.getCommentId() + "---" + replySendForm.getReplyContent());
        if (bindingResult.hasErrors()) {
            log.error("用户发帖表单验证异常：" + bindingResult.getAllErrors().stream().findFirst().toString());
            return ResponseModel.error().message("回复参数异常");
        }
        UserVo replier = (UserVo) request.getSession().getAttribute(SysConstant.CURRENT_USER);
        try {
            Map<String, Object> resMap = replyService.saveReply(replySendForm.asReply(replier, replySendForm.getCommentId()));
            if ("0".equals(resMap.get(SysConstant.RESP_CODE))) {
                return ResponseModel.ok().message("回复成功！");
            } else {
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
