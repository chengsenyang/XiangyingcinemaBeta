package com.example.xiangyingcinema.controller;

import com.example.xiangyingcinema.dao.UserDao;
import com.example.xiangyingcinema.entity.Result;
import com.example.xiangyingcinema.entity.StatusCode;
import com.example.xiangyingcinema.pojo.ImageCode;
import com.example.xiangyingcinema.pojo.User;
import com.example.xiangyingcinema.service.UserService;
import com.example.xiangyingcinema.util.JwtUtil;
import com.example.xiangyingcinema.vo.ResponseVO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@RestController

@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder encoder;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";


    @RequestMapping(value = "/login",method = RequestMethod.POST)

    public Result login(@RequestBody User user){
        System.out.println(user.getMobile());
        user =userService.login(user.getMobile(),user.getPassword());
        if (user==null){
            return new Result(false, StatusCode.LOGINERROR,"登陆失败");
        }
        String mobile=user.getMobile();//////////
        //String token =jwtUtil.createJWT(user.getId(),user.getMobile(),"user");
        String token =jwtUtil.createJWT(user.getId(),mobile,"user");
        Map<String,Object> map =new HashMap<>();
        map.put("token",token);
        map.put("roles","user");
        map.put("nickname",user.getNickname());
        map.put("mobile",user.getMobile());
        return new Result(true, StatusCode.OK,"登陆成功",map);
    }





    @RequestMapping(value = "/code/image",method =RequestMethod.GET)
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ImageCode imageCode=createImageCode(request);

        //sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        redisTemplate.opsForValue().set("imageCode_",imageCode.getCode(),6, TimeUnit.HOURS);
        System.out.println("图形验证码为："+imageCode.getCode());


        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());


    }








    private ImageCode createImageCode(HttpServletRequest request) {
        int width =67;
        int height =23;
        BufferedImage image =new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        Graphics g=image.getGraphics();

        Random random=new Random();

        g.setColor(getRandColor(200,250));
        g.fillRect(0,0,width,height);
        g.setFont(new Font("Times New Roman",Font.ITALIC,20));
        g.setColor(getRandColor(160,200));
        for (int i=0;i<155;i++){
            int x=random.nextInt(width);
            int y=random.nextInt(height);
            int xl=random.nextInt(12);
            int yl=random.nextInt(12);
            g.drawLine(x,y,x+xl,y+yl);
        }
        String sRand ="";
        for (int i=0;i<4;i++){
            String rand=String.valueOf(random.nextInt(10));
            sRand+=rand;
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            g.drawString(rand,13*i+6,16);
        }
        g.dispose();
        return new ImageCode(image,sRand,60);
    }

    /**
     * 生成背景条纹
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc,int bc){
        Random random=new Random();
        if (fc>255){
            fc=255;
        }
        if (bc>255){
            bc=255;
        }
        int r=fc+random.nextInt(bc - fc);
        int g=fc+random.nextInt(bc - fc);
        int b=fc+random.nextInt(bc - fc);
        return new Color(r,g,b);
    }

    /**
 * 发送短信验证码
 */
@RequestMapping(value = "/sendsms/{mobile}",method = RequestMethod.PUT)
@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "",methods = {})

public Result sendSms(@PathVariable String mobile){
    userService.sendSms(mobile);
    return new Result(true, StatusCode.OK,"手机验证码发送成功");
}
//,@PathVariable String iCode,@RequestBody ImageCode imageCode
@RequestMapping(value = "/register/{code}/{iCode}",method = RequestMethod.POST)
@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "",methods = {})

public Result register(@PathVariable String code, @RequestBody User user){
     //得到缓存中的验证码
   String checkcodeRedis = (String) redisTemplate.opsForValue().get("checkcode_"+user.getMobile());
    System.out.println("sssssssss"+checkcodeRedis);
    System.out.println(user.getPassword());
    System.out.println(user.getNickname());

    System.out.println(code);
    if (checkcodeRedis.isEmpty()){
        return new Result(false, StatusCode.ERROR,"请先获取手机验证码");
    }
    if (!checkcodeRedis.equals(code)){
        return new Result(false, StatusCode.ERROR,"请输入正确的手机验证码");
    }
    //得到缓存中的图片验证码
   /* String imageRedis = (String) redisTemplate.opsForValue().get("imageCode_");
    System.out.println("图形验证码为："+imageRedis);
    if (imageRedis.isEmpty()){
        return new Result(false,StatusCode.ERROR,"请先获得图片验证码");
    }
    if (!imageRedis.equals(iCode)){
        return new Result(false,StatusCode.ERROR,"请输入正确的图片验证码");
    }*/

        //拿出session中的ImageCode对象
       /* ImageCode imageCodeInSession = (ImageCode) sessionStrategy.getAttribute((RequestAttributes) request, UserController.SESSION_KEY);

        //校验


        if(imageCodeInSession == null){
            return new Result(false,StatusCode.ERROR,"验证码不存在，请刷新验证码");
        }

        if(!StringUtils.equalsIgnoreCase(imageCodeInSession.getCode(), iCode)){
            return new Result(false,StatusCode.ERROR,"验证码错误");
        }
        //验证通过，移除session中验证码
        sessionStrategy.removeAttribute((RequestAttributes) request, SESSION_KEY);*/





    userService.add(user);


    String mobile=user.getMobile();//////////
    //String token =jwtUtil.createJWT(user.getId(),user.getMobile(),"user");
    String token =jwtUtil.createJWT(user.getId(),mobile,"user");
    Map<String,Object> map =new HashMap<>();
    map.put("token",token);
    map.put("roles","user");
    map.put("nickname",user.getNickname());
    map.put("mobile",user.getMobile());















    return new Result(true, StatusCode.OK,"注册成功",map);
}












    @RequestMapping(value="getUserInfo",method = RequestMethod.GET)
    public ResponseVO getUserInfo(){
        String header = request.getHeader("Authorization");
        System.out.println("11111111header是"+header);

        System.out.println("header是"+header);
        String token = header.substring(7);

        Claims claims = jwtUtil.parseJWT(token);
        String mobile=claims.getSubject();












        if(mobile != null && mobile.trim().length()>0){

            User userInfo = userService.getUserInfo( mobile);
            if(userInfo!=null){
                return ResponseVO.success(userInfo);
            }else{
                return ResponseVO.appFail("用户信息查询失败");
            }
        }else{
            return ResponseVO.serviceFail("用户未登陆");
        }
    }

    @RequestMapping(value="updateUserInfo/{id}",method = RequestMethod.POST)
    public ResponseVO updateUserInfo(@PathVariable String id, User user){
        // 获取当前登陆用户
        System.out.println("id"+id);


        String header = request.getHeader("Authorization");
        System.out.println("11111111header是"+header);

        System.out.println("header是"+header);
        String token = header.substring(7);

        Claims claims = jwtUtil.parseJWT(token);
        String mobile=claims.getSubject();






        if(mobile != null && mobile.trim().length()>0){
            user.setMobile(mobile);
            user.setNickname(user.getNickname());
            user.setId(id);
            user.setPassword(encoder.encode(user.getPassword()));
            User userInfo = userService.updateUserInfo(user);
            System.out.println(userInfo);
            if(userInfo!=null){
                return ResponseVO.success(userInfo);
            }else{
                return ResponseVO.appFail("用户信息修改失败");
            }
        }else{
            return ResponseVO.serviceFail("用户未登陆");
        }
    }










}