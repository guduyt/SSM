package com.yt.commons.utils;

import org.junit.Test;

public class ValidatorUtilTest {

    @Test
    public void validatorTest(){
        String s= "d~s\\n5  \"623  234";
      Util.log.info("isSpace:" + ValidatorUtil.isSpace(s));
        Util.log.info("removeSpace:" + ValidatorUtil.removeSpace(s));
        Util.log.info("SpLetter:" + ValidatorUtil.isSpLetter(s));
        Util.log.info("SpLetter:" + ValidatorUtil.isSpLetter("ds\\n5\"623  234"));
        Util.log.info("isPhone:" + ValidatorUtil.isPhone("83343343"));
        Util.log.info("isPhone:" + ValidatorUtil.isPhone("18580707789"));
    }

}