package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.baseDAO.BaseDAO;
import com.atguigu.qqzone.dao.HostReplyDAO;
import com.atguigu.qqzone.pojo.HostReply;

/**
 * @author Yan
 * @create 2023-04-29 14:20
 **/
public class HostReplyDAOImpl extends BaseDAO<HostReply> implements HostReplyDAO {
    @Override
    public HostReply getHostReplyById(Integer replyId) {
        return load("select * from t_host_reply where reply = ?",replyId);
    }

    @Override
    public void delHostReply(Integer id) {
        super.executeUpdate("delete from t_host_reply where id = ?",id);
    }
}
