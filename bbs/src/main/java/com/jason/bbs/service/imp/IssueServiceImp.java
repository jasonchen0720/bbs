package com.jason.bbs.service.imp;

import com.jason.bbs.common.SysConstant;
import com.jason.bbs.common.SysEnum;
import com.jason.bbs.dao.interf.IssueDao;
import com.jason.bbs.error.BaseSystemException;
import com.jason.bbs.error.BbsErrorEnum;
import com.jason.bbs.pojo.entity.Comment;
import com.jason.bbs.pojo.entity.Issue;
import com.jason.bbs.service.interf.IssueService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    public Map<String, Object> getIssueList(String column) {
        if (column == null || column.isEmpty())
            throw new BaseSystemException(BbsErrorEnum.BBS_PARAM_NULL);
        Map<String, Object> map = new HashMap<>();
        map.put("columnBelong", column);
        try {
            List<Issue> issues = issueDao.query(Issue.class, map, -1, -1);
            map.clear();
            map.put(SysConstant.RESP_CODE, SysEnum.ResultCode.OK.getCode());
            map.put(SysConstant.RESP_DATA, issues);
            log.info("获取主题列表成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.clear();
            map.put(SysConstant.RESP_CODE, SysEnum.ResultCode.ERROR.getCode());
        }
        return map;
    }

    @Transactional
    @Override
    public Map<String, Object> saveIssue(Issue issue) {
        if (issue == null)
            throw new BaseSystemException(BbsErrorEnum.BBS_PARAM_NULL);
        Map<String, Object> map = new HashMap<>();
        issue = (Issue) issueDao.save(issue);
        if (issue != null) {
            log.info("保存帖子成功");
            map.put(SysConstant.RESP_CODE, SysEnum.ResultCode.OK.getCode());
        } else {
            log.info("保存帖子失败");
            map.put(SysConstant.RESP_CODE, SysEnum.ResultCode.ERROR.getCode());
        }
        return map;
    }

    @Transactional
    @Override
    public Map<String, Object> getIssue(Long issueId) {
        if (issueId == null)
            throw new BaseSystemException(BbsErrorEnum.BBS_PARAM_NULL);
        Map<String, Object> map = new HashMap<>();
        Issue issue = (Issue) issueDao.get(Issue.class, issueId);
        if (issue == null) {
            map.put(SysConstant.RESP_CODE, SysEnum.ResultCode.ERROR.getCode());
            map.put(SysConstant.RESP_MSG, SysEnum.ResultMsg.ISSUE_NOT_FOUND.getMsg());
        } else {
            log.info("获取到帖子细节");
            List<Comment> commentList = issue.getComments();
            commentList.forEach(comment -> comment.getReplies());
            /*for (Comment comment : commentList) {
                log.info("评论内容：" + comment.getCommentContent());
                for (Reply reply : comment.getReplies()) {
                    log.info("回复内容：" + reply.getReplyContent());
                }
            }*/
            map.put(SysConstant.RESP_CODE, SysEnum.ResultCode.OK.getCode());
            map.put(SysConstant.RESP_DATA, issue);
        }
        return map;
    }
}
