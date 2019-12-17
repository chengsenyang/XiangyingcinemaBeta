package com.example.xiangyingcinema.vo.order;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderQueryVO implements Serializable{

    private String cinemaId;
    private String filmPrice;

}
