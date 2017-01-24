package com.yt.commons.cache;

/**
 * RedisCache
 *
 * @author yitao
 * @version 1.0.0
 * @date 2016/7/12 15:57
 */
/*@Service("RedisCache")*/
public class RedisCache {

   /* @Autowired
    private RedisTemplate<String, String> redisTemplate;




    *//**
     * 通过key删除（字节）
     * @param key
     *//*
    public void del(final byte [] key){
        redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.del(key);
                return true;
            }
        });
    }

    *//**
     * 通过key删除
     * @param key
     *//*
    public void del(final String key){

        redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.del(key.getBytes());
                return true;
            }
        });

    }

    public Boolean del(final String... keys){

       return (Boolean) redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                for (int i = 0; i < keys.length; i++) {
                     connection.del(keys[i].getBytes());
                }
                return true;
            }
        });
    }

    *//**
     * 添加key value 并且设置存活时间(byte)
     * @param key
     * @param value
     * @param liveTime
     *//*
    public void set(final byte [] key,final byte [] value,final int liveTime){
        redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                connection.expire(key, liveTime);
                return 1L;
            }
        });
    }
    *//**
     * 添加key value 并且设置存活时间
     * @param key
     * @param value
     * @param liveTime
     *//*
    public void set(final String key,final String value,int liveTime){
        redisTemplate.opsForValue().set(key,value,liveTime);
    }
    *//**
     * 添加key value
     * @param key
     * @param value
     *//*
    public void set(final String key,final String value){
        redisTemplate.opsForValue().set(key,value);
    }
    *//**添加key value (字节)(序列化)
     * @param key
     * @param value
     *//*
    public void set(final byte [] key,final byte [] value){
        redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                return 1L;
            }
        });
    }
    *//**
     * 获取redis value (String)
     * @param key
     * @return
     *//*
    public String get(final String key){
       return redisTemplate.opsForValue().get(key).toString();
    }
    *//**
     * 获取redis value (byte [] )(反序列化)
     * @param key
     * @return
     *//*
    public byte[] get(final byte [] key){

        return (byte[]) redisTemplate.execute(new RedisCallback() {
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.get(key);
            }
        });
    }

    *//**
     * 通过正则匹配keys
     * @param pattern
     * @return
     *//*
    public Set<String> keys(final String pattern){

        return redisTemplate.keys(pattern);

    }

    *//**
     * 检查key是否已经存在
     * @param key
     * @return
     *//*
    public boolean exists(final String key){
        return (Boolean)redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(key.getBytes());
            }
        });
    }
    *//**
     * 清空redis 所有数据
     * @return
     *//*
    public String flushDB() {
        return (String)redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }
    *//**
     * 查看redis里有多少数据
     *//*
    public long dbSize(){
        return (Long) redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
    }

    *//**
     * 检查是否连接成功
     * @return
     *//*
    public String ping(){

        return (String) redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection)  throws DataAccessException {
                return connection.ping();
            }
        });
    }*/


     /*redisTemplate.execute(new RedisCallback<Boolean>(){
           String s;
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> redisSerializer = (RedisSerializer<String>) redisTemplate .getKeySerializer();
                s=redisSerializer.deserialize(redisConnection.get(redisSerializer.serialize("yt")));
                s=redisSerializer.deserialize(redisConnection.getSet(redisSerializer.serialize("yt"),redisSerializer.serialize("缓存测试数据")));
                return true;
            }
        });*/
}
