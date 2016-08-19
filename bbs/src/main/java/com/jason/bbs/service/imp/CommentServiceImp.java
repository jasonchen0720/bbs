package com.jason.bbs.service.imp;

import com.jason.bbs.common.SysEnum;
import com.jason.bbs.dao.interf.CommentDao;
import com.jason.bbs.dao.interf.IssueDao;
import com.jason.bbs.exception.BaseSystemException;
import com.jason.bbs.exception.BbsErrorEnum;
import com.jason.bbs.pojo.bo.CommonBo;
import com.jason.bbs.pojo.entity.Comment;
import com.jason.bbs.pojo.entity.Issue;
import com.jason.bbs.service.interf.CommentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jason on 2016/8/12.
 */
@Service
public class CommentServiceImp implements CommentService {

    private static Logger log = Logger.getLogger(CommentServiceImp.class);

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private IssueDao issueDao;

    @Transactional
    @Override
    public CommonBo saveComment(Comment comment) {
        if (comment == null || comment.getIssue() == null || comment.getIssue().getIssueId() == null)
            throw new BaseSystemException(BbsErrorEnum.BBS_PARAM_NULL);
        Issue issue = (Issue) issueDao.get(Issue.class, comment.getIssue().getIssueId());
        if (issue != null) {
            comment.setIssue(issue);
            comment = (Comment) commentDao.save(comment);
            if (comment != null) {
                log.info("保存评论成功");
                return CommonBo.success().message(SysEnum.ResultMsg.COMMENT_SAVE_OK.getMsg());
            } else {
                return CommonBo.success().message(SysEnum.ResultMsg.COMMENT_SAVE_ERROR.getMsg());
            }
        } else {
            log.error("评论的帖子不存在");
            return CommonBo.success().message(SysEnum.ResultMsg.ISSUE_NOT_FOUND.getMsg());
        }
    }

    /*@Transactional
    @Override
    public Map<String, Object> getComment(Long commentId) {
        Map<String, Object> map = new HashMap<>();
        Comment comment = (Comment) commentDao.get(Comment.class, commentId);
        if (comment == null) {
            log.info("评论被删除或评论不存在");
            map.put(SysConstant.RESP_CODE, SysEnum.ResultCode.ERROR.getCode());
            map.put(SysConstant.RESP_MSG, SysEnum.ResultMsg.COMMENT_NOT_FOUND.getMsg());
        } else {
            map.put(SysConstant.RESP_DATA, comment);
            map.put(SysConstant.RESP_CODE, SysEnum.ResultCode.OK.getCode());
        }
        return map;
    }*/
}
