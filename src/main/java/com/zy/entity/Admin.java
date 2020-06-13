package com.zy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "admin")
/**
 * 管理员
 */
public class Admin {

    //管理员编号，主键
    //采用自增策略
    @TableId(type = IdType.AUTO)
    private Integer id;

    //用户名
    @TableField(value = "username")
    private String username;

    //密码
    @TableField(value = "password")
    private String password;

    //权限
    @TableField(value = "perms")
    private String perms;

}
