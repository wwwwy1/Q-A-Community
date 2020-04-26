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
* @since 2020-04-22
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class Statistic implements Serializable {
    public Statistic() {
    }

    public Statistic(String userId, Integer res, String userNickname) {
        this.userId = userId;
        this.res = res;
        this.userNickname = userNickname;
    }

    public Statistic(Integer tagsId, Integer count) {
        this.tagsId = tagsId;
        this.count = count;
    }

    public Statistic(String userId, Integer count) {
        this.userId = userId;
        this.count = count;
    }

    private static final long serialVersionUID = 1L;

            /**
            * 统计id
            */
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            /**
            * 用户id
            */
        @TableField("user_id")
    private String userId;

            /**
            * 标签id
            */
        @TableField("tags_id")
    private Integer tagsId;

            /**
            * 数量
            */
        @TableField("count")
    private Integer count;
    @TableField(exist = false)
    private Integer res;
    @TableField(exist = false)
    private String userNickname;

    /**
     * 软删除  0:未删除 1:已删除
     */
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;
    /**
     * 插入时间
     */
    @TableField(value = "insert_date",fill = FieldFill.INSERT)
    private LocalDateTime insertDate;
    /**
     * 更新时间
     */
    @TableField(value = "update_date",fill = FieldFill.UPDATE)
    private LocalDateTime updateDate;


}
