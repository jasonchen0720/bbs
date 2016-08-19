package com.jason.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jason on 2016/8/19.
 */
@Controller
@RequestMapping(value = "/column")
public class ColumnController {

    @RequestMapping(value = "/columns")
    public String listColumns(){

        return "column/columnList";

    }
}
