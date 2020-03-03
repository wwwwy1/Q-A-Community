package cn.Ideal.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
* @since 2020-03-02
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class ThumbUp implements Serializable {
    public ThumbUp(String userId, Integer forumId) {
        this.userId = userId;
        this.forumId = forumId;
    }

    public ThumbUp(String userId, Integer forumId, Integer replyId) {
        this.userId = userId;
        this.forumId = forumId;
        this.replyId = replyId;
    }

    private static final long serialVersionUID = 1L;

            /**
            * 点赞id
            */
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            /**
            * 用户id
            */
        @TableField("user_id")
    private String userId;

            /**
            * 帖子id
            */
        @TableField("forum_id")
    private Integer forumId;

            /**
            * 回复id
            */
        @TableField("reply_id")
    private Integer replyId;

            /**
            * 插入日期
            */
        @TableField("insert_date")
    private LocalDateTime insertDate;

            /**
            * 更新日期
            */
        @TableField("update_date")
    private LocalDateTime updateDate;

            /**
            * 软删除  0:未删除 1:已删除
            */
        @TableField("is_deleted")
    private Integer isDeleted;


}
