package com.jason.bbs.controller;

import com.jason.bbs.common.SysConstant;
import com.jason.bbs.exception.BaseSystemException;
import com.jason.bbs.pojo.bo.CommonBo;
import com.jason.bbs.pojo.bo.IssueBo;
import com.jason.bbs.form.IssuePublishForm;
import com.jason.bbs.pojo.vo.ResponseModel;
import com.jason.bbs.pojo.vo.UserVo;
import com.jason.bbs.service.interf.IssueService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by jason on 2016/8/11.
 */
@Controller
@RequestMapping(value = "/issue")
public class IssueController {
    private static Logger log = Logger.getLogger(IssueController.class);

    @Autowired
    private IssueService issueService;


    @ResponseBody
    @RequestMapping(value = "/publish", method = RequestMethod.GET)
    public ResponseModel publishIssue(@Valid IssuePublishForm issuePublishForm, BindingResult bindingResult, HttpServletRequest request) {
        log.info("开始发帖子了");
        if (bindingResult.hasErrors()) {
            log.error("用户发帖表单验证异常：" + bindingResult.getAllErrors().stream().findFirst().toString());
            return ResponseModel.error().message("用户发帖表单验证异常");
        }
        UserVo creator = (UserVo) request.getSession().getAttribute(SysConstant.CURRENT_USER);
        CommonBo commonBo = issueService.saveIssue(issuePublishForm.asIssue(creator));
        if (commonBo.isSuccess()) {
            return ResponseModel.ok().message("发帖成功");
        } else {
            return ResponseModel.error().message("发帖失败");
        }
    }

    @RequestMapping(value = "/issues/{columnBelong}")
    public String showIssues(@PathVariable("columnBelong") String columnBelong, Model model) {
        try {
           IssueBo issueBo = issueService.getIssueList(columnBelong);
            if (issueBo.isSuccess()) {
                log.info("加载主题列表成功");
                model.addAttribute("columnBelong", columnBelong);
                model.addAttribute("issues", issueBo.getIssues());
                return "issue/issueList";
            } else {
                log.info("加载主题列表异常");
                model.addAttribute("data", ResponseModel.error().message("加载主题列表异常"));
                return "result/error";
            }
        } catch (BaseSystemException e) {
            log.error(e.getErrorMessage());
            model.addAttribute("data", ResponseModel.error().message(e.getErrorMessage()));
            return "result/error";
        } catch (Exception e) {
            model.addAttribute("data", ResponseModel.error().message(e.getMessage()));
            return "result/error";
        }
    }


    @RequestMapping(value = "/issueDetail/{issueId}", method = RequestMethod.GET)
    public String showIssueDetail(@PathVariable("issueId") Long issueId, Model model) {
        log.info("显示帖子详情");
        try {
            IssueBo issueBo = issueService.getIssue(issueId);
            if (issueBo.isSuccess()) {
                model.addAttribute("issue", issueBo.getIssues().get(0));
                return "issue/issueDetail";
            } else {
                model.addAttribute("data", ResponseModel.error().message(issueBo.getMessage()));
                return "result/error";
            }
        } catch (BaseSystemException e) {
            log.error(e.getErrorMessage());
            model.addAttribute("data", ResponseModel.error().message(e.getErrorMessage()));
            return "result/error";
        } catch (Exception e) {
            model.addAttribute("data", ResponseModel.error().message(e.getMessage()));
            return "result/error";
        }
    }
}

