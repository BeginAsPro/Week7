package org.example.Week7;

import Week6.Dao.CustomerDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class InsertDataJDBC {
    private final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String DB_URL = "jdbc:mysql://localhost:3306/schema_name";
    private final static String USER = "root";
    private final static String PASSWORD = "ld8221266";
    private ArrayBlockingQueue<Connection> conn = new ArrayBlockingQueue<Connection>(20);

    private ThreadPoolExecutor executor;

    private void init() {
        int core = Runtime.getRuntime().availableProcessors() * 4;
        int timeout = 1000;
        int size = 1000;
        executor = new ThreadPoolExecutor(core, core,
                timeout, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(size), new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 20; i++) {
            try {
                Class.forName(JDBC_DRIVER);
                Connection connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
                connection.setAutoCommit(false);
                conn.put(connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Connection getConn() {
        try {
            return conn.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void close(Connection connection) {
        try {
            conn.put(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insertData() {
        insetBatchMain();
    }

    private void insetBatchMain() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            ArrayList<CustomerDao> list = new ArrayList<>(100000);
            for (int j = 0; j < 100000; j++) {
                CustomerDao customerDao = new CustomerDao();
                customerDao.setId(i);
                customerDao.setName("zyh");
                customerDao.setGender('M');
                customerDao.setEmail("11122@qq.com");
                customerDao.setIdentityType(1);
                customerDao.setIdentityNum("43229932030XXXXX");
                customerDao.setPhoneNum(127282892);
                customerDao.setCreatedTime(new Date(System.currentTimeMillis()));
                customerDao.setModifiedTime(new Date(System.currentTimeMillis()));
                list.add(customerDao);
            }
            long insertStart = System.currentTimeMillis();
            insertBatch(list);
            System.out.println("insert one batch time : " + (System.currentTimeMillis() - insertStart));
        }

        System.out.println("insert batch time : " + (System.currentTimeMillis() - start));
    }

    private void insertBatch(ArrayList<CustomerDao> customers) {
        Connection connection = getConn();
        try {
            String insertSql = "INSERT INTO `customer` (`id`,`name`, `identify_type`,`identify_num`,`phone_num`,`email`,`gender`,`created_time`,`modified_time`) " +
                    "VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            customers.forEach(customerDao -> {
                try {
                    preparedStatement.setInt(1, customerDao.getId());
                    preparedStatement.setString(2, customerDao.getName());
                    preparedStatement.setInt(3, customerDao.getIdentityType());
                    preparedStatement.setString(4, customerDao.getIdentityNum());
                    preparedStatement.setInt(5, customerDao.getPhoneNum());
                    preparedStatement.setString(6, customerDao.getEmail());
                    preparedStatement.setString(7, customerDao.getGender().toString());
                    preparedStatement.setDate(8, new Date(customerDao.getCreatedTime().getTime()));
                    preparedStatement.setDate(9, new Date(customerDao.getModifiedTime().getTime()));
                    preparedStatement.addBatch();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            });
            preparedStatement.executeBatch();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            close(connection);
        }
    }

    public static void main(String[] args) {
        InsertDataJDBC insertDataJDBC = new InsertDataJDBC();
        insertDataJDBC.init();
        insertDataJDBC.insertData();
    }
}
