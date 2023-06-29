package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.HostReply;

/**
 * @author Yan
 * @create 2023-04-29 14:16
 **/
public interface HostReplyService {
    HostReply getHostReplyByReplyId(Integer replyId);
    //删除主任回复
    void delHostReply(Integer id);
}
