package com.example.xiangyingcinema.controller;


import com.example.xiangyingcinema.dao.UserDao;
import com.example.xiangyingcinema.entity.PageResult;
import com.example.xiangyingcinema.entity.Result;
import com.example.xiangyingcinema.entity.StatusCode;
import com.example.xiangyingcinema.pojo.Spit;
import com.example.xiangyingcinema.pojo.User;
import com.example.xiangyingcinema.service.SpitService;
import com.example.xiangyingcinema.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserDao userDao;


    /*@RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK, "查询成功", spitService.findAll());
    }*/
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result findAll(){
        return new Result(true, StatusCode.OK, "查询成功", spitService.findAll());
    }



    @RequestMapping(method =RequestMethod.GET)
    public Result findByParentidIsNull(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findByParentidIsNull());
    }












    @RequestMapping(value = "/{spitId}",method =RequestMethod.GET)
    public Result findById(@PathVariable String spitId){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findById(spitId));
    }
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Spit spit){
            spitService.save(spit);
        return new Result(true, StatusCode.OK,"保存成功");
    }

    @RequestMapping(value = "/{spitId}",method = RequestMethod.PUT)
    public Result update(@PathVariable String spitId, @RequestBody Spit spit){
        spit.setId(spitId);    ////////////////////////////////////////////////////////////////////////////
        spitService.update(spit);
        return new Result(true, StatusCode.OK,"修改成功");
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id){
        System.out.println(id+"是Id");
        spitService.deleteById(id);
        return new Result(true, StatusCode.OK,"删除成功");
    }


    @RequestMapping(value = "/{nickname}",method = RequestMethod.DELETE)
    public Result deleteByNickname(@PathVariable String nickname){
        System.out.println(nickname+"是昵称");
        spitService.deleteByNickname(nickname);
        return new Result(true, StatusCode.OK,"删除成功");
    }






    @RequestMapping(value = "/commentlist/{parentid}/{page}/{size}", method = RequestMethod.GET)
    public Result comment(@PathVariable String parentid, @PathVariable int page, @PathVariable int size){
        Page<Spit> pageData = spitService.findByParentid(parentid, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Spit>(pageData.getTotalElements(), pageData.getContent()));
    }

    @RequestMapping(value = "/thumbup/{spitId}", method = RequestMethod.GET)
    public Result thumbup(@PathVariable String spitId){
       // String userid = "11111";
        String header = request.getHeader("Authorization");
        System.out.println("11111111header是"+header);

        System.out.println("header是"+header);
        String token = header.substring(7);

        Claims claims = jwtUtil.parseJWT(token);
        String userId=claims.getSubject();
        User u=userDao.findByMobile(userId);
        String userid = u.getMobile();
        System.out.println("usermobile是"+userid);
        //先判断该用户是否已经点赞了。
        if(redisTemplate.opsForValue().get("spit_"+userid+"_"+spitId)!=null){
            return new Result(false, StatusCode.ERROR, "不能重复点赞");
        }
        spitService.thumbup(spitId);
        redisTemplate.opsForValue().set("spit_"+userid+"_"+spitId, 1);
        return new Result(true, StatusCode.OK, "点赞成功");
    }
    @RequestMapping(value = "/findbynickname", method = RequestMethod.GET)
    public Result findByNickname(){
        String header = request.getHeader("Authorization");
        System.out.println("11111111header是"+header);

        System.out.println("header是"+header);
        String token = header.substring(7);

        Claims claims = jwtUtil.parseJWT(token);
        String nickname=claims.getSubject();

        return new Result(true, StatusCode.OK, "查询成功", spitService.findByNickname(nickname));
    }

}
