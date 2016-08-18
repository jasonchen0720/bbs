package com.jason.bbs.service.interf;

import com.jason.bbs.pojo.entity.Reply;

import java.util.Map;

/**
 * Created by jason on 2016/8/10.
 */
public interface ReplyService {

    Map<String,Object> saveReply(Reply reply);

}
