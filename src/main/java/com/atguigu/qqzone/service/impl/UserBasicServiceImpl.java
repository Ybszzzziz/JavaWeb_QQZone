package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.UserBasicDAO;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.UserBasicService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yan
 * @create 2023-04-28 16:39
 **/
public class UserBasicServiceImpl implements UserBasicService {

    private UserBasicDAO userBasicDAO = null;
    @Override
    public UserBasic login(String loginId, String pwd) {
        UserBasic userBasic = userBasicDAO.getUserBasic(loginId, pwd);
        return userBasic;

    }

    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic) {
        List<UserBasic> userBasicList = userBasicDAO.getUserBasicUserList(userBasic);
        List<UserBasic> friendList = new ArrayList<>(userBasicList.size());
        for (UserBasic friend : userBasicList) {
            friend = userBasicDAO.getUserBasciById(friend.getId());
            friendList.add(friend);
        }
        return friendList;
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        return userBasicDAO.getUserBasciById(id);
    }
}
