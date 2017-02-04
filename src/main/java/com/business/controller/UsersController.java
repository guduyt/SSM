package com.business.controller;

import com.business.service.UsersService;
import com.entity.auto.model.SysUser;
import com.yt.commons.utils.ExcelUtils;
import com.yt.commons.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/page")
    public String getPage(@RequestHeader("host") String host, @CookieValue(value = "JSESSIONID", defaultValue = "") String cookie, Model model) {
        UserDetails userInfo = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("name", "我是好人");
        model.addAttribute("cookie", cookie);

        return "/html/page";
    }

    @ResponseBody
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public void importExcel(HttpServletRequest request) {
        String s = FileUtils.getUploadFile(null, "test.xlsx", request);
        List<Object[]> objects = ExcelUtils.getExcelData(s, 1, 0);
        objects.size();
    }

    @ResponseBody
    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        FileUtils.downloadFile("/uploadFiles/test.txt", "test关键字.txt", request, response);

    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE)
    public int delete(@RequestBody SysUser users) {

        return this.usersService.delete(users);
    }

    @ResponseBody
    @RequestMapping("/get")
    public SysUser toIndex(@RequestHeader("host") String host, @CookieValue(value = "JSESSIONID", defaultValue = "") String cookie, @RequestParam(defaultValue = "1") long id) {
        return this.usersService.getById(id);
    }

    @ResponseBody
    @RequestMapping("/get/{id}")
    public SysUser getById(@PathVariable long id) {
        return this.usersService.getById(id);
    }

    @ResponseBody
    @RequestMapping("/getlist")
    public List<SysUser> getList() {

        return this.usersService.getUsers();
    }


}
