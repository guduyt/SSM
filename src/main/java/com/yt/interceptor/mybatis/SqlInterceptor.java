package com.yt.interceptor.mybatis;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;

/**
 * SqlInterceptor
 *
 * @author yitao
 * @version 1.0.0
 * @date 2016/8/23 16:36
 */
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class,Integer.class})})
public class SqlInterceptor implements Interceptor {
    public static final Logger log= LoggerFactory.getLogger(SqlInterceptor.class);
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (!(invocation.getTarget() instanceof RoutingStatementHandler))
            return invocation.proceed();
        if(printSql) {
            RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
            BoundSql boundSql = handler.getBoundSql();

            String sqlString = boundSql.getSql();
            if (null != sqlString) {
                sqlString = sqlString.replaceAll("\\n", " ");
            }
            log.info("执行sql语句：" + sqlString);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return (o instanceof StatementHandler)?Plugin.wrap(o, this):o;
    }

    @Override
    public void setProperties(Properties properties) {

    }


    public boolean isPrintSql() {
        return printSql;
    }

    public void setPrintSql(boolean printSql) {
        this.printSql = printSql;
    }

    private boolean printSql;

    private String dialect;


    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }
}
