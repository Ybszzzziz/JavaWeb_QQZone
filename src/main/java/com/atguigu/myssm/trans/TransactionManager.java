package com.atguigu.myssm.trans;

import com.atguigu.myssm.baseDAO.ConnUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Yan
 * @create 2023-04-20 20:04
 **/
public class TransactionManager {
    //开启事务
    public static void beginTrans() throws SQLException {
        Connection conn = ConnUtil.getConn();
        conn.setAutoCommit(false);
    }

    //提交事务
    public static void commit() throws SQLException {
        Connection conn = ConnUtil.getConn();
        conn.commit();
        ConnUtil.closeConn();
    }
    //回滚事务
    public static void rollback() throws SQLException {
        Connection conn = ConnUtil.getConn();
        conn.rollback();
        ConnUtil.closeConn();
    }

}
