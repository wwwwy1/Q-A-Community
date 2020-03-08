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
* @since 2020-03-08
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class TaskList implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 任务id
            */
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            /**
            * 用户id
            */
        @TableField("user_id")
    private String userId;

            /**
            * 任务内容
            */
        @TableField("content")
    private String content;

            /**
            * 任务类型1:未完成 2:进行中 3:已完成
            */
        @TableField("type")
    private Integer type;

            /**
            * 排序
            */
        @TableField("rank")
    private Integer rank;

            /**
            * 任务时间
            */
        @TableField("task_date")
    private LocalDateTime taskDate;

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
