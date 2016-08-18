package com.jason.bbs.form;

import com.jason.bbs.pojo.entity.Issue;
import com.jason.bbs.pojo.vo.UserVo;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by jason on 2016/8/13.
 */
public class IssuePublishForm {

    @NotBlank(message = "{bbs.issue.columnBelong.null}")
    private String columnBelong;

    @NotBlank(message = "{bbs.issue.issueContent.null}")
    private String issueContent;

    public String getColumnBelong() {
        return columnBelong;
    }

    public void setColumnBelong(String columnBelong) {
        this.columnBelong = columnBelong;
    }

    public String getIssueContent() {
        return issueContent;
    }

    public void setIssueContent(String issueContent) {
        this.issueContent = issueContent;
    }

    public Issue asIssue(UserVo creator) {

        Issue issue = new Issue();
        issue.setColumnBelong(this.columnBelong);
        issue.setIssueContent(this.issueContent);
        issue.setCreatorId(creator.getUserId());
        issue.setCreatorName(creator.getUsername());
        return issue;

    }
}
