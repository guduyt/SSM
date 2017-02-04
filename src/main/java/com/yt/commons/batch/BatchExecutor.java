package com.yt.commons.batch;

import com.yt.commons.IBatchExecutor;
import com.yt.commons.exceptions.BusinessException;
import com.yt.commons.utils.ApplicationContextUtils;
import com.yt.mybatis.model.BaseMapper;
import com.yt.mybatis.model.BaseModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 批量插入数据到数据库
 * Created by yt on 2016/8/26.
 */

public class BatchExecutor<T extends BaseModel> implements IBatchExecutor<T> {

    private static final  Logger LOGGER = LoggerFactory.getLogger(BatchExecutor.class);

    private SqlSessionFactory sqlSessionFactory;

    private BaseMapper mapper;

    private int batchSize = 500;

    public BatchExecutor() {
        this.sqlSessionFactory = ApplicationContextUtils.getBeanByClass(SqlSessionFactory.class);
    }

    public BatchExecutor(BaseMapper mapper) {
        this.mapper = mapper;
        this.sqlSessionFactory = ApplicationContextUtils.getBeanByClass(SqlSessionFactory.class);
    }

    public BatchExecutor(BaseMapper mapper, int batchSize) {
        this.mapper = mapper;
        this.batchSize = batchSize;
        this.sqlSessionFactory = ApplicationContextUtils.getBeanByClass(SqlSessionFactory.class);
    }


    @Override
    public int batchInsert(List<T> list) {
        int insertNumber = 0;
        sqlSessionFactory.getConfiguration().addMapper(BaseMapper.class);
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false)) {
            int count = list.size();
            List<T> data = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                insertNumber++;
                T t = list.get(i);
                this.batchInsertProcess(data,t,sqlSession,i,count) ;
            }
        }
        return insertNumber;
    }

    private void batchInsertProcess( List<T> data,T t,SqlSession sqlSession,int number,int count){
        if (null == this.mapper) {
            Class<?> tClass = getMapperClass(t.getClass().getName());
            if (null == tClass) {
                throw new BusinessException(50001, t.getClass().getName() + "实体对应的Mapper加载类失败！");
            }
            this.mapper = (BaseMapper) sqlSession.getMapper(tClass);
        }
        data.add(t);
        if (count == 1 || (number != 0 && (number % this.batchSize == 0 || number == count - 1))) {

            this.mapper.insertBatch(data);
            sqlSession.commit();
            sqlSession.clearCache();

            if(LOGGER.isDebugEnabled())
                LOGGER.info("数据保存成功，成功保存"+data.size()+"条数据");
            data.clear();
        }
    }

    //当没有初始化BaseMapper时，通过model获得对应mapper的类路径;要求MBG生成的model、mapper文件的相对路径默认没有改变。
    private Class<?> getMapperClass(String s) {
        String mapperClass = StringUtils.replaceOnce(s, ".model.", ".mapper.") + "Mapper";
        Class<?> tClass = null;
        try {
            tClass = Class.forName(mapperClass);
        } catch (ClassNotFoundException ex) {
            throw new BusinessException(50033,"路径:" + mapperClass + "下加载类失败！",ex);
        }
        return tClass;
    }


    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void setMapper(BaseMapper mapper) {
        this.mapper = mapper;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }
}
