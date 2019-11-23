package com.yt.sqlite;

import com.yt.commons.utils.LogUtils;
import org.junit.Test;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * SQLiteTest
 *
 * @author yitao
 * @version 1.0.0
 * @date 2019/11/23 11:50
 */
public class SQLiteTest {
    String create_key="create_table";
    final String DB_PATH = "src/test/resources/";
    final String SQL_PATH="SQLite-sql.properties";
    static Properties properties;

    @Test
    public void createDBAndTable() throws Exception {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(properties.getProperty(create_key));


        statement.close();
        connection.close();
    }


    @Test
    public void  add() throws Exception{
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        String insert="insert INTO user(user_name,password,mobile,email,enable,is_lock,expire,remark,creator,create_time,editor,edit_time,last_time) values ('易涛"+System.currentTimeMillis()+"','"+System.currentTimeMillis()+"','18589707789','123',1,1,'2020-12-01 00:10:47','','','2017-07-08 00:10:07','','2017-07-08 00:10:07','2018-12-11 22:12:45.635');";
        statement.executeUpdate(insert);
        ResultSet resultSet=statement.executeQuery("select last_insert_rowid() AS max_id from user limit 1;");
        connection.commit();
        int maxId=resultSet.getInt("max_id");
        LogUtils.LOGGER.info("last_insert_rowid() get maxId:{}",maxId);
        resultSet.close();
        statement.close();

        String update="update user set expire= ?,remark=? WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setDate(1,new Date(System.currentTimeMillis()));
        preparedStatement.setString(2,"update remark");
        preparedStatement.setInt(3,maxId);
        preparedStatement.execute();
        connection.commit();

        String select="select * from  user WHERE id=?";
        preparedStatement = connection.prepareStatement(select);
        preparedStatement.setInt(1,maxId);

        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            LogUtils.LOGGER.info("id:{}",resultSet.getInt("id"));
            LogUtils.LOGGER.info("expire:{}",resultSet.getDate("expire"));
            LogUtils.LOGGER.info("remark:{}",resultSet.getString("remark"));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void addAndGetId() throws Exception{
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        String insert="insert INTO user(user_name,password,mobile,email,enable,is_lock,expire,remark,creator,create_time,editor,edit_time,last_time) values ('易涛"+System.currentTimeMillis()+"','"+System.currentTimeMillis()+"','18589707789','123',1,1,'2020-12-01 00:10:47','','','2017-07-08 00:10:07','','2017-07-08 00:10:07','2018-12-11 22:12:45.635');";
        statement.executeUpdate(insert);
        ResultSet keyResult=statement.getGeneratedKeys();
        if (keyResult.next()){
            LogUtils.LOGGER.info("generatedKeys get max id:{}",keyResult.getInt(1));
        }

        ResultSet resultSet=statement.executeQuery("select last_insert_rowid() AS max_id from user limit 1;");
        int maxId=resultSet.getInt("max_id");
        LogUtils.LOGGER.info("last_insert_rowid() get maxId:{}",maxId);
        connection.commit();
        resultSet.close();
        statement.close();
        connection.close();
    }


    private Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
        Properties prop= new Properties();
        prop.load(this.getClass().getClassLoader().getResourceAsStream(SQL_PATH));

        properties= PropertiesLoaderUtils.loadAllProperties(SQL_PATH);
        Class.forName("org.sqlite.JDBC");
        File file = new File(DB_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }
        return DriverManager.getConnection("jdbc:sqlite:"+DB_PATH+"test.db");
    }
    
}
