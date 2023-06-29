package com.atguigu.myssm.baseDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Yan
 * @create 2023-04-20 20:10
 **/
public class ConnUtil {
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver" ;
    public static final String URL = "jdbc:mysql://localhost:3306/qqzonedb?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    public static final String USER = "root";
    public static final String PWD = "Ybs123123." ;

    public static Connection createConn(){
        try {
            //1.加载驱动
            Class.forName(DRIVER);
            //2.通过驱动管理器获取连接对象
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getConn(){
        Connection conn = threadLocal.get();
        if (conn == null){
            conn = createConn();
            threadLocal.set(conn);
        }
        return threadLocal.get();

    }
    public static void closeConn() throws SQLException {
        Connection conn = threadLocal.get();
        if (conn == null){
            return;
        }
        if (!conn.isClosed()){
            conn.close();
            threadLocal.set(null );
        }

    }


}
