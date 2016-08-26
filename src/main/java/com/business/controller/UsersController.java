package com.business.controller;

import com.business.service.UsersService;
import com.entity.model.Users;
import com.yt.commons.utils.ExcelUtils;
import com.yt.commons.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by YT on 2016/1/26.
 */
@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;


    @ResponseBody
    @RequestMapping(value = "/import",method = RequestMethod.POST)
    public void importExcel(HttpServletRequest request){
      String s= FileUtils.getUploadFile(null,"test.xlsx",request) ;
        List<Object[]> objects= ExcelUtils.getExcelData(s,1,0);
    }

    @ResponseBody
    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        FileUtils.downloadFile("/uploadFiles/test.txt","test关键字.txt",request,response);

    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public int insert(@RequestBody Users users) {
        return  this.usersService.insert(users);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public int update(@RequestBody Users users) {

        return  this.usersService.update(users);
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE)
    public int delete(@RequestBody Users users) {

        return  this.usersService.delete(users);
    }

    @ResponseBody
    @RequestMapping("/get")
    public Users toIndex(@RequestHeader ("host") String host,@CookieValue(value="JSESSIONID", defaultValue="") String cookie,@RequestParam(defaultValue = "1") String id) {
        return  this.usersService.getById(id);
    }

    @ResponseBody
    @RequestMapping("/get/{id}")
    public Users getById(@PathVariable String id) {
        return  this.usersService.getById(id);
    }

    @ResponseBody
    @RequestMapping("/getlist")
    public List<Users> getList() {

        return  this.usersService.getUsers();
    }



}
