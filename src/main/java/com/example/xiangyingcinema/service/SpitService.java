package com.example.xiangyingcinema.service;

import com.example.xiangyingcinema.dao.SpitDao;
import com.example.xiangyingcinema.dao.UserDao;
import com.example.xiangyingcinema.pojo.Spit;
import com.example.xiangyingcinema.pojo.User;
import com.example.xiangyingcinema.util.DateUtil;
import com.example.xiangyingcinema.util.IdWorker;
import com.example.xiangyingcinema.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SpitService {
    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserDao userDao;


    public List<Spit> findAll(){
        return spitDao.findAll();
    }



    public List<Spit> findByParentidIsNull(){
        return spitDao.findByParentidIsNull();
    }





    public Spit findById(String id){
        return spitDao.findById(id).get();
    }

    public void save(Spit spit){
        String header = request.getHeader("Authorization");
        System.out.println("11111111header是"+header);

        System.out.println("header是"+header);
        String token = header.substring(7);

        Claims claims = jwtUtil.parseJWT(token);
        String userId=claims.getSubject();
        User u=userDao.findByMobile(userId);
        String nickname = u.getMobile();
        System.out.println("nickname是"+nickname);









        spit.setId(idWorker.nextId()+"");
        //初始化数据完善
        spit.setPublishtime(DateUtil.getTime(new Date()));//发布日期
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态
        spit.setNickname(nickname);//昵称
        spitDao.save(spit);
    }

    public void update(Spit spit){
        spitDao.save(spit);
    }

    public void deleteById(String id){
        spitDao.deleteById(id);
    }

    public void deleteByNickname(String nickname){
        spitDao.deleteByNickname(nickname);
    }

    public Page<Spit> findByParentid(String parentid, int page, int size){
        Pageable pageable= PageRequest.of(page-1,size);
        return spitDao.findByParentid(parentid,pageable);
    }

    public void thumbup(String spitId){
        Spit spit = spitDao.findById(spitId).get();
        spit.setThumbup((spit.getThumbup()==null ? 0: spit.getThumbup())+1);
        spitDao.save(spit);
    }

    public List<Spit> findByNickname(String nickname){
         return spitDao.findByNickname(nickname);
    };






}
