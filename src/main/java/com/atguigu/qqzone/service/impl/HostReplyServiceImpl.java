package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.HostReplyDAO;
import com.atguigu.qqzone.pojo.HostReply;
import com.atguigu.qqzone.service.HostReplyService;

/**
 * @author Yan
 * @create 2023-04-29 14:17
 **/
public class HostReplyServiceImpl implements HostReplyService {
    private HostReplyDAO hostReplyDAO;
    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return hostReplyDAO.getHostReplyById(replyId);
    }

    @Override
    public void delHostReply(Integer id) {
        hostReplyDAO.delHostReply(id);
    }

}
