package cn.Ideal.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author wwwwy
 * @since 2022-05-06
 */
@TableName("image_group")
@ApiModel(value = "ImageGroup对象", description = "")
public class ImageGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("图片组id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("标题")
    private String groupTitle;

    @ApiModelProperty("封面")
    private String groupImage;

    @ApiModelProperty("标签记录id,用逗号间隔")
    private String groupLabel;

    @ApiModelProperty("回复数")
    private Integer replyNum;

    @ApiModelProperty("点击量")
    private Integer clickNum;

    @ApiModelProperty("点击量")
    private Integer thumbNum;

    @ApiModelProperty("插入日期")
    private LocalDateTime insertDate;

    @ApiModelProperty("更新日期")
    private LocalDateTime updateDate;

    @ApiModelProperty("软删除  0:未删除 1:已删除")
    private Integer isDeleted;

    @ApiModelProperty("创建人用户id")
    private String userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }
    public String getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(String groupImage) {
        this.groupImage = groupImage;
    }
    public String getGroupLabel() {
        return groupLabel;
    }

    public void setGroupLabel(String groupLabel) {
        this.groupLabel = groupLabel;
    }
    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }
    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }
    public Integer getThumbNum() {
        return thumbNum;
    }

    public void setThumbNum(Integer thumbNum) {
        this.thumbNum = thumbNum;
    }
    public LocalDateTime getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDateTime insertDate) {
        this.insertDate = insertDate;
    }
    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ImageGroup{" +
            "id=" + id +
            ", groupTitle=" + groupTitle +
            ", groupImage=" + groupImage +
            ", groupLabel=" + groupLabel +
            ", replyNum=" + replyNum +
            ", clickNum=" + clickNum +
            ", thumbNum=" + thumbNum +
            ", insertDate=" + insertDate +
            ", updateDate=" + updateDate +
            ", isDeleted=" + isDeleted +
            ", userId=" + userId +
        "}";
    }
}
