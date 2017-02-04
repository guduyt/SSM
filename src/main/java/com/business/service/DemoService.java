package com.business.service;

import com.business.vo.DemoVO;

import java.util.List;

/**
 * DemoService
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/1/20 19:22
 */
public interface DemoService {

    List<DemoVO> query();
    DemoVO queryById(int id);
    DemoVO queryByName(String name);
    boolean save(DemoVO demoVO);
    boolean update(DemoVO demoVO);
    boolean delete(int id);

}
