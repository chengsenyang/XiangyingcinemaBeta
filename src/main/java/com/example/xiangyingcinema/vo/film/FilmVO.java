package com.example.xiangyingcinema.vo.film;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FilmVO implements Serializable {

    private int filmNum;
    private int nowPage;
    private int totalPage;
    private List<FilmInfo> filmInfo;
}
