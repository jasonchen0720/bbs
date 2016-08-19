package com.jason.bbs.service.interf;


import com.jason.bbs.pojo.bo.CommonBo;
import com.jason.bbs.pojo.bo.IssueBo;
import com.jason.bbs.pojo.entity.Issue;


import java.util.Map;

/**
 * Created by jason on 2016/8/10.
 */
public interface IssueService {

    IssueBo getIssueList(String column);
    IssueBo getIssue(Long issueId);
    CommonBo saveIssue(Issue issue);
}
