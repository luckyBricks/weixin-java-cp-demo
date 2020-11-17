package com.github.binarywang.demo.wx.cp.controller;

import com.github.binarywang.demo.wx.cp.config.WxCpConfiguration;
import com.github.binarywang.demo.wx.cp.config.WxCpProperties;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpOauth2UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("wx/cp/oauth")
public class WxOauth2Redirect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    WxCpProperties wxCpProperties;



    @RequestMapping("/createUrlForCode")
    public String createUrlForCode (
        @RequestParam(value = "accUrl") String url,
        @RequestParam(value = "index") int index){
        int agentId = wxCpProperties.getAppConfigs().get(index).getAgentId();
        WxCpService wxCpService = WxCpConfiguration.getCpService(agentId);
        //利用State参数携带index跳转，让getInfo中少处理一个参数
        String redirectUrl = wxCpService.getOauth2Service().buildAuthorizationUrl(url, Integer.toString(index));
        logger.info("跳转URL已经创建：{}", redirectUrl);
        return "redirect:"+redirectUrl;
    }


    //http://67b950926228.ngrok.io/wx/cp/oauth/createUrlForCode?index=0&accUrl=http%3A%2F%2F67b950926228.ngrok.io%2Fwx%2Fcp%2Foauth%2FgetInfo%3FtableauPage%3Dhttp%253A%252F%252Fwww.baidu.com%253Fparm1%253D1%2526parm2%253D2
    @RequestMapping("/getInfo")
    @ResponseBody
    public Map<String,String> oauth(@RequestParam(value = "code")String code,
                                    @RequestParam(value = "tableauPage") String tableauPage,
                                    @RequestParam(value = "state") String index) throws WxErrorException {
        int agentId = wxCpProperties.getAppConfigs().get(Integer.parseInt(index)).getAgentId();
        WxCpService wxCpService = WxCpConfiguration.getCpService(agentId);
        WxCpOauth2UserInfo res = wxCpService.getOauth2Service().getUserInfo(code);
        Map<String, String> resMap = new HashMap<>();
        resMap.put("userId",res.getUserId());
        resMap.put("deviceId", res.getDeviceId());
        resMap.put("tableau",tableauPage);
        logger.info("获取用户信息：{}",resMap);
        return resMap ;
    }
}
