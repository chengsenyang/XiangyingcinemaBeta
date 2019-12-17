package com.example.xiangyingcinema.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.xiangyingcinema.dao.UserDao;
import com.example.xiangyingcinema.pojo.User;
import com.example.xiangyingcinema.service.alipay.AliPayServiceAPI;
import com.example.xiangyingcinema.service.order.OrderServiceAPI;
import com.example.xiangyingcinema.util.JwtUtil;
import com.example.xiangyingcinema.util.TokenBucket;
import com.example.xiangyingcinema.vo.ResponseVO;
import com.example.xiangyingcinema.vo.alipay.AliPayInfoVO;
import com.example.xiangyingcinema.vo.alipay.AliPayResultVO;
import com.example.xiangyingcinema.vo.order.OrderVO;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(value = "/order/")
public class OrderController {

    private static TokenBucket tokenBucket = new TokenBucket();
    private static final String IMG_PRE="../assets/img/";

    @Autowired
    private OrderServiceAPI orderServiceAPI;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AliPayServiceAPI aliPayServiceAPI;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserDao userDao;




    public ResponseVO error(Integer fieldId, String soldSeats, String seatsName){
        return ResponseVO.serviceFail("抱歉，下单的人太多了，请稍后重试");
    }

    // 购票
    @RequestMapping(value = "buyTickets",method = RequestMethod.POST)
    public ResponseVO buyTickets(Integer fieldId,String soldSeats,String seatsName, User user){

        if(tokenBucket.getToken()){
            // 验证售出的票是否为真
            boolean isTrue = orderServiceAPI.isTrueSeats(fieldId+"",soldSeats);

            // 已经销售的座位里，有没有这些座位
            boolean isNotSold = orderServiceAPI.isNotSoldSeats(fieldId+"",soldSeats);

            // 验证，上述两个内容有一个不为真，则不创建订单信息
            if(isTrue && isNotSold){
                // 创建订单信息,注意获取登陆人
               //String userId = CurrentUser.getCurrentUser();
                String header = request.getHeader("Authorization");
                System.out.println("11111111header是"+header);
                if(header == null || header.trim().length() == 0)
                {
                    return ResponseVO.serviceFail("用户未登陆");
                }
                /*if (!header.startsWith("Bearer "))
                {
                    return ResponseVO.serviceFail("用户未登陆");
                }*/
                System.out.println("header是"+header);
                String token = header.substring(7);

                Claims claims = jwtUtil.parseJWT(token);
                String userId=claims.getSubject();
                User u=userDao.findByMobile(userId);
                String usermobile = u.getMobile();
                System.out.println("usermobile是"+usermobile);
                //String userId = (String) claims.get("roles");
                if (userId==null || !userId.equals(usermobile))
                {
                    return ResponseVO.serviceFail("用户未登陆");
                }


                  System.out.println("userId是"+userId);



                OrderVO orderVO = orderServiceAPI.saveOrderInfo(fieldId,soldSeats,seatsName,userId);

                System.out.println("111111111status是"+orderVO.getOrderStatus());
                if(orderVO == null){
                    log.error("购票未成功");
                    return ResponseVO.serviceFail("购票业务异常");
                }else{
                    System.out.println("status是"+orderVO.getOrderStatus());
                    return ResponseVO.success(orderVO);

                }
            }else{
                return ResponseVO.serviceFail("订单中的座位编号有问题");
            }
        }else{
            return ResponseVO.serviceFail("购票人数过多，请稍后再试");
        }
    }


    @RequestMapping(value = "getOrderInfo",method = RequestMethod.POST)
    public ResponseVO getOrderInfo(
            @RequestParam(name = "nowPage",required = false,defaultValue = "1")Integer nowPage,
            @RequestParam(name = "pageSize",required = false,defaultValue = "5")Integer pageSize
    ){

        // 获取当前登陆人的信息
        //String userId = CurrentUser.getCurrentUser();
        String header = request.getHeader("Authorization");
        String token = header.substring(7);

        Claims claims = jwtUtil.parseJWT(token);
        String userId=claims.getSubject();

        // 使用当前登陆人获取已经购买的订单
        Page<OrderVO> page = new Page<>(nowPage,pageSize);
        if(userId != null && userId.trim().length()>0){
            Page<OrderVO> result = orderServiceAPI.getOrderByUserId(userId, page);

            return ResponseVO.success(nowPage,(int)result.getPages(),"",result.getRecords());

        }else{
            return ResponseVO.serviceFail("用户未登陆");
        }
    }


    @RequestMapping(value = "getPayInfo",method = RequestMethod.POST)
    public ResponseVO getPayInfo(@RequestParam("orderId") String orderId){
        // 获取当前登陆人的信息
        //String userId = CurrentUser.getCurrentUser();
        String userId = request.getHeader("Authorization");
        if(userId==null || userId.trim().length()==0){
            return ResponseVO.serviceFail("抱歉，用户未登陆");
        }
        // 订单二维码返回结果
        AliPayInfoVO aliPayInfoVO = aliPayServiceAPI.getQRCode(orderId);
        return ResponseVO.success(IMG_PRE,aliPayInfoVO);
    }


    @RequestMapping(value = "getPayResult",method = RequestMethod.POST)
    public ResponseVO getPayResult(
            @RequestParam("orderId") String orderId,
            @RequestParam(name="tryNums",required = false,defaultValue = "1") Integer tryNums){
        // 获取当前登陆人的信息
        //String userId = CurrentUser.getCurrentUser();
        String userId = request.getHeader("Authorization");
        if(userId==null || userId.trim().length()==0){
            return ResponseVO.serviceFail("抱歉，用户未登陆");
        }


        // 判断是否支付超时
        if(tryNums>=4){
            return ResponseVO.serviceFail("订单支付失败，请稍后重试");
        }else{
            AliPayResultVO aliPayResultVO = aliPayServiceAPI.getOrderStatus(orderId);
            if(aliPayResultVO == null || aliPayResultVO.getOrderId().isEmpty()){
                AliPayResultVO serviceFailVO = new AliPayResultVO();
                serviceFailVO.setOrderId(orderId);
                serviceFailVO.setOrderStatus(0);
                serviceFailVO.setOrderMsg("支付不成功");
                return ResponseVO.success(serviceFailVO);
            }
            return ResponseVO.success(aliPayResultVO);
        }
    }

}