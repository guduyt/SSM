package com.business.service.impl;

import com.business.service.UsersService;
import com.entity.auto.mapper.SysUserMapper;
import com.entity.auto.model.SysUser;
import com.entity.auto.model.SysUserExample;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by yt on 2018/4/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring.xml" })
public class UsersServiceImplTest {

    @InjectMocks
    @Autowired
    private UsersService usersService;

    @Mock
    private SysUserMapper usersMapper;

    

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        usersService=new UsersServiceImpl();
        ReflectionTestUtils.setField(usersService,"usersMapper",usersMapper);
    }
    
    @Test
    public void getUsers() throws Exception {
        Mockito.when(usersMapper.selectPageByExample(Mockito.any())).thenReturn(Lists.newArrayList(getData()));
        ArgumentCaptor<SysUserExample> argumentCaptor=ArgumentCaptor.forClass(SysUserExample.class);
        
        List<SysUser> list=usersService.getUsers();
        Assert.assertEquals(list.size(),1);
        verify(usersMapper).selectPageByExample(argumentCaptor.capture());

        SysUserExample sysUserExample=argumentCaptor.getValue();
        Assert.assertEquals(sysUserExample.getPageSize(),10);
        
    }

    @Test
    public void getById() throws Exception {

    }

    @Test
    public void insert() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    private SysUser getData(){
        SysUser sysUser=  new SysUser();
        sysUser.setId(1L);
        sysUser.setUserName("test");
        return sysUser;
    }

}