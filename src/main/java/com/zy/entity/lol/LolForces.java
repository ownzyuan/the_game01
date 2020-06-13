package com.zy.entity.lol;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "lol_forces")
/**
 * lol的势力实体类
 */
public class LolForces {

    //势力编号，主键
    //采用手动赋值方式
    @TableId(type = IdType.INPUT)
    private Integer fId;

    //势力名
    @TableField(value = "f_name")
    private String fName;

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
