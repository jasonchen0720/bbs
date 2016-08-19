package com.jason.bbs.controller;

import com.jason.bbs.common.util.JsonUtil;
import com.jason.bbs.pojo.vo.ResponseModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jason on 2016/8/10.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    /* @Autowired
     private ColumnService columnService;
    */

    @RequestMapping(value = "/toTest", method = RequestMethod.GET)
    public String toTest(){

        return "test";
    }

    @ResponseBody
    @RequestMapping(value = "/test",produces = "application/json;charset=UTF-8")
    public String test() {
        System.out.println(JsonUtil.objectToJsonStr(new ResponseModel(0, "你好")));
        return JsonUtil.objectToJsonStr(new ResponseModel(0, "你好"));
    }
}
