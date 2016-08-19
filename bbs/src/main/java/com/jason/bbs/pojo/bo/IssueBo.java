package com.jason.bbs.pojo.bo;

import com.jason.bbs.pojo.entity.Issue;

import java.util.List;

/**
 * Created by jason on 2016/8/19.
 */
public class IssueBo extends CommonBo {

    private List<Issue> issues;

    public IssueBo() {
    }

    public IssueBo(Boolean result, List<Issue> issues) {
        super(result);
        this.issues = issues;
    }

    public static IssueBo success(List<Issue> issues) {
        return new IssueBo(true, issues);
    }

    public static IssueBo fail(List<Issue> issues){
        return new IssueBo(false,issues);
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }
}
