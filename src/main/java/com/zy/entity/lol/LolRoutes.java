package com.zy.entity.lol;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "lol_routes")
/**
 * lol的分路实体类
 */
public class LolRoutes {

    //分路编号，主键
    //采用手动赋值方式
    @TableId(type = IdType.INPUT)
    private Integer rId;

    //分路名
    @TableField(value = "route")
    private String route;
}
