package com.yt.commons.cache;

/**
 * IRedis
 *
 * @author yitao
 * @version 1.0.0
 * @date 2016/7/13 17:12
 */
public interface IRedis {

    /**
     * 通过key删除（字节）
     * @param key
     */
    public void del(byte [] key);

    /**
     * 通过key删除
     * @param key
     */
    public void del(String key);

    /**
     * 添加key value 并且设置存活时间(byte)
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(byte [] key,byte [] value,int liveTime);

    /**
     * 添加key value 并且设置存活时间
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(String key,String value,int liveTime);

    /**
     * 添加key value
     * @param key
     * @param value
     */
    public void set(String key,String value);

    /**添加key value (字节)(序列化)
     * @param key
     * @param value
     */
    public void set(byte [] key,byte [] value);

    /**
     * 获取redis value (String)
     * @param key
     * @return
     */
    public String get(String key);

    /**
     * 获取redis value (byte [] )(反序列化)
     * @param key
     * @return
     */
    public byte[] get(byte [] key);


    /**
     * 检查key是否已经存在
     * @param key
     * @return
     */
    public boolean exists(String key);

}
