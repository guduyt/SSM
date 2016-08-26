package com.yt.commons;

import com.yt.commons.utils.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring.xml" })
public class FileDateUtilsTest {

    @Test
    public void testDeleteUploadFile(){
        FileUtils.deleteUploadFile("E:/Java/demo/out/artifacts/demo_war_exploded/uploadFiles/数据库修改数据备份69cc098d-c2d5-4e5b-bfcf-da48358e40e2.xlsx");
        Assert.assertTrue(true);
    }

    @Test
    public void testGetFile() throws Exception {
       File file= FileUtils.getFile("E:/Java/demo/out/artifacts/demo_war_exploded/uploadFiles/", "test.txt");

        Assert.assertTrue(true);
    }

    @Test(expected = NullPointerException.class)
    public void testGetFileIsNull() throws Exception {
        File file= FileUtils.getFile("E:/Java/demo/out/artifacts/demo_war_exploded/uploadFiles/", null);

        Assert.assertTrue(true);
    }

    @Test
    public void testGetFile1() throws Exception {

    }

    @Test
    public void testOpenInputStream() throws Exception {

        FileInputStream inputStream= FileUtils.openInputStream(FileUtils.getFile("E:/Java/demo/out/artifacts/demo_war_exploded/uploadFiles/", "test.txt")) ;
        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream,"gbk")) ;
        String line;
        while((line = reader.readLine()) !=null){
            System.out.println(line);
        }

        Assert.assertTrue(true);
    }

    @Test
    public void testOpenOutputStream() throws Exception {

    }

    @Test
    public void testOpenOutputStream1() throws Exception {

    }

    @Test
    public void testForceMkdir(){

    }
}