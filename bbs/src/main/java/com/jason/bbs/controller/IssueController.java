package com.jason.bbs.controller;

import com.jason.bbs.common.SysConstant;
import com.jason.bbs.common.SysEnum;
import com.jason.bbs.error.BaseSystemException;
import com.jason.bbs.error.BbsErrorEnum;
import com.jason.bbs.pojo.entity.Issue;
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
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

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
        Map<String, Object> resMap = issueService.saveIssue(issuePublishForm.asIssue(creator));
        if (SysEnum.ResultCode.ERROR.getCode().equals(resMap.get(SysConstant.RESP_CODE))) {
            return ResponseModel.error().message("发帖失败");
        } else {
            return ResponseModel.ok().message("发帖成功");
        }
    }

    @RequestMapping(value = "/issues/{columnBelong}")
    public String showIssues(@PathVariable("columnBelong") String columnBelong, Model model) {
        try {
            Map<String, Object> resMap = issueService.getIssueList(columnBelong);
            if (SysEnum.ResultCode.ERROR.getCode().equals(resMap.get(SysConstant.RESP_CODE))) {
                log.info("加载主题列表异常");
                model.addAttribute("data", ResponseModel.error().message("加载主题列表异常"));
                return "result/error";
            } else {
                log.info("加载主题列表成功");
                List<Issue> issues = (List<Issue>) resMap.get(SysConstant.RESP_DATA);
                model.addAttribute("columnBelong", columnBelong);
                model.addAttribute("issues", issues);
                return "issue/issueList";
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
            Map<String, Object> resMap = issueService.getIssue(issueId);
            log.info("resp code:" + resMap.get(SysConstant.RESP_CODE).toString());
            if (SysEnum.ResultCode.ERROR.getCode().equals(resMap.get(SysConstant.RESP_CODE))) {
                model.addAttribute("data", ResponseModel.error().message(resMap.get(SysConstant.RESP_MSG).toString()));
                return "result/error";
            } else {
                Issue issue = (Issue) resMap.get(SysConstant.RESP_DATA);
                model.addAttribute("issue", issue);
                return "issue/issueDetail";
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

