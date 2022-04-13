package com.hygge.vblog.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName bing_wallpaper
 */

public class BingWallpaper implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 版权
     */
    private String copyright;

    /**
     * 来源地址
     */
    private String copyrightlink;

    /**
     * 图片地址
     */
    private String url;

    /**
     * 
     */
    private String urlbase;

    /**
     * 开始日期
     */
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    private Date startdate;

    /**
     * 时间
     */
    @JsonFormat(pattern = "yyyyMMddHHmm", timezone = "GMT+8")
    private Date fullstartdate;

    /**
     * 结束日期
     */
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    private Date enddate;

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 版权
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * 版权
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     * 来源地址
     */
    public String getCopyrightlink() {
        return copyrightlink;
    }

    /**
     * 来源地址
     */
    public void setCopyrightlink(String copyrightlink) {
        this.copyrightlink = copyrightlink;
    }

    /**
     * 图片地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 图片地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     */
    public String getUrlbase() {
        return urlbase;
    }

    /**
     * 
     */
    public void setUrlbase(String urlbase) {
        this.urlbase = urlbase;
    }

    /**
     * 开始日期
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * 开始日期
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * 时间
     */
    public Date getFullstartdate() {
        return fullstartdate;
    }

    /**
     * 时间
     */
    public void setFullstartdate(Date fullstartdate) {
        this.fullstartdate = fullstartdate;
    }

    /**
     * 结束日期
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * 结束日期
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

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
        BingWallpaper other = (BingWallpaper) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getCopyright() == null ? other.getCopyright() == null : this.getCopyright().equals(other.getCopyright()))
            && (this.getCopyrightlink() == null ? other.getCopyrightlink() == null : this.getCopyrightlink().equals(other.getCopyrightlink()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getUrlbase() == null ? other.getUrlbase() == null : this.getUrlbase().equals(other.getUrlbase()))
            && (this.getStartdate() == null ? other.getStartdate() == null : this.getStartdate().equals(other.getStartdate()))
            && (this.getFullstartdate() == null ? other.getFullstartdate() == null : this.getFullstartdate().equals(other.getFullstartdate()))
            && (this.getEnddate() == null ? other.getEnddate() == null : this.getEnddate().equals(other.getEnddate()));
    }

    @Override
    public int hashCode() {
        final int prime = 7;
        int result = 13;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getCopyright() == null) ? 0 : getCopyright().hashCode());
        result = prime * result + ((getCopyrightlink() == null) ? 0 : getCopyrightlink().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getUrlbase() == null) ? 0 : getUrlbase().hashCode());
        result = prime * result + ((getStartdate() == null) ? 0 : getStartdate().hashCode());
        result = prime * result + ((getFullstartdate() == null) ? 0 : getFullstartdate().hashCode());
        result = prime * result + ((getEnddate() == null) ? 0 : getEnddate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", copyright=").append(copyright);
        sb.append(", copyrightlink=").append(copyrightlink);
        sb.append(", url=").append(url);
        sb.append(", urlbase=").append(urlbase);
        sb.append(", startdate=").append(startdate);
        sb.append(", fullstartdate=").append(fullstartdate);
        sb.append(", enddate=").append(enddate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}