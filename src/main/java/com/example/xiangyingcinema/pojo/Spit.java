package com.example.xiangyingcinema.pojo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name="tb_comment")
@Entity
@Data
public class Spit implements Serializable {

    @Id
    private String id;
    private String content;
    private String publishtime;
    private String userid;
    private String nickname;
    private Integer visits;
    private Integer thumbup;
    private Integer share;
    private Integer comment;
    private String state;
    private String parentid;



}
