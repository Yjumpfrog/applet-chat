package com.chat.message.service.impl;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.chat.message.service.ALSendSmsService;
import org.springframework.stereotype.Service;

@Service
public class ALSendSmsServiceImp implements ALSendSmsService {


    /**
     * 发送验证码
     * @param phone
     * @param code
     * @return
     */
    @Override
    public boolean SendCode(String phone,String code) {
        Config config = new Config();
        config.setAccessKeyId("LTAI4G2Qtmduu3gQQeuhppKc");
        config.setAccessKeySecret("3mIjUEyBMgoyCw8rH3PPoBEXGTdggj");
        config.endpoint = "dysmsapi.aliyuncs.com";
        Client client = null;
        try {
            SendSmsRequest sendSmsRequest = new SendSmsRequest();
            sendSmsRequest.setPhoneNumbers(phone)
                    .setSignName("阿里云短信")
                    .setTemplateCode("SMS_273765561")
                    .setTemplateParam("{\"code\":\""+code+"\"}");
            RuntimeOptions runtime = new RuntimeOptions();
            client = new Client(config);
            // 复制代码运行请自行打印 API 的返回值
            client.sendSmsWithOptions(sendSmsRequest, runtime);
            return true;
        } catch (TeaException error) {
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        }
        return false;
    }

}
