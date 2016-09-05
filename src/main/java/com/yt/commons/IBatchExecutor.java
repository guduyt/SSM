package com.yt.commons;

import java.util.List;

/**
 * Created by yt on 2016/8/26.
 */
public interface IBatchExecutor<T> {

    public int batchInsert(List<T> list) ;
}
