package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.baseDAO.BaseDAO;
import com.atguigu.qqzone.dao.TopicDAO;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author Yan
 * @create 2023-04-28 16:34
 **/
public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return super.executeQuery("select * from t_topic where author = ?",userBasic.getId());

    }

    @Override
    public void addTopic(Topic topic) {

    }

    @Override
    public void delTopic(Topic topic) {
        super.executeUpdate("delete from t_topic where id = ?",topic.getId());
    }

    @Override
    public Topic getTopic(Integer id) {

        return load("select * from t_topic where id = ?",id);
    }
}
