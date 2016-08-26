package com.yt;

import com.yt.commons.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * IntTest
 *
 * @author yitao
 * @version 1.0.0
 * @date 2016/8/25 13:50
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring.xml" })
public class IntTest {

    @Test
    public void Integertest(){
        Integer t=-129;
        Integer  t1=-129;
        DateUtils.log.info("t==t1:"+(t==t1));

        Integer a=-128;
        Integer  a1=-128;
        DateUtils.log.info("a==a1:"+(a==a1));
        int i= -129;
        int i1=-129;

        DateUtils.log.info("(i==i1):"+(i==i1));
    }
}
