package org.example.Week7.common;
import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class JdbcUtils {
    private static String url = "jdbc:mysql://localhost:3306/schema_name";
    private static String userName = "root";
    private static String pwd = "ld8221266";

    public static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, pwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static DataSource getDateSource() {
        DataSource dataSource = null;
        try {
            File file = new ClassPathResource("shardingJdbc.yml").getFile();
            dataSource = YamlShardingDataSourceFactory.createDataSource(file);
        } catch (IOException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
        if(null != rs){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(null != ps) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(null != con) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}