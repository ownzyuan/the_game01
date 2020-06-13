package com.zy.entity.lol;

import com.baomidou.mybatisplus.annotation.*;
import com.zy.enums.GenderEnum;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "lol")
/**
 * 英雄联盟实体类
 */
public class Lol {

    //英雄编号，主键
    //采用手动赋值方式
    @TableId(type = IdType.INPUT)
    private Integer hId;

    //英雄称号
    @TableField(value = "designation")
    private String designation;

    //英雄名
    @TableField(value = "hero_name")
    private String heroName;

    //性别，采用枚举的方式
    @TableField(value = "gender")
    //private Integer gender;
    private GenderEnum gender;

    //势力编号，可以查询forces表得到
    @TableField(value = "force_id")
    private Integer forceId;

    //主要职业编号，可以查询forces表得到
    @TableField(value = "occupation_one")
    private Integer occupationOne;

    //次要职业编号，可以查询forces表得到
    @TableField(value = "occupation_two")
    private Integer occupationTwo;

    //推荐分路一，可以查询routes表得到
    @TableField(value = "route_one")
    private Integer routeOne;

    //推荐分路二，可以查询routes表得到
    @TableField(value = "route_two")
    private Integer routeTwo;

    //逻辑删除
    @TableLogic
    private Integer deleted;

    //创建时间
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    //更新时间
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
