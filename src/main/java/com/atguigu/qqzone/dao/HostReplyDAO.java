package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.HostReply;

/**
 * @author Yan
 * @create 2023-04-28 16:24
 **/
public interface HostReplyDAO {
    //根据replyId查询关联的HostReply实体
    HostReply getHostReplyById(Integer id);
    //删除特定的HostReply
    void delHostReply(Integer id);
}
