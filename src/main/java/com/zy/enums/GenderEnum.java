package com.zy.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum GenderEnum {
    男(0,"男"),
    女(1,"女");

    GenderEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @EnumValue
    private Integer code;
    private String msg;

}
