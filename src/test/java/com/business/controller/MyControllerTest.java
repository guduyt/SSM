package com.business.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.ServletContext;

/**
 * Created by yt on 2017-1-20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring.xml" })
@WebAppConfiguration
public class MyControllerTest {


    @Autowired
    ServletContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws JsonProcessingException {
        mockMvc = MockMvcBuilders.standaloneSetup(UsersController.class).build();
    }

    @Test
    public void getString() throws Exception {
        try {

            MvcResult result= mockMvc.perform(MockMvcRequestBuilders.get("/users/getlist").accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)).andReturn();
            result.getResponse();

        }   catch (Exception ex){

        }
    }

}