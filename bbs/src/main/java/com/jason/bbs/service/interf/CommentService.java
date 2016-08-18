package com.jason.bbs.service.interf;

import com.jason.bbs.pojo.entity.Comment;

import java.util.Map;

/**
 * Created by jason on 2016/8/10.
 */
public interface CommentService {
    Map<String,Object> saveComment(Comment comment);

    Map<String,Object> getComment(Long commentId);
}
