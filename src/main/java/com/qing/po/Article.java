package com.qing.po;


import java.util.Date;


public class Article {
    private Integer id;
    private String title;
    private String content;
    private Integer userId;
    private String userAccout;
    private Date createTime;

    public String getUserAccout() {
        return userAccout;
    }

    public void setUserAccout(String userAccout) {
        this.userAccout = userAccout;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", userAccout='" + userAccout + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
