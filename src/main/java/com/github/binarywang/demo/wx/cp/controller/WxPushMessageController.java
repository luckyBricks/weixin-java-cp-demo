package com.github.binarywang.demo.wx.cp.controller;

import com.github.binarywang.demo.wx.cp.config.WxCpConfiguration;
import com.github.binarywang.demo.wx.cp.config.WxCpProperties;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpMessage;
import me.chanjar.weixin.cp.bean.WxCpMessageSendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Bricks
 * @date ：Created in 2020/11/17 0:51
 * @description：推送消息到企业自有应用
 * @modified By：
 */

@RestController
@RequestMapping("/wx/cp/push")
public class WxPushMessageController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    WxCpProperties wxCpProperties;
    /**
     * @param user 希望推送的用户列表，用|隔开
     * @param content 希望推送的文本消息正文
     * @param index 希望推送的应用agentId
     */
    @GetMapping("/sendContent")
    public Map<String, String> pushToUsers(@RequestParam(value = "user") String user,
                                           @RequestParam(value = "content") String content,
                                           @RequestParam(value = "index") int index)
    {
        Map<String, String> returnSet = new HashMap<>();
        returnSet.put("code", "100");
        //从properties中获取企业应用信息
        int agentId = wxCpProperties.getAppConfigs().get(index).getAgentId();
        final WxCpService wxCpService = WxCpConfiguration.getCpService(agentId);

        //构造消息体
        WxCpMessage message = WxCpMessage
            .TEXT()
            .agentId(agentId)
            .toUser(user)
            .content(content)
            .build();
        this.logger.info("\n接收到来自微信服务器的认证消息：echoStr=[{}]", message.getContent());

        //通过封装好的提示类返回结果
        WxCpMessageSendResult wxCpMessageSendResult = new WxCpMessageSendResult();
        try {
            wxCpMessageSendResult = wxCpService.messageSend(message);
        }catch (WxErrorException e){
            e.printStackTrace();
            this.logger.info("\n----->错误原因是：[{}]", e.getCause());
            this.logger.info("\n------->消息是：[{}]", e.getMessage());
            returnSet.put("code", "400");
            returnSet.put("msg", "推送失败，请联系管理员");
        }

        if(wxCpMessageSendResult.getErrCode()==0){
            returnSet.put("code","0");
            returnSet.put("msg", "推送成功");
            return returnSet;
        }else {
            returnSet.put("code", "500");
            returnSet.put("msg", "推送失败，内部错误");
            return returnSet;
        }


    }
}
