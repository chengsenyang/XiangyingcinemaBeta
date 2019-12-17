//package com.example.xiangyingcinema.listener;
//
//import com.aliyuncs.exceptions.ClientException;
//import com.example.xiangyingcinema.util.SmsUtils;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
//@Component
//@RabbitListener(queues = "sms")
//public class SmsListener {
////    @Autowired
////    private SmsUtils smsUtils;
////    @Value("${aliyun.sms.template_code}")
////    private String template_code;
////    @Value("${aliyun.sms.sign_name}")
////    private String sign_name;
//    @RabbitHandler
//    public void executeSms(Map<String,String> map){
//        String mobile=map.get("mobile");
//        String checkcode=map.get("checkcode");
//        System.out.println("手机号:"+map.get("mobile"));
//        System.out.println("验证码:"+map.get("checkcode"));
//        System.out.println("准备发短信");
//        try {
//            System.out.println("正在发短信");
////            System.out.println(template_code+" "+sign_name);
//            SmsUtils.sendSms(mobile, checkcode);
////            smsUtil.sendSms(mobile,template_code,sign_name,"{\"checkcode\":\""+checkcode+"\"}");
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
//    }
//}
