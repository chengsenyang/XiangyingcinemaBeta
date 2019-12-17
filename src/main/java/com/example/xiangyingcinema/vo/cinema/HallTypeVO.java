package com.example.xiangyingcinema.vo.cinema;

import lombok.Data;

import java.io.Serializable;

@Data
public class HallTypeVO implements Serializable {

    private String halltypeId;
    private String halltypeName;
    private boolean isActive;


}