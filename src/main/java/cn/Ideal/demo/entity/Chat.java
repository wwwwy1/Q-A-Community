package cn.Ideal.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* <p>
    * 
    * </p>
*
* @author wwwwy
* @since 2020-03-12
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 聊天id
            */
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            /**
            * 谁发的id
            */
        @TableField("from_user_name")
    private String fromUserName;

            /**
            * 发给谁的
            */
        @TableField("to_user_name")
    private String toUserName;

            /**
            * 信道id
            */
        @TableField("channel_id")
    private String channelId;

            /**
            * 发送内容
            */
        @TableField("content")
    private String content;

            /**
            * 地点
            */
        @TableField("location")
    private String location;
    /**
     * 0 未读，1 已读
     */
    @TableField("is_read")
    private Integer isRead;
    /**
     * 软删除  0:未删除 1:已删除
     */
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;

    /**
     * 插入时间
     */
    @TableField(value = "insert_date",fill = FieldFill.INSERT)
    private LocalDateTime insertDate;

    @TableField(value = "update_date",fill = FieldFill.UPDATE)
    private LocalDateTime updateDate;


}
