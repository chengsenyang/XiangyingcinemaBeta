package com.example.xiangyingcinema.vo.cinema;

import lombok.Data;

import java.io.Serializable;

@Data
public class CinemaInfoVO implements Serializable {

    private String cinemaId;
    private String imgUrl;
    private String cinemaName;
    private String cinemaAdress;
    private String cinemaPhone;

}
