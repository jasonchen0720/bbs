package com.jason.bbs.test;


import com.jason.bbs.common.SysConstant;
import com.jason.bbs.common.SysEnum;
import com.jason.bbs.dao.interf.IssueDao;
import com.jason.bbs.dao.interf.UserDao;
import com.jason.bbs.pojo.entity.Issue;
import com.jason.bbs.pojo.entity.User;
import com.jason.bbs.service.interf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 2016/8/11.
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BbsTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private IssueDao issueDao;

    @Test
    public void testSave() {
        User user = new User("111@qq.com", "jasonchen", "1");
        user = (User) userDao.save(user);
        Assert.assertNotNull(user);
    }

    @Test
    public void testQuery() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", "jasonchen");
        List<User> list = userDao.query(User.class, map, -1, -1);
        Assert.assertEquals(list.size() > 0, true);
        list.forEach(user -> System.out.println("已经查询到用户，用户ID为：" + user.getUserId()));
    }

    @Test
    public void testValidateUserName() {
        Map<String, Object> map = userService.validateUserName("jasonchen");
        System.out.println("jasonchen:" + map.get(SysConstant.RESP_MSG));
        Assert.assertEquals(map.get(SysConstant.RESP_CODE), SysEnum.ResultCode.ERROR.getCode());
    }

    @Test
    public void testDelete() {

        Issue issue = new Issue();
        issue.setIssueId(1L);
        issueDao.delete(issue);

    }
}
