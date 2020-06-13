package com.zy.vo;

import com.zy.enums.GenderEnum;
import lombok.Data;


@Data
/**
 * 传输的实体类
 */
public class LolVo {

    //英雄编号
    private Integer hId;
    //英雄称号
    private String designation;
    //英雄名
    private String heroName;
    //性别
    private GenderEnum gender;
    //势力名
    private String force;
    //职业名（主）
    private String occupationOne;
    //职业名（次）
    private String occupationTwo;
    //推荐分路名一
    private String routeOne;
    //推荐分路名二
    private String routeTwo;

}
