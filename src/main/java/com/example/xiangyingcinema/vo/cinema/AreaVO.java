package com.example.xiangyingcinema.vo.cinema;

import lombok.Data;

import java.io.Serializable;

@Data
public class AreaVO implements Serializable {

    private String areaId;
    private String areaName;
    private boolean isActive;


}
