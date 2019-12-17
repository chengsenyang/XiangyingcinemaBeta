//package com.example.xiangyingcinema.service;
//
//import com.example.xiangyingcinema.dao.UserDao;
//import com.example.xiangyingcinema.pojo.User;
//import com.example.xiangyingcinema.util.IdWorker;
//import com.example.xiangyingcinema.util.JwtUtil;
//
//import org.apache.commons.lang3.RandomStringUtils;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//@Service
//@Transactional
//public class UserService {
//    @Autowired
//    private UserDao userDao;
//    @Autowired
//    private IdWorker idWorker;
//    @Autowired
//    private RedisTemplate redisTemplate;
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//    @Autowired
//    private BCryptPasswordEncoder encoder;
//
//
//
//
//
//    public void sendSms(String mobile) {
//        //生成六位数字随机数
//        String checkcode= RandomStringUtils.randomNumeric(6);
//        //向缓存中放一份
//         redisTemplate.opsForValue().set("checkcode_"+mobile,checkcode,6, TimeUnit.HOURS);
//
//        //给用户发一份
//        Map<String,String> map=new HashMap<>();
//        map.put("mobile",mobile);
//        map.put("checkcode",checkcode);
//         rabbitTemplate.convertAndSend("sms",map);
//        //在控制台显示一份（测试）
//        System.out.println("验证码为:"+checkcode);
//    }
//
//
//    public void add(User user) {
//        user.setId(idWorker.nextId()+"");
//        user.setPassword(encoder.encode(user.getPassword()));
//        userDao.save(user);
//    }
//
//
//    public User login(String mobile, String password) {
//      User user=userDao.findByMobile(mobile);
//      if (user!=null&&encoder.matches(password,user.getPassword())){
//          return user;
//      }
//      return null;
//    }
//
//
//
//
//
//
//
//
//
//
//    public User getUserInfo(String mobile) {
//
//        User user = userDao.findByMobile(mobile);
//
//        return user;
//    }
//
//
//    public User updateUserInfo(User user) {
//        // 将传入的参数转换为DO 【MoocUserT】
//
//     /*  // user.setId(idWorker.nextId()+"");
//        user.setNickname(user.getNickname());
//        user.setLifeState(user.getLifeState());
//        user.setBirthday(user.getBirthday());
//        user.setBiography(user.getBiography());
//        user.setHeadAddress(user.getHeadAddress());
//        user.setEmail(user.getEmail());
//        user.setAddress(user.getAddress());
//        user.setMobile(user.getMobile());
//        user.setSex(user.getSex());*/
//
//
//        // DO存入数据库
//         userDao.save(user);
//        System.out.println("nickname is"+user.getNickname());
//        // 将数据从数据库中读取出来
//        User userInfo = getUserInfo(user.getMobile());
//        // 将结果返回给前端
//        return userInfo;
//        /*if(integer!=null){
//            // 将数据从数据库中读取出来
//            User userInfo = getUserInfo(user.getId());
//            // 将结果返回给前端
//            return userInfo;
//        }else{
//            return null;
//        }*/
//    }
//
//
//
//
//
//
//
//
//
//
//
//}



package com.example.xiangyingcinema.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.example.xiangyingcinema.dao.UserDao;
import com.example.xiangyingcinema.pojo.User;
import com.example.xiangyingcinema.util.IdWorker;
import com.example.xiangyingcinema.util.SmsUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
    @Autowired
    private BCryptPasswordEncoder encoder;





    public void sendSms(String mobile) {
        //生成六位数字随机数
        String checkcode= RandomStringUtils.randomNumeric(6);
        //向缓存中放一份
        redisTemplate.opsForValue().set("checkcode_"+mobile,checkcode,6, TimeUnit.HOURS);

        //给用户发一份
        Map<String,String> map=new HashMap<>();
        map.put("mobile",mobile);
        map.put("checkcode",checkcode);
//        rabbitTemplate.convertAndSend("sms",map);
        try {
            System.out.println(mobile);
            System.out.println("正在发短信");
            String result = SmsUtils.sendSms(mobile, checkcode);
            JSONObject jsonObject = JSON.parseObject(result);
            String resultCode = jsonObject.get("Code").toString();
            String resultMsg = jsonObject.get("Message").toString();
            System.out.println(resultCode);
            System.out.println(resultMsg);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        //在控制台显示一份（测试）
        System.out.println("验证码为:"+checkcode);
    }


    public void add(User user) {
        user.setId(idWorker.nextId()+"");
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.save(user);
    }


    public User login(String mobile, String password) {
        User user=userDao.findByMobile(mobile);
        if (user!=null&&encoder.matches(password,user.getPassword())){
            return user;
        }
        return null;
    }










    public User getUserInfo(String mobile) {

        User user = userDao.findByMobile(mobile);

        return user;
    }


    public User updateUserInfo(User user) {
        // 将传入的参数转换为DO 【MoocUserT】

     /*  // user.setId(idWorker.nextId()+"");
        user.setNickname(user.getNickname());
        user.setLifeState(user.getLifeState());
        user.setBirthday(user.getBirthday());
        user.setBiography(user.getBiography());
        user.setHeadAddress(user.getHeadAddress());
        user.setEmail(user.getEmail());
        user.setAddress(user.getAddress());
        user.setMobile(user.getMobile());
        user.setSex(user.getSex());*/


        // DO存入数据库
        userDao.save(user);
        System.out.println("nickname is"+user.getNickname());
        // 将数据从数据库中读取出来
        User userInfo = getUserInfo(user.getMobile());
        // 将结果返回给前端
        return userInfo;
        /*if(integer!=null){
            // 将数据从数据库中读取出来
            User userInfo = getUserInfo(user.getId());
            // 将结果返回给前端
            return userInfo;
        }else{
            return null;
        }*/
    }











}

