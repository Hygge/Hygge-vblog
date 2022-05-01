package com.hygge.vblog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 文件存储记录表
 * @TableName v_file_record
 */
@TableName(value ="v_file_record")
@Data
public class VFileRecord implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件地址或者链接
     */
    private String fileUrl;

    /**
     * 文件类型 0图片 
     */
    private Integer type;

    /**
     * 文件后缀
     */
    private String fileSuffixName;

    /**
     * 本地文件还是云存储 0本地 1云
     */
    private Integer localOrCloud;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 是否删除 0正常 1删除
     */
    private Integer del = 0;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        VFileRecord other = (VFileRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName()))
            && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath()))
            && (this.getFileUrl() == null ? other.getFileUrl() == null : this.getFileUrl().equals(other.getFileUrl()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getFileSuffixName() == null ? other.getFileSuffixName() == null : this.getFileSuffixName().equals(other.getFileSuffixName()))
            && (this.getLocalOrCloud() == null ? other.getLocalOrCloud() == null : this.getLocalOrCloud().equals(other.getLocalOrCloud()))
            && (this.getSize() == null ? other.getSize() == null : this.getSize().equals(other.getSize()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getFileUrl() == null) ? 0 : getFileUrl().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getFileSuffixName() == null) ? 0 : getFileSuffixName().hashCode());
        result = prime * result + ((getLocalOrCloud() == null) ? 0 : getLocalOrCloud().hashCode());
        result = prime * result + ((getSize() == null) ? 0 : getSize().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fileName=").append(fileName);
        sb.append(", path=").append(path);
        sb.append(", fileUrl=").append(fileUrl);
        sb.append(", type=").append(type);
        sb.append(", fileSuffixName=").append(fileSuffixName);
        sb.append(", localOrCloud=").append(localOrCloud);
        sb.append(", size=").append(size);
        sb.append(", createDate=").append(createDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}