package com.example.xiangyingcinema.vo.film;

import lombok.Data;

import java.io.Serializable;

@Data
public class SourceVO implements Serializable {

    private String sourceId;
    private String sourceName;
    private boolean isActive;

}