package com.jason.bbs.service.interf;

import com.jason.bbs.pojo.bo.CommonBo;
import com.jason.bbs.pojo.entity.Reply;

import java.util.Map;

/**
 * Created by jason on 2016/8/10.
 */
public interface ReplyService {

    CommonBo saveReply(Reply reply);

}
