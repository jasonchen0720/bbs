package com.jason.bbs.service.imp;

import com.jason.bbs.common.SysConstant;
import com.jason.bbs.common.SysEnum;
import com.jason.bbs.dao.interf.CommentDao;
import com.jason.bbs.dao.interf.ReplyDao;
import com.jason.bbs.error.BaseSystemException;
import com.jason.bbs.error.BbsErrorEnum;
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
    public Map<String, Object> saveReply(Reply reply) {
        if (reply == null || reply.getComment() == null || reply.getComment().getCommentId() == null) {
            throw new BaseSystemException(BbsErrorEnum.BBS_PARAM_NULL);
        }
        Comment comment = (Comment) commentDao.get(Comment.class, reply.getComment().getCommentId());
        Map<String, Object> map = new HashMap<>();
        if (comment != null) {
            reply = (Reply) replyDao.save(reply);
            if (reply != null) {
                log.info("保存回复成功");
                map.put(SysConstant.RESP_CODE, SysEnum.ResultCode.OK.getCode());
            }else{
                map.put(SysConstant.RESP_CODE, SysEnum.ResultCode.ERROR.getCode());
                map.put(SysConstant.RESP_MSG,SysEnum.ResultMsg.REPLY_SAVE_EEROR);
            }
        } else {
            log.error("评论不存在或已被删除");
            map.put(SysConstant.RESP_CODE, SysEnum.ResultCode.ERROR.getCode());
            map.put(SysConstant.RESP_MSG,SysEnum.ResultMsg.COMMENT_NOT_FOUND);
        }
        return map;
    }
}
