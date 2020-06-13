package com.zy.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "games")
/**
 * 游戏实体类
 */
public class Games {

    //游戏编号，主键
    //自增策略
    @TableId(type = IdType.AUTO)
    private Integer idGame;

    //游戏名
    @TableField(value = "game_name")
    private String gameName;

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
