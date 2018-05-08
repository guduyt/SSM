package com.yt.commons;

import java.util.List;

/**
 * Created by yt on 2016/8/26.
 */
public interface IBatchExecutor<T> {

    List<T> batchInsert(List<T> list) ;

    List<T> batchInsertSelective(List<T> list);
}
