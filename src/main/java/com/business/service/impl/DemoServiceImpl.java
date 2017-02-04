package com.business.service.impl;

import com.business.service.DemoService;
import com.business.vo.DemoVO;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * DemoServiceImpl
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/1/20 19:23
 */
@Service
public class DemoServiceImpl implements DemoService {

    private List<DemoVO> data = new ArrayList<>();

    @PostConstruct
    private void init() {
        DemoVO demoVO = new DemoVO();
        demoVO.setId(1);
        demoVO.setName("苹果");

        demoVO.setDate(new Date());
        demoVO.setPrice(5);

        DemoVO demoVO1 = new DemoVO();
        demoVO1.setId(2);
        demoVO1.setName("西瓜");

        demoVO1.setDate(new Date());
        demoVO1.setPrice(2);

        DemoVO demoVO2 = new DemoVO();
        demoVO2.setId(3);
        demoVO2.setName("樱桃");

        demoVO2.setDate(new Date());
        demoVO2.setPrice(3);
        data.add(demoVO);
        data.add(demoVO1);
        data.add(demoVO2);
    }

    @Override
    public List<DemoVO> query() {
        return this.data;
    }

    @Override
    public DemoVO queryById(int id) {
        Optional<DemoVO> optional = this.data.stream().filter(p -> p.getId() == id).findFirst();
        return optional.orElse(new DemoVO());
    }

    @Override
    public DemoVO queryByName(String name) {
        Optional<DemoVO> optional = this.data.stream().filter(p -> p.getName().equals(name)).findFirst();
        return optional.orElse(new DemoVO());
    }

    @Override
    public boolean save(DemoVO demoVO) {
        this.data.add(demoVO);
        return true;
    }

    @Override
    public boolean update(DemoVO demoVO) {

        this.data.forEach(d -> {
            if (d.getId() == demoVO.getId()) {
                d = demoVO;
            }
        });
        return true;
    }

    @Override
    public boolean delete(int id) {

        this.data.removeIf(d -> d.getId() == id);
        return true;
    }
}
