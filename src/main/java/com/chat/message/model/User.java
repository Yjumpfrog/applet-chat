package com.chat.message.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName(value = "user")
public class User implements Serializable {

    private Long id;

    private String name;

    private String avatar;

    private String openId;

    @TableField(exist = false)
    private String code;

    private String username;

    private String password;
}
