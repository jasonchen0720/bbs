package com.jason.bbs.controller;

import com.jason.bbs.service.interf.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jason on 2016/8/10.
 */
@Controller
@RequestMapping(value = "/column")
public class ColumnController {

    @Autowired
    private ColumnService columnService;

    @RequestMapping(value = "/columns")
    public String showColumns(){
        return "column/columnList";
    }
}
