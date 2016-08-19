package com.jason.bbs.form;

import com.jason.bbs.pojo.entity.Comment;
import com.jason.bbs.pojo.entity.Issue;
import com.jason.bbs.pojo.vo.UserVo;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by jason on 2016/8/13.
 */
public class CommentPublishForm {

    @NotBlank(message = "{bbs.comment.commentContent.null}")
    private String commentContent;


    @NotNull(message = "{bbs.comment.issueId.null}")
    private Long issueId;

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public Comment asComment(UserVo author, Long issueId) {
        Comment comment = new Comment();
        Issue issue = new Issue();
        issue.setIssueId(issueId);
        comment.setAuthorId(author.getUserId());
        comment.setAuthorName(author.getUsername());
        comment.setCommentContent(this.commentContent);
        comment.setIssue(issue);
        return comment;

    }
}
