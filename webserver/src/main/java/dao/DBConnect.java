package dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 * 该类提供了建立和关闭数据库连接的方法。
 */
public class DBConnect {

    /**
     * 使用 Druid 数据源建立数据库连接。
     *
     * @return 数据库的 Connection 对象
     * @throws RuntimeException 如果在连接过程中发生错误
     */
    public static Connection getConn() {
        try {
            Properties properties = new Properties();
//            System.out.println(System.getProperty("user.dir"));

            properties.load(new FileInputStream("E:\\Work\\Project\\IDEAProject\\WebSSO\\webserver\\src\\druid.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            Connection connection = dataSource.getConnection();
            System.out.println("连接成功:" + connection);
            return connection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭给定的数据库连接和声明。
     *
     * @param conn 要关闭的 Connection 对象
     * @param stmt 要关闭的 Statement 对象
     */
    public static void closeConn(Connection conn, Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }

//    public static void main(String[] args) {
//        System.out.println(System.getProperty("user.dir"));
//        Connection conn=getConn();
//
//    }
}
