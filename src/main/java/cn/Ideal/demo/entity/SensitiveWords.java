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
* @since 2020-02-25
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class SensitiveWords implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 敏感词id
            */
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            /**
            * 敏感词内容
            */
        @TableField("content")
    private String content;

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
