package com.jason.bbs.service.interf;

import com.jason.bbs.pojo.bo.CommonBo;
import com.jason.bbs.pojo.entity.Comment;

import java.util.Map;

/**
 * Created by jason on 2016/8/10.
 */
public interface CommentService {

   CommonBo saveComment(Comment comment);

    //Map<String,Object> getComment(Long commentId);
}
