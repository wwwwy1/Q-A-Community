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
    public class User implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 主键
            */
            @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

            /**
            * 登录名
            */
        @TableField("user_name")
    private String userName;

            /**
            * 昵称
            */
        @TableField("user_nickname")
    private String userNickname;

            /**
            * 头像
            */
        @TableField("user_img")
    private String userImg;

            /**
            * 密码
            */
        @TableField("user_password")
    private String userPassword;

            /**
            * 邮箱
            */
        @TableField("user_email")
    private String userEmail;

            /**
            * 点赞数,可为负数
            */
        @TableField("user_thumbs")
    private Integer userThumbs;

            /**
            * 发帖数
            */
        @TableField("user_post")
    private Integer userPost;

            /**
            * 回复数
            */
        @TableField("user_reply")
    private Integer userReply;

            /**
            * 经验值
            */
        @TableField("user_exp")
    private Integer userExp;

            /**
            * 等级
            */
        @TableField("user_level")
    private Integer userLevel;

            /**
            * 积分
            */
        @TableField("user_point")
    private Integer userPoint;

            /**
            * 头衔
            */
        @TableField("user_title")
    private String userTitle;

            /**
            * 是否验证邮箱 0否 1是
            */
        @TableField("user_check")
    private Integer userCheck;

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

        @TableField("last_login_date")
    private LocalDateTime lastLoginDate;


}
