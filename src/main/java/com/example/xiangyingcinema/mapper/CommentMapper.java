package com.example.xiangyingcinema.mapper;

import com.example.xiangyingcinema.pojo.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//评论留言
public interface CommentMapper {

    //***************************************前台功能******************************************
    //获取相关 留言或者 评论
    List<Comment> getComment(String flagid);

    //获取子评论
    List<Comment> getSonComment(String cmid);
    //获取最新评论
    List<Comment> getNewComment();
    //增新评论
    String addComment(Comment comment);

    //**************************************后台功能***********************************************
    //根据条件获取相对应的数据
    List<Comment> getCommentBycondition(String flag);
    // 删除评论
    void deleteComment(String cmid);
    //留言成功
    void agreeComment(String cmid);
    //获取单一的留言
    Comment getSingleComment(String cmid);



}
