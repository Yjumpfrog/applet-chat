package com.chat.message.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


@Data
@TableName("message")
public class BusinessMessageEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 是否实现时间
     * 0：否 1：是
     */
    private Integer type;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 内容类型 0文字1图片2视频 3礼物
     */
    private Integer contentType;
    /**
     * 是否已读
     */
    private String isRead;
    /**
     * 发送人
     */
    private Long sender;
    /**
     * 接收人
     */
    private Long receiver;

    /**
     * 发送消息请求ID
     */
    private String requestId;

    private Boolean isLast;

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 创建人
     */
    @TableField(value = "created_by",fill = FieldFill.INSERT)
    private Long createdBy;
    /**
     * 创建时间
     */
    @TableField(value = "created_time",fill = FieldFill.INSERT)
    private Date createdTime;
    /**
     * 更新人
     */
    @TableField(value = "updated_by",fill = FieldFill.INSERT_UPDATE)
    private Long updatedBy;
    /**
     * 更新时间
     */
    @TableField(value = "updated_time",fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;
    /**
     * 是否删除
     */
    @TableField(value = "del_flag",fill = FieldFill.INSERT)
    private String delFlag;
    /**
     * 状态
     */
    private String status;
    /**
     * 备注
     */
    private String remarks;
}
