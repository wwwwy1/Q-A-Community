package cn.Ideal.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value = "Image对象", description = "")
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("图片id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("图片组id")
    private Integer groupId;

    @ApiModelProperty("标题")
    private String imageTitle;

    @ApiModelProperty("图片源地址")
    private String imageSourceUrl;

    @ApiModelProperty("图片预览地址")
    private String imagePreviewUrl;

    @ApiModelProperty("下载数")
    private Integer downloadNum;

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
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }
    public String getImageSourceUrl() {
        return imageSourceUrl;
    }

    public void setImageSourceUrl(String imageSourceUrl) {
        this.imageSourceUrl = imageSourceUrl;
    }
    public String getImagePreviewUrl() {
        return imagePreviewUrl;
    }

    public void setImagePreviewUrl(String imagePreviewUrl) {
        this.imagePreviewUrl = imagePreviewUrl;
    }
    public Integer getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(Integer downloadNum) {
        this.downloadNum = downloadNum;
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
        return "Image{" +
            "id=" + id +
            ", groupId=" + groupId +
            ", imageTitle=" + imageTitle +
            ", imageSourceUrl=" + imageSourceUrl +
            ", imagePreviewUrl=" + imagePreviewUrl +
            ", downloadNum=" + downloadNum +
            ", insertDate=" + insertDate +
            ", updateDate=" + updateDate +
            ", isDeleted=" + isDeleted +
            ", userId=" + userId +
        "}";
    }
}
