package com.example.xiangyingcinema.vo.film;

import lombok.Data;

import java.io.Serializable;

@Data
public class BannerVO implements Serializable {
    private String bannerId;
    private  String bannerAddress;
    private String bannerUrl;
}
