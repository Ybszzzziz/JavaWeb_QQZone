package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author Yan
 * @create 2023-04-28 16:48
 **/
public interface TopicService {
    //查询特定用户的日志列表
    List<Topic> getTopicList (UserBasic userBasic);
}
