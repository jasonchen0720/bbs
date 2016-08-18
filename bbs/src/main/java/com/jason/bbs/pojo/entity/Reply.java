package com.jason.bbs.pojo.entity;


import javax.persistence.*;

import java.util.Date;
import javax.persistence.Column;
/**
 * Created by jason on 2016/8/10.
 */
@Entity
@Table(name="T_REPLY")
public class Reply {

    @Id
    @Column(name = "reply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @Column(name = "replier_id")
    private Long replierId;

    @Column(name = "replier_name")
    private String replierName;

    @Column(name = "reply_to_id")
    private Long replyToId;

    @Column(name = "reply_to_name")
    private String replyToName;

    @Column(name = "reply_content")
    private String replyContent;

    @Column(name = "reply_time")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date replyTime = new Date();

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public Long getReplierId() {
        return replierId;
    }

    public void setReplierId(Long replierId) {
        this.replierId = replierId;
    }

    public String getReplierName() {
        return replierName;
    }

    public void setReplierName(String replierName) {
        this.replierName = replierName;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }


    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
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
}
