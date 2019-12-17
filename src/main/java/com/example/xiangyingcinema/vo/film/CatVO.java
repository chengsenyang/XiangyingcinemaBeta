package com.example.xiangyingcinema.vo.film;

import lombok.Data;

import java.io.Serializable;

@Data
public class CatVO implements Serializable {

    private String catId;
    private String catName;
    private boolean isActive;

}
