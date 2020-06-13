package com.zy.vo;

import lombok.Data;

import java.util.List;

@Data
public class DataVo<T> {

    private Integer code;
    private String msg;
    private Long count;
    private List<T> data;

}
