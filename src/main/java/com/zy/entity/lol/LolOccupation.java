package com.zy.entity.lol;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "lol_occupation")
/**
 * lol的职业实体类
 */
public class LolOccupation {

    //职业编号，主键
    //采用默认方式
    @TableId
    private Integer hcId;

    //职业名（英文）
    @TableField(value = "name_us")
    private String nameUs;

    //职业名（中文）
    @TableField(value = "name_cn")
    private String nameCn;

}
