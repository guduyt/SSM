package com.business.controller;

import com.alibaba.fastjson.JSON;
import com.business.vo.DemoVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
/*@ContextConfiguration(locations = { "classpath:config/spring.xml" })*/
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = "classpath:config/spring.xml"),
        @ContextConfiguration(name = "child", locations = "classpath:config/spring-mvc.xml")
})
@WebAppConfiguration(value = "src/main/webapp")
@Profile("dev")
public class DemoControllerTest {

    private MockMvc mockMvc;
    private MockHttpSession session;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.session = new MockHttpSession();
    }


    @Test
    public void testQuery() throws Exception {
       MvcResult result= mockMvc.perform(MockMvcRequestBuilders.get("/demo"))
                                   .andExpect(MockMvcResultMatchers.status().isOk())
                                   .andDo(MockMvcResultHandlers.print())
                                   .andReturn();
        List<DemoVO> list= JSON.parseArray(result.getResponse().getContentAsString(), DemoVO.class) ;
    }

    @Test
    public void testQueryById() throws Exception {
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.get("/demo/{id}",1))
                                    .andExpect(MockMvcResultMatchers.status().isOk())
                                    .andDo(MockMvcResultHandlers.print())
                                    .andReturn();
       DemoVO demoVO= JSON.parseObject(result.getResponse().getContentAsString(), DemoVO.class);
        Assert.assertEquals(1,demoVO.getId());
    }

    @Test
    public void testQueryByName() throws Exception {
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.get("/demo/query").param("name","西瓜"))
                                    .andExpect(MockMvcResultMatchers.status().isOk())
                                    .andDo(MockMvcResultHandlers.print())
                                    .andReturn();
        DemoVO demoVO= JSON.parseObject(result.getResponse().getContentAsString(), DemoVO.class);
        Assert.assertEquals(2,demoVO.getId());
    }

    //json格式数据传输测试
    @Test
    public void testSave() throws Exception {
        DemoVO demoVO = new DemoVO();
        demoVO.setId(11);
        demoVO.setName("桃子");
        demoVO.setDate(new Date());
        demoVO.setPrice(6);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/demo")
                                            .characterEncoding("UTF-8")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(JSON.toJSONString(demoVO))
                                            .accept(MediaType.APPLICATION_JSON))
                                    .andExpect(MockMvcResultMatchers.status().isOk())
                                    .andDo(MockMvcResultHandlers.print())
                                    .andReturn();
        Assert.assertEquals("true", result.getResponse().getContentAsString());
    }


    @Test
    public void testSaveValidated() throws Exception {
        DemoVO demoVO = new DemoVO();
        demoVO.setId(11);
        demoVO.setName("桃子");
        demoVO.setDate(new Date());
        demoVO.setPrice(11);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/demo")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(demoVO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testUpdate() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/demo/query")
                                            .param("name", "苹果"))
                                    .andExpect(MockMvcResultMatchers.status().isOk())
                                    .andDo(MockMvcResultHandlers.print())
                                    .andReturn();
        DemoVO demoVO = JSON.parseObject(result.getResponse().getContentAsString(), DemoVO.class);
        demoVO.setName("火龙果");
        demoVO.setPrice(9.99);
        result =mockMvc.perform(MockMvcRequestBuilders.put("/demo")
                                .characterEncoding("UTF-8")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSON.toJSONString(demoVO))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print()).andReturn();
        Assert.assertEquals("true", result.getResponse().getContentAsString());
    }

    @Test
    public void testDelete() throws Exception {
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.delete("/demo").param("id", "3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assert.assertEquals("true", result.getResponse().getContentAsString());
    }


    @Test(expected = RuntimeException.class)
    public void testException() {
        throw new RuntimeException("测试异常断言");
    }


    @Test
    public void testGetAjaxException() throws Exception  {

        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.get("/demo/error")
                .header("Content Type", MediaType.APPLICATION_JSON)
                .header("X-Requested-With", "XMLHttpRequest")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assert.assertTrue(true);
    }

    @Test
    public void testGetException() throws Exception  {

        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.get("/demo/error"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assert.assertTrue(RuntimeException.class.isAssignableFrom(result.getResolvedException().getClass()));
    }

    //下载
    @Test
    public void testDownload() throws Exception {
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.get("/demo/download"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
       Assert.assertTrue(result.getResponse().getHeaderValue("Content-Disposition").toString().contains("demo.txt"));
    }

    //文件上传
    @Test
    public void testImportExcel() throws Exception {

        MockMultipartFile multipartFile=new MockMultipartFile("test.txt","上传测试数据".getBytes());
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.fileUpload("/demo/import").file(multipartFile))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assert.assertTrue(result.getResponse().getContentAsString().contains("test.txt"));
    }
}