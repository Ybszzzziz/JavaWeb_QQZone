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
            //userBasic这个key保存的是登陆着的信息
            //friend这个key保存的是当前进入的是谁的空间的信息
            session.setAttribute("userBasic",userB);
            session.setAttribute("friend",userB);
            return "index";
        }else {

            return "login";
        }
    }
    public String friend(Integer id, HttpSession session){
        //1.根据id获取指定的用户信息
        UserBasic currFriend = userBasicService.getUserBasicById(id);
        List<Topic> topicList = topicService.getTopicList(currFriend);
        currFriend.setTopicList(topicList);
        session.setAttribute("friend",currFriend);
        return "index";
    }

}
