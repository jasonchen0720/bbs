package com.jason.test;


import com.jason.bbs.dao.interf.UserDao;
import com.jason.bbs.pojo.entity.User;
import com.jason.bbs.pojo.vo.AjaxResponse;
import com.jason.bbs.service.interf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
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

    @Test
    public void testSave() {
        User user = new User("111@qq.com", "jasonchen", "1");
        user = (User) userDao.save(user);
        Assert.assertNotNull(user);
    }
    @Test
    public  void testQuery(){
        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("username","'jason'");
//        map.put("password","'0720'");
//        System.out.println(map.get("password"));
//        StringBuilder sd = new StringBuilder("chenjie").append(map.get("password"));
//        System.out.println(sd.toString());
//        List<User> list = userDao.query(User.class,map,-1,-1);
//        list.forEach(user-> System.out.println(user.getUserId()));
//        map=userService.validateUserName("jasonchen");
//        System.out.println(map.get("respCode"));
        AjaxResponse.ok();
        AjaxResponse.fail();
        AjaxResponse.fail(3);
        AjaxResponse.noData();

    }
}
