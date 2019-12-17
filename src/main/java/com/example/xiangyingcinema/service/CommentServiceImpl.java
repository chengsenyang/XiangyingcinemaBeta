package com.example.xiangyingcinema.service;/*package com.example.xiangyingcinema.service;

import com.example.xiangyingcinema.mapper.CommentMapper;
import com.example.xiangyingcinema.pojo.Comment;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service

public class CommentServiceImpl implements CommentService {

    @Autowired
    private JavaMailSender jms; //自动注入的Bean 发送邮件的

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private LogFileServiceImpl logFileService;

    //***************************************前台功能******************************************
    //获取相关 留言或者 评论
    public PageInfo<Comment> getComment(String flagid, Integer currentPage, Integer pageSize){

        PageHelper.startPage(currentPage,pageSize);  //底层实现原理是改写sql语句

        List<Comment> commentList = commentMapper.getComment(flagid);
        for ( Comment comment : commentList ){   //找到有子评论的
            comment.setComments( commentMapper.getSonComment(comment.getCmid()) );
        }
        PageInfo<Comment> pageInfoComment = new PageInfo<Comment>(commentList);

        return pageInfoComment;
    }
    //获取最新评论
    public List<Comment> getNewComment(){

        return commentMapper.getNewComment();
    }

    //增新评论
    public String addComment(Comment comment){
        //1、整理数据
        comment.setCmid(UUID.randomUUID().toString());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        comment.setCmtime (format.format(new Date()));
        comment.setCmflag("0");
        if (comment.getSuperiorid().equals("")){
            comment.setSuperiorid("0");
            comment.setSuperiorname("");
        }
        //2、存入数据库
        commentMapper.addComment(comment);

        return "success";
    }


    //**************************************后台功能***********************************************
    //根据条件获取相对应的数据
    public PageInfo<Comment> getCommentBycondition(String flag, int page, int pageSize) {
        //根据条件获取相对应的数据
        // flag 0 ：评论  1 ： 留言 2 ： 全部  3：未处理的
        //page 当前页
        //pagesize 当前展示页数
        PageHelper.startPage(page,pageSize);  //底层实现原理是改写sql语句
        List<Comment> commentList = commentMapper.getCommentBycondition(flag);
        for (Comment comment : commentList){
            if(comment.getBtitle() == null || comment.getBtitle().equals(""))
                comment.setBtitle("留言");
        }
        PageInfo<Comment> pageInfoBlog = new PageInfo<Comment>(commentList);
        return pageInfoBlog;
    }
    // 删除评论
    public String deleteComment(Comment comment){
        // 1、删除数据
        commentMapper.deleteComment(comment.getCmid());
        //前期有些留言没有绑定邮箱
        if( !comment.getCmemail().equals("0")){
            // 2、给留言/评论人 发一封邮件
            String content =  comment.getCmname()+"你好 : \n" +
                    "你在 小道仙的个人博客 系统留言中，由于不符合网络言论规范，已经删除了你的留言\n" +
                    "如果你有什么问题，可以联系博主  1140459171\n" +
                    "\n\n                                                                   --小道仙的后宫";

            SimpleMailMessage mainMessage = new SimpleMailMessage();
            //发送者
            mainMessage.setFrom("xdxBlog@126.com");
            //接收者
            mainMessage.setTo(comment.getCmemail());
            //发送的标题
            mainMessage.setSubject("留言管理");
            //发送的内容
            mainMessage.setText(content);

            jms.send(mainMessage);
        }




        //3、书写日志
        logFileService.writerSimpleLog("删除评论 :  删除了一条评论");
        return "true";
    }

    //留言成功
    public String agreeComment(Comment comment) {

        commentMapper.agreeComment(comment.getCmid());

        // 2、给留言/评论人 发一封邮件
        //前期有些留言没有绑定邮箱
        String url = "";
        if( !comment.getCmemail().equals("0")){

            if (comment.getFlagid().equals("538764e7-af76-40bd-819a-cb9fa0b43904"))
                url = "http://www.xdx97.com/#/messageboard";
            else
                url = "www.xdx97.com/#/single?bid="+comment.getFlagid();

            String content =  "<html>" +
                    "<meta charset=\"utf-8\">"+
                    "<body>" + "<BR>" +
                    "<span>" + comment.getCmname() +" : 你好 </span>"+ "<BR>" +
                    "<span> 你在 <strong>小道仙个人博客 </strong> 中留言 已经入选了，感谢你的支持 </span>"+ "<BR>" +
                    "<div style=\"background: gainsboro;\">" + comment.getCmcontext() + "<BR>" +
                    "</div>" + "<BR>" +
                    "<a target=\"_blank\"  href=\""+url+"\">你可以点击前往查看</a>" +
                    "<p>如果不是你本人，请忽略此邮件，该邮件由系统发送，请勿回复！</p>" +
                    "</body>" +
                    "</html>";

            try {
                MimeMessage mainMessage =  jms.createMimeMessage();
                MimeMessageHelper messageHelper = new MimeMessageHelper(mainMessage,true,"UTF-8");
                messageHelper.setFrom("xdxBlog@126.com");
                messageHelper.setTo(comment.getCmemail());
                messageHelper.setSubject("留言管理");
                messageHelper.setText(content,true);
                jms.send(mainMessage);
            }catch (Exception e){

            }
        }
        // 如果是回复某个人的评论  就个那个人也发一封邮件
        if ( !comment.getSuperiorid().equals("0") ){
            // 先用 Superiorid 去找到相关的留言信息
            Comment c = commentMapper.getSingleComment(comment.getSuperiorid());
            if ( !c.getCmemail().equals("0")) {

                if (c.getFlagid().equals("538764e7-af76-40bd-819a-cb9fa0b43904"))
                    url = "http://www.xdx97.com/#/messageboard";
                else
                    url = "www.xdx97.com/#/single?bid="+c.getFlagid();

                String content1 =  "<html>" +
                        "<meta charset=\"utf-8\">"+
                        "<body>" + "<BR>" +
                        "<span>" + c.getCmname() +" : 你好 </span>"+ "<BR>" +
                        "<span> 你在 <strong>小道仙个人博客 </strong> 中留言 得到了回复 </span>"+ "<BR>" +
                        "<div style=\"background: gainsboro;\">" + c.getCmcontext() + "<BR>" +
                        "</div>" + "<BR>" +
                        "<span>回复类容如下 :  </span>"+
                        "<div style=\"background: gainsboro;\">" +comment.getCmcontext()+
                        "</div>" + "<BR>" +
                        "<a target=\"_blank\"  href=\""+url+"\">你可以点击前往查看</a>" +
                        "<p>如果不是你本人，请忽略此邮件，该邮件由系统发送，请勿回复！</p>" +
                        "</body>" +
                        "</html>";

                try {
                    MimeMessage mainMessage1 =  jms.createMimeMessage();
                    MimeMessageHelper messageHelper1 = new MimeMessageHelper(mainMessage1,true,"UTF-8");
                    messageHelper1.setFrom("xdxBlog@126.com");
                    messageHelper1.setTo(c.getCmemail());
                    messageHelper1.setSubject("留言管理");
                    messageHelper1.setText(content1,true);
                    jms.send(mainMessage1);
                }catch (Exception e){
                }

            }
        }

        logFileService.writerSimpleLog("新增留言 :  "+comment.getCmname());
        return "true";
    }
}*/