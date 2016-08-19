package com.jason.bbs.service.imp;

import com.jason.bbs.common.SysEnum.ResultMsg;
import com.jason.bbs.dao.interf.CommentDao;
import com.jason.bbs.dao.interf.ReplyDao;
import com.jason.bbs.exception.BaseSystemException;
import com.jason.bbs.exception.BbsErrorEnum;
import com.jason.bbs.pojo.bo.CommonBo;
import com.jason.bbs.pojo.entity.Comment;
import com.jason.bbs.pojo.entity.Reply;
import com.jason.bbs.service.interf.ReplyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jason on 2016/8/12.
 */

@Service
public class ReplyServiceImp implements ReplyService {

    private static Logger log = Logger.getLogger(ReplyServiceImp.class);

    @Autowired
    private ReplyDao replyDao;

    @Autowired
    private CommentDao commentDao;

    @Transactional
    @Override
    public CommonBo saveReply(Reply reply) {
        if (reply == null || reply.getComment() == null || reply.getComment().getCommentId() == null) {
            throw new BaseSystemException(BbsErrorEnum.BBS_PARAM_NULL);
        }
        Comment comment = (Comment) commentDao.get(Comment.class, reply.getComment().getCommentId());
        Map<String, Object> map = new HashMap<>();
        if (comment != null) {
            reply = (Reply) replyDao.save(reply);
            if (reply != null) {
                log.info("保存回复成功");
                return CommonBo.success().message(ResultMsg.REPLY_SAVE_OK.getMsg());
            }else{
                return CommonBo.fail().message(ResultMsg.REPLY_SAVE_EEROR.getMsg());
            }
        } else {
            log.error("评论不存在或已被删除");
            return CommonBo.fail().message(ResultMsg.COMMENT_NOT_FOUND.getMsg());
        }
    }
}
