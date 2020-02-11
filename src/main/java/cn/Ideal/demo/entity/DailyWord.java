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
    public class DailyWord implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 主键
            */
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            /**
            * 登录名
            */
        @TableField("content")
    private String content;

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
