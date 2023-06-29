package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.TopicDAO;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.TopicService;

import java.util.List;

/**
 * @author Yan
 * @create 2023-04-28 16:49
 **/
public class TopicServiceImpl implements TopicService {
    private TopicDAO topicDAO = null;
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }
}
