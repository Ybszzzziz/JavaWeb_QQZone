package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.TopicService;
import com.atguigu.qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Yan
 * @create 2023-04-28 16:53
 **/
public class UserController {
    private UserBasicService userBasicService;
    private TopicService topicService;
    public String login(String loginId, String pwd, HttpSession session){
        UserBasic userB = userBasicService.login(loginId, pwd);

        if (userB != null){
            List<UserBasic> friendList = userBasicService.getFriendList(userB);
            List<Topic> topicList = topicService.getTopicList(userB);
            userB.setFriendList(friendList);
            userB.setTopicList(topicList);
            session.setAttribute("userBasic",userB);
            return "index";
        }else {

            return "login";
        }
    }
}
