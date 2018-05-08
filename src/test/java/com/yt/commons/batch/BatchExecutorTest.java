package com.yt.commons.batch;

import com.entity.auto.mapper.SysUserMapper;
import com.entity.auto.model.SysRole;
import com.entity.auto.model.SysUser;
import com.google.common.collect.Lists;
import com.yt.commons.utils.DateUtils;
import com.yt.commons.utils.LogUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring.xml" })
public class BatchExecutorTest {

    @Autowired
    private SysUserMapper sysUsersMapper;
    /*批量插入数据测试用例*/
    @Test
    public void testBatchInsert() throws Exception {
        BatchExecutor batchExecutor=new BatchExecutor(3);

        List<SysUser> list=new ArrayList<>();
        List<SysRole> roles= Lists.newArrayList();
        SysRole sysRole=new SysRole();
        sysRole.setRoleName("test"+System.currentTimeMillis());
        sysRole.setDescription(sysRole.getRoleName());
        sysRole.setEnable(true);
        roles.add(sysRole);
        
        for (int i=41;i<52;i++){
            SysUser users=new SysUser();
            users.setId((long)i);
            users.setPassword("test" + i);
            users.setUserName("test" + i);
            users.setMobile("123");
            users.setEmail("123");
            users.setIsLock(true);
            users.setEnable(true);
            users.setCreator("test");
            users.setCreateTime(new Date());
            users.setRemark(null);
            users.setExpire(DateUtils.stringToDateForFormat("2020-01-01"));
            //sysUsersMapper.insert(users);
            list.add(users) ;
        }
        long start= System.currentTimeMillis();
        int result=list.size();

        batchExecutor.batchInsertSelective(list);
        LogUtils.LOGGER.info("总共时间："+(System.currentTimeMillis()-start));

        Assert.assertEquals(list.size(),result);
    }
}