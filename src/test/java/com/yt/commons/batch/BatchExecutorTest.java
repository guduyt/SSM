package com.yt.commons.batch;

import com.entity.model.Users;
import com.yt.commons.utils.Util;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring.xml" })
public class BatchExecutorTest {

    /*批量插入数据测试用例*/
    @Test
    public void testBatchInsert() throws Exception {
        BatchExecutor batchExecutor=new BatchExecutor();

        List<Users> list=new ArrayList<>();
        for (int i=51;i<2150;i++){
            Users users=new Users();
            users.setId("111"+i);
            users.setAge(22+i);
            users.setName("test"+i);
            list.add(users) ;
        }
        long start= System.currentTimeMillis();
        int result=batchExecutor.batchInsert(list);
        Util.log.info("总共时间："+(System.currentTimeMillis()-start));

        Assert.assertEquals(list.size(),result);
    }
}