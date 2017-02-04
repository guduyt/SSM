package com.yt.security;

import com.entity.auto.mapper.SysRoleMapper;
import com.entity.auto.model.SysRole;
import com.entity.auto.model.SysRoleExample;
import com.entity.manual.mapper.UserSecurityMapper;
import com.entity.manual.model.Resource;
import com.entity.manual.model.User;
import com.yt.commons.Page;
import com.yt.commons.utils.LogUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring.xml" })
public class UserDAOImplTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserSecurityMapper mapper;

    @Autowired
    private  SysRoleMapper sysRole;

    @Test
    public void TestRole(){
        SysRoleExample example=new SysRoleExample();
       List<SysRole>list= sysRole.selectPageByExample(example);
    }

    @Test
    public void testQueryUserAndRoles() throws Exception {
        List<User> list= userDAO.queryUserAndRoles(null) ;
    }

    @Test
    public void testQueryResourcesAndRoles() throws Exception {
        List<Resource> list=userDAO.queryResourcesAndRoles("页面4");

    }

    @Autowired
    private SysRoleMapper sysRolesMapper;

    @Test
    public void testMapper(){
        SysRoleExample sysRolesExample=new SysRoleExample();
       LogUtils.LOGGER.info("数据"+ sysRolesMapper.countByExample(sysRolesExample));
        SysRole sysRoles= sysRolesMapper.selectByPrimaryKey(4);
        sysRoles.setDescription("test");
        sysRoles.setEnable(false);
        sysRolesMapper.updateByPrimaryKey(sysRoles)   ;
        sysRoles.setId(null);
        sysRoles.setDescription("test1");
        sysRolesMapper.insert(sysRoles) ;
        Page page=new Page(3,1);
        sysRolesExample.setCurrentPage(page.getCurrentPage());
        sysRolesExample.setPageSize(page.getPageSize());
        List<SysRole> list= sysRolesMapper.selectPageByExample(sysRolesExample);


    }
}