package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author Yan
 * @create 2023-04-28 16:48
 **/
public interface TopicService {
    //根据id获取特定的topic
    Topic getTopicById(Integer id);
    //查询特定用户的日志列表
    List<Topic> getTopicList (UserBasic userBasic);
    //根据Id获取指定的topic信息，包含这个topic关联的作者信息
    Topic getTopic(Integer id);
    //删除特定的topic
    void delTopic(Integer id);
}
