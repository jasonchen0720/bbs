package com.jason.bbs.form;

import com.jason.bbs.pojo.entity.Comment;
import com.jason.bbs.pojo.entity.Reply;
import com.jason.bbs.pojo.vo.UserVo;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by jason on 2016/8/13.
 */
public class ReplySendForm {

    @NotBlank(message = "{bbs,reply.replyContent.null}")
    private  String replyContent;

    @NotNull(message = "{bbs,reply.commentId.null}")
    private  Long commentId;

    @NotNull(message = "{bbs,reply.replyToId.null}")
    private Long replyToId;

    @NotBlank(message = "{bbs,reply.replyToName.null}")
    private String replyToName;

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }



    public Long getReplyToId() {
        return replyToId;
    }

    public void setReplyToId(Long replyToId) {
        this.replyToId = replyToId;
    }

    public String getReplyToName() {
        return replyToName;
    }

    public void setReplyToName(String replyToName) {
        this.replyToName = replyToName;
    }
    public Reply asReply(UserVo replier , Long commentId){

        Reply reply = new Reply();

        Comment comment = new Comment();

        comment.setCommentId(commentId);

        reply.setReplierId(replier.getUserId());

        reply.setReplierName(replier.getUsername());

        reply.setComment(comment);

        reply.setReplyContent(this.replyContent);

        reply.setReplyToId(this.replyToId);

        reply.setReplyToName(this.replyToName);

        return reply;

    }
}
