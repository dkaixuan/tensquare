package com.tensquare.user.mq;

import com.tensquare.common.util.TensquareUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/1/27 10:41
 */
@Component
@RabbitListener(queues ="sms")
public class SmsListener {


    @Value("${tensquare.short.message.appcode}")
    private String appcode;


    /**
     * 发送短信
     * @param map
     */
    @RabbitHandler
    public void sendSms(Map<String, String> map) {
        String mobile = map.get("mobile");
        String code = map.get("code");
       // TensquareUtils.sendShortMessage(appcode,code,mobile);
    }



}
