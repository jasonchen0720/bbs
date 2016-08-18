package com.jason.bbs.service.interf;


import com.jason.bbs.pojo.entity.Issue;


import java.util.Map;

/**
 * Created by jason on 2016/8/10.
 */
public interface IssueService {

    Map<String,Object> getIssueList(String column);
    Map<String,Object> saveIssue(Issue issue);
    Map<String,Object> getIssue(Long issueId);
}
