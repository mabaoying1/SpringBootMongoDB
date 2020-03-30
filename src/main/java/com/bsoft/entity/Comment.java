package com.bsoft.entity;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "comment") //如果省略，默认类名小写
//复合索引
//@CompoundIndex(def="{'userId':1},{'nickName':-1}")
public class Comment implements Serializable {

    @Id
    private String id;
    @Field("content")//属性对应mongodb字段名，如果一致，无须该注解
    private String content;//吐槽内容
    private String articleId;//文章id
    private Date publishTime;//发布日期
    @Indexed  //添加一个单字段的索引
    private String userId;//发布人id
    private String nickName;//发布人昵称
    private Date createDateTime;//评论的日期时间
    private Integer likeNum;//点赞数
    private Integer replyNum;//回复数
    private String state;//状态
    private String parentId;//上级id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", publishTime=" + publishTime +
                ", userId='" + userId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", createDateTime=" + createDateTime +
                ", likeNum=" + likeNum +
                ", replyNum=" + replyNum +
                ", state='" + state + '\'' +
                ", parentId='" + parentId + '\'' +
                ", articleId='" + articleId + '\'' +
                '}';
    }
}
