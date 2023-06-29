package com.atguigu.qqzone.pojo;

import java.util.Date;

/**
 * @author Yan
 * @create 2023-04-28 16:04
 **/
public class Reply {
    private Integer id;
    private Date replyDate;
    private UserBasic author;   //M:1
    private Topic topic;        //M:1
    private HostReply hostReply;    //1:1
    public Reply(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    public UserBasic getAuthor() {
        return author;
    }

    public void setAuthor(UserBasic author) {
        this.author = author;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public HostReply getHostReply() {
        return hostReply;
    }

    public void setHostReply(HostReply hostReply) {
        this.hostReply = hostReply;
    }
}
