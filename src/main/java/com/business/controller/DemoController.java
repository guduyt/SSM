package com.business.controller;

import com.business.service.DemoService;
import com.business.vo.DemoVO;
import com.yt.commons.exceptions.BusinessException;
import com.yt.commons.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * DemoController
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/1/20 19:20
 */
@Controller
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private DemoService service;


    @ResponseBody
    @RequestMapping
    public List<DemoVO> query() {
        return service.query();
    }

    @ResponseBody
    @RequestMapping("/{id}")
    public DemoVO queryById(@PathVariable int id) {
        return service.queryById(id);
    }

    @ResponseBody
    @RequestMapping("/query")
    public DemoVO queryByName(String name) {
        return service.queryByName(name);
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public boolean save(@Validated(DemoVO.Insert.class) @RequestBody DemoVO demoVO) {
        return service.save(demoVO);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public boolean update(@RequestBody DemoVO demoVO) {
        return service.update(demoVO);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE)
    public boolean delete(int id) {
        return service.delete(id);
    }


    @ResponseBody
    @RequestMapping("/error")
    public boolean getException(HttpServletRequest request, HttpServletResponse response) {
        throw new BusinessException("抛出异常");
    }

    @ResponseBody
    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        FileUtils.downloadFile("/uploadFiles/test.txt", "demo.txt", request, response);
    }

    @ResponseBody
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importExcel(HttpServletRequest request) {
        return FileUtils.getUploadFile(null, "test.txt", request);
    }
}
