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
* @since 2020-02-11
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class Reply implements Serializable {
    public Reply() {
    }

    public Reply(Integer id, Integer replyThumbs) {
        this.id = id;
        this.replyThumbs = replyThumbs;
    }

    private static final long serialVersionUID = 1L;

            /**
            * 回复id
            */
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            /**
            * 帖子id
            */
        @TableField("forum_id")
    private Integer forumId;

            /**
            * 回复内容
            */
        @TableField("reply_content")
    private String replyContent;

            /**
            * 回复者id
            */
        @TableField("reply_user_id")
    private String replyUserId;
    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private User reUser;

    /**
            * 0则为第一层，其他则为楼中楼
            */
        @TableField("reply_father")
    private Integer replyFather;
        // 0未操作 1已经点赞 2已经点踩
    @TableField(exist = false)
    private Integer canThumbUp;
            /**
            * 被回复人id
            */
        @TableField("reply_return_user_id")
    private String replyReturnUserId;
    /**
     * 点赞数
     */
    @TableField("reply_thumbs")
    private Integer replyThumbs;
    /**
     * 第一层评论中，有多少条回复内容
     */
    @TableField("reply_count")
    private Integer replyCount;
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
