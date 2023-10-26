package com.liangzubiao.utiljdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
* @Description: 封装了与连接池的获取和释放连接的方法
* @Author: Nvgu
* @CreateDate: 2023/10/25
* @ModifiedDate: 2023/10/25
*/
public class Connector {

    static private DataSource dataSource;

    static {//在类加载时为获取数据源
        Properties properties = new Properties();
        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(resourceAsStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //关闭（可选择）连接
    public static void releaseConnect(Connection connection, Statement statement, ResultSet resultSet) {
        try (connection; statement; resultSet) {
            // 不再需要显式关闭连接、语句和结果集
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //获取连接
    public static Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }

    //获取数据源
    public static DataSource getDataSource() {
        return dataSource;
    }
}
