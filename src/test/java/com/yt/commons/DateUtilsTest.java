package com.yt.commons;

import com.entity.mapper.UsersMapper;
import com.yt.commons.utils.ApplicationContextUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring.xml" })
public class DateUtilsTest {



    @Autowired
    private UsersMapper usersMapper;

    private  static int num=0;

    public static void t(){
        try {
            Thread.sleep(2);
        } catch (Exception ex){

        }

        num++;
    }

    @Test
    public void test(){
        DefaultSqlSessionFactory  sqlSessionFactory= ApplicationContextUtils.getBean("sqlSessionFactory");
        try(SqlSession batchSqlSession=sqlSessionFactory.openSession(ExecutorType.BATCH,false)){



        }   catch (Exception e){

        }
    }



}