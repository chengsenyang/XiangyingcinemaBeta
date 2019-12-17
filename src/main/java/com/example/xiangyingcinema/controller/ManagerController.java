package com.example.xiangyingcinema.controller;

import com.example.xiangyingcinema.dao.FilmTRepository;
import com.example.xiangyingcinema.dao.OrderTRepository;
import com.example.xiangyingcinema.model.film.FilmT;
import com.example.xiangyingcinema.model.order.OrderT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private OrderTRepository orderTRepository;

    @Autowired
    private FilmTRepository filmTRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        String admin = (String)redisTemplate.opsForValue().get("admin");
        if (admin==null){
            map.put("msg", "请登录");
            map.put("url", "/manager/doLogin");
            return new ModelAndView("common/error");
        }

        PageRequest request = new PageRequest(page - 1, size);
        Page<OrderT> orderDTOPage = orderTRepository.findAll(request);
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);
//        orderDTOPage.getTotalPages()
        System.out.println(orderDTOPage.getContent());
        return new ModelAndView("order/list", map);
    }

    @GetMapping("/doLogin")
    public ModelAndView doLogin(Map<String, Object> map) {
        return new ModelAndView("login");
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("uuid") String uuid,
                               Map<String, Object> map) {
        String admin = (String)redisTemplate.opsForValue().get("admin");
        if (admin==null){
            map.put("msg", "请登录");
            map.put("url", "/manager/doLogin");
            return new ModelAndView("common/error");
        }

            orderTRepository.deleteById(uuid);
        map.put("msg", "取消订单成功");
        map.put("url", "/manager/list");
        return new ModelAndView("common/success");
    }




    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("filmId") Integer filmId,
                               Map<String, Object> map) {
        String admin = (String)redisTemplate.opsForValue().get("admin");
        if (admin==null){
            map.put("msg", "请登录");
            map.put("url", "/manager/doLogin");
            return new ModelAndView("common/error");
        }
//        try {
//            orderDTO = orderService.findOne(orderId);
//        }catch (SellException e) {
//            log.error("【卖家端查询订单详情】发生异常{}", e);
//            map.put("msg", e.getMessage());
//            map.put("url", "/seller/order/list");
//            return new ModelAndView("common/error", map);
//        }
        FilmT filmT =  filmTRepository.findByUuid(filmId);

        System.out.println(filmId);
        System.out.println(filmT);
        map.put("orderDTO", filmT);
        return new ModelAndView("order/detail", map);
    }


    @GetMapping("/logout")
    public ModelAndView logout(Map<String, Object> map) {

        redisTemplate.expire("admin",1, TimeUnit.SECONDS);
        map.put("msg", "登出成功");
        map.put("url", "/manager/doLogin");
        return new ModelAndView("common/success");
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("username") String ue ,@RequestParam("password") String pw
            ,Map<String, Object> map) {
        String username = "admin";
        String password = "admin";
        if (ue.equalsIgnoreCase(username)&&pw.equalsIgnoreCase(password)) {
            map.put("msg", "登入成功");
            map.put("url", "/manager/list");
            redisTemplate.opsForValue().set("admin",username,6, TimeUnit.HOURS);
            return new ModelAndView("common/success");
        }else{
            map.put("msg", "账号或密码错误");
            map.put("url", "/manager/doLogin");
            return new ModelAndView("common/error");
        }
    }




}
