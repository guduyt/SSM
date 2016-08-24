package com.yt.commons;

import com.entity.mapper.UsersMapper;
import com.entity.model.UsersExample;
import com.yt.commons.cache.ShardedJedisCache;
import com.yt.mybatis.model.BaseModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.ShardedJedisPool;

import java.math.BigInteger;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring.xml" })
public class UtilsTest {



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
        com.yt.commons.Page page=new Page();

        UsersExample example=new UsersExample();



               Utils.log.info(""+page.getEndRow());
        Utils.log.info(""+page.getTotalPage());

        example.setPage(page);
        UsersExample.Criteria criteria= example.createCriteria();
        criteria.andAgeBetween(20,65) ;
        page.setPageSize(20);


        List<? extends BaseModel> list= usersMapper.selectByExample(example) ;

        page.setCurrentPage(5);
        example.setPage(page);
        list=usersMapper.selectPageByExample(example) ;
        page.setCurrentPage(3);
        example.setPage(page);
        list=usersMapper.selectPageByExample(example) ;
    }



}