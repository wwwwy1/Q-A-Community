package cn.Ideal.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
    public class Forum implements Serializable {

    private static final long serialVersionUID = 1L;

    public Forum() {
    }

    public Forum(Integer id, Integer forumReplys, Integer forumClicks, Integer forumThumbs) {
        this.id = id;
        this.forumReplys = forumReplys;
        this.forumClicks = forumClicks;
        this.forumThumbs = forumThumbs;
    }

    /**
            * 帖子id
            */
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            /**
            * 标题
            */
        @TableField("forum_title")
    private String forumTitle;

            /**
     * html格式内容
     */
    @TableField("forum_content")
    private String forumContent;
    /**
     * md格式内容
     */
    @TableField("forum_md")
    private String forumMd;

            /**
            * 标签记录id,用逗号间隔
            */
        @TableField("forum_tips")
    private String forumTips;
    @TableField(exist = false)
    private List<String> forumTipNames;
    @TableField(exist = false)
    private String abbreviationContent;
    @TableField(exist = false)
    private Integer canThumbUp;
    ;
            /**
            * 回复数
            */
        @TableField("forum_replys")
    private Integer forumReplys;

            /**
            * 点击量
            */
        @TableField("forum_clicks")
    private Integer forumClicks;

            /**
            * 点赞量
            */
        @TableField("forum_thumbs")
    private Integer forumThumbs;

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

            /**
            * 创建人用户id
            */
        @TableField("user_id")
    private String userId;


}
