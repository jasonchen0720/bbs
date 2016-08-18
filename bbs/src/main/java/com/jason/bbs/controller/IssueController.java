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

    @RequestMapping(value = "/toPublish/{columnBelong}")
    public String toPublish(@PathVariable("columnBelong") String columnBelong, Model model) {

        model.addAttribute("columnBelong", columnBelong);
        return "issue/issuePublish";

    }

    @RequestMapping(value = "/publish",method = RequestMethod.POST)
    public String publishIssue(@Valid IssuePublishForm issuePublishForm, BindingResult bindingResult, Model model, HttpServletRequest req) {
        log.info("开始发帖子了");
        if (bindingResult.hasErrors()) {
            log.error("用户发帖表单验证异常：" + bindingResult.getAllErrors().stream().findFirst().toString());
            model.addAttribute("data", new ResponseModel(-1, "发帖参数异常"));
            return "result/error";
        }
        UserVo creator = (UserVo) req.getSession().getAttribute(SysConstant.CURRENT_USER);
        Map<String, Object> resMap = issueService.saveIssue(issuePublishForm.asIssue(creator));
        if (SysEnum.ResultCode.ERROR.getCode().equals(resMap.get(SysConstant.RESP_CODE))) {

            model.addAttribute("data", new ResponseModel(-1, "发帖失败"));
            return "result/error";
        } else {
            resMap = issueService.getIssueList(issuePublishForm.getColumnBelong());
            if (SysEnum.ResultCode.ERROR.getCode().equals(resMap.get(SysConstant.RESP_CODE))) {
                log.info("添加主题后，重新加载主题列表异常");
                model.addAttribute("data", new ResponseModel(-1, "请刷新列表"));
                return "result/error";
            } else {
                log.info("添加主题后，加载主题列表成功");
                List<Issue> issues = (List<Issue>) resMap.get(SysConstant.RESP_DATA);
                model.addAttribute("columnBelong", issuePublishForm.getColumnBelong());
                model.addAttribute("issues",issues);
                return "issue/issueList";
            }
        }
    }

    @RequestMapping(value = "/issues/{columnBelong}")
    public String showIssues(@PathVariable("columnBelong") String columnBelong, Model model) {
        try {
            Map<String, Object> resMap = issueService.getIssueList(columnBelong);
            if (SysEnum.ResultCode.ERROR.getCode().equals(resMap.get(SysConstant.RESP_CODE))) {
                log.info("加载主题列表异常");
                model.addAttribute("data", new ResponseModel(-1, "加载主题列表异常，请重试"));
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
            if (BbsErrorEnum.BBS_PARAM_NULL.getMessage().equals(e.getErrorMessage())) {
                model.addAttribute("data", new ResponseModel(-1, "栏目参数为空"));
                return "result/error";
            } else {
                model.addAttribute("data", new ResponseModel(-1, "系统错误"));
                return "result/error";
            }
        }
    }

    @RequestMapping(value = "/issueDetail/{issueId}", method = RequestMethod.GET)
    public String showIssueDetail(@PathVariable("issueId") Long issueId, Model model, HttpServletRequest req) {
        log.info("显示帖子详情");
        try {
            Map<String, Object> resMap = issueService.getIssue(issueId);
            log.info("resp code:" + resMap.get(SysConstant.RESP_CODE).toString());
            if (SysEnum.ResultCode.ERROR.getCode().equals(resMap.get(SysConstant.RESP_CODE))) {
                model.addAttribute("data", new ResponseModel(-1, resMap.get(SysConstant.RESP_MSG).toString()));
                return "result/error";
            } else {
                Issue issue = (Issue) resMap.get(SysConstant.RESP_DATA);
                model.addAttribute("issue", issue);
                return "issue/issueDetail";
            }
        } catch (BaseSystemException e) {
            log.error(e.getErrorMessage());
            if (BbsErrorEnum.BBS_PARAM_NULL.getMessage().equals(e.getErrorMessage())) {
                model.addAttribute("data", new ResponseModel(-1, "帖子id参数为空"));
                return "result/error";
            } else {
                model.addAttribute("data", new ResponseModel(-1, "系统错误"));
                return "result/error";
            }
        }
    }

}
