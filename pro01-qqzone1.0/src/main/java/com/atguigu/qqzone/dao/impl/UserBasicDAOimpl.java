package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.baseDAO.BaseDAO;
import com.atguigu.qqzone.dao.UserBasicDAO;
import com.atguigu.qqzone.pojo.UserBasic;

import java.sql.Connection;
import java.util.List;

/**
 * @author Yan
 * @create 2023-04-28 16:25
 **/
public class UserBasicDAOimpl extends BaseDAO<UserBasic> implements UserBasicDAO {
    @Override
    public UserBasic getUserBasic(String loginId, String pwd) {
        return super.load("select * from t_user_basic where loginId = ? and pwd = ? ",loginId,pwd);
    }

    @Override
    public List<UserBasic> getUserBasicUserList(UserBasic userBasic) {
        String sql = "select fid as id from t_friend where uid = ?";
        return super.executeQuery(sql,userBasic.getId());
    }

    @Override
    public UserBasic getUserBasciById(Integer id) {
        return super.load("select * from t_user_basic where id = ?",id);
    }
}
