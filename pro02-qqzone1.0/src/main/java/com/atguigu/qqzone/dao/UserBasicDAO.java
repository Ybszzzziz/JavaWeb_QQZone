package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author Yan
 * @create 2023-04-28 16:19
 **/
public interface UserBasicDAO {
    //根据账号密码获取特定用户信息
    public UserBasic getUserBasic(String loginId, String pwd);
    //获取指定用户的所有好友列表
    public List<UserBasic> getUserBasicUserList(UserBasic userBasic);
    //根据id查询UserBasic的信息
    UserBasic getUserBasciById(Integer id);
}

