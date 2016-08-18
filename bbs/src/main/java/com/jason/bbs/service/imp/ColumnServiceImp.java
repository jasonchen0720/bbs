package com.jason.bbs.service.imp;

import com.jason.bbs.service.interf.ColumnService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jason on 2016/8/10.
 */
@Service
public class ColumnServiceImp implements ColumnService{

    @Override
    public Map<String, Object> getAllColumns() {
        Map<String,Object> map = new HashMap<>();

        return map;
    }
}
