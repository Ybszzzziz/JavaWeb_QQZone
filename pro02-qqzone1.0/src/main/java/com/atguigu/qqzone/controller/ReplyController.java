package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.ReplyService;
import javassist.expr.NewArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author Yan
 * @create 2023-04-30 9:30
 **/
public class ReplyController {
    ReplyService replyService;
    public String addReply(String content, HttpSession session, Integer topicId){
        UserBasic author = (UserBasic) session.getAttribute("userBasic");
        Reply reply = new Reply(new Date(), author, content, new Topic(topicId));
        replyService.addReply(reply);
//        session.setAttribute("reply",reply);
        return "redirect:topic.do?operate=topicDetail&id="+topicId;
    }
    public String delReply(Integer replyId , Integer topicId){
        replyService.delReply(replyId);
        return "redirect:topic.do?operate=topicDetail&id="+topicId;

    }
}
