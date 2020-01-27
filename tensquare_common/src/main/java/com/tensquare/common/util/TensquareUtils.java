package com.tensquare.common.util;

import com.tensquare.common.constants.ResultCodeEnum;
import com.tensquare.common.exception.TensquareException;
import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/1/23 20:02
 */
public class TensquareUtils {

    /**
     * 生成随机长度数字字符串
     * @param length
     * @return
     */
    public static String randomCode(int length) {
        if (length <= 0) {
            throw new TensquareException(ResultCodeEnum.SUCCESS);
        }
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            // 1.生成随机数
            double doubleRandom = Math.random();
            // 2.调整
            int integerRandom = (int) (doubleRandom * 10);
            // 3.拼接
            builder.append(integerRandom);
        }
        return builder.toString();

    }



    /**
     * 发送验证码短信
     * @param appCode		阿里云市场中调用API时识别身份的appCode
     * @param randomCode	验证码值
     * @param phoneNum		接收验证码短信的手机号
     */
    public static void sendShortMessage(String appCode, String randomCode, String phoneNum) {

        String host = "http://dingxin.market.alicloudapi.com";
        String path = "/dx/sendSms";
        String method = "POST";

        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appCode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", phoneNum);
        querys.put("param", "code:"+randomCode);
        querys.put("tpl_id", "TP1711063");
        Map<String, String> bodys = new HashMap<String, String>();


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            throw new TensquareException(ResultCodeEnum.SMS_CODE_INTERFACE_ERROR);
        }
    }




}
