package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.baseDAO.BaseDAO;
import com.atguigu.qqzone.dao.ReplyDAO;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;

import java.util.List;

/**
 * @author Yan
 * @create 2023-04-29 14:46
 **/
public class ReplyDAOImpl extends BaseDAO<Reply> implements ReplyDAO {
    @Override
    public List<Reply> getReplyList(Topic topic) {
        return executeQuery("select * from t_reply where topic = ?",topic.getId());
    }

    @Override
    public void addReply(Reply reply) {
        executeUpdate("insert into t_reply values (0,?,?,?,?)",reply.getContent(),reply.getReplyDate(),reply.getAuthor().getId(),reply.getTopic().getId());
    }

    @Override
    public void delReply(Integer id) {
        executeUpdate("delete from t_reply where id = ?",id);
    }

    @Override
    public Reply getReply(Integer id) {
        return load("select * from t_reply where id = ?",id);
    }
}
