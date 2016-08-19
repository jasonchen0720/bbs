package com.jason.bbs.service.imp;

import com.jason.bbs.common.SysEnum.ResultMsg;
import com.jason.bbs.dao.interf.IssueDao;
import com.jason.bbs.exception.BaseSystemException;
import com.jason.bbs.exception.BbsErrorEnum;
import com.jason.bbs.pojo.bo.CommonBo;
import com.jason.bbs.pojo.bo.IssueBo;
import com.jason.bbs.pojo.entity.Comment;
import com.jason.bbs.pojo.entity.Issue;
import com.jason.bbs.service.interf.IssueService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 2016/8/11.
 */
@Service
public class IssueServiceImp implements IssueService {

    private static Logger log = Logger.getLogger(IssueServiceImp.class);

    @Autowired
    private IssueDao issueDao;

    @Transactional
    @Override
    public IssueBo getIssueList(String column) {
        if (column == null || column.isEmpty()) {
            throw new BaseSystemException(BbsErrorEnum.BBS_PARAM_NULL);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("columnBelong", column);
        List<Issue> issues = issueDao.query(Issue.class, map, -1, -1);
        if (issues != null) {
            log.info("获取主题列表成功");
            return (IssueBo) IssueBo.success(issues).message(ResultMsg.ISSUE_LOAD_OK.getMsg());
        } else {
            return (IssueBo) IssueBo.success(issues).message(ResultMsg.ISSUE_LOAD_ERROR.getMsg());
        }
    }

    @Transactional
    @Override
    public IssueBo getIssue(Long issueId) {
        if (issueId == null)
            throw new BaseSystemException(BbsErrorEnum.BBS_PARAM_NULL);
        Map<String, Object> map = new HashMap<>();
        Issue issue = (Issue) issueDao.get(Issue.class, issueId);
        if (issue == null) {
            return (IssueBo) IssueBo.fail(null).message(ResultMsg.ISSUE_NOT_FOUND.getMsg());
        } else {
            log.info("获取到帖子细节");
            List<Comment> commentList = issue.getComments();
            commentList.forEach(comment -> comment.getReplies());
            List<Issue> issues = new ArrayList<>();
            issues.add(issue);
            return (IssueBo) IssueBo.success(issues).message(ResultMsg.ISSUE_GET_OK.getMsg());
        }
    }

    @Transactional
    @Override
    public CommonBo saveIssue(Issue issue) {
        if (issue == null)
            throw new BaseSystemException(BbsErrorEnum.BBS_PARAM_NULL);
        issue = (Issue) issueDao.save(issue);
        if (issue != null) {
            return CommonBo.success().message(ResultMsg.ISSUE_SAVE_OK.getMsg());
        } else {
            return CommonBo.fail().message(ResultMsg.ISSUE_SAVE_ERROR.getMsg());
        }
    }
}
