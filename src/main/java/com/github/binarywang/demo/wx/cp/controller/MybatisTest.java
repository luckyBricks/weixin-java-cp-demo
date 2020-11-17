package com.github.binarywang.demo.wx.cp.controller;


import com.github.binarywang.demo.wx.cp.mapper.WxTableauMappingMapper;
import com.github.binarywang.demo.wx.cp.model.WxTableauMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Bricks
 * @date ：Created in 2020/11/17 23:12
 * @description：ceshi
 * @modified By：
 */
@Controller
@RequestMapping("wx/cp/selectAll")
public class MybatisTest {
    @Autowired(required = false)
    WxTableauMappingMapper wxTableauMappingMapper;

    @GetMapping("/test")
    @ResponseBody
    public Map<String,String> getDetail(@RequestParam(value = "userId") String userId){
        WxTableauMapping  wxTableauMapping = wxTableauMappingMapper.selectByPrimaryKey(1);
        System.out.println(wxTableauMapping);
        Map res = new HashMap<>();
        res.put("userId",wxTableauMapping.getUserId());
        res.put("tableauId", wxTableauMapping.getTableauId());
        res.put("tableauUrl", wxTableauMapping.getTableauUrl());
        return res;
    }
}
