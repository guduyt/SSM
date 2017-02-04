package com.yt.interceptor.mybatis;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;

/**
 * SqlInterceptor
 * 打印执行sql语句
 *
 * @author yitao
 * @version 1.0.0
 * @date 2016/8/23 16:36
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class SqlInterceptor implements Interceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Interceptor.class);
    private boolean printSql;
    private String dialect;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (!(invocation.getTarget() instanceof RoutingStatementHandler))
            return invocation.proceed();
        if (printSql) {
            RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
            BoundSql boundSql = handler.getBoundSql();

            String sqlString = boundSql.getSql();
            if (null != sqlString) {
                sqlString = sqlString.replaceAll("\\n", " ");
            }
            if (LOGGER.isDebugEnabled())
                LOGGER.debug("执行sql语句：" + sqlString);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return (o instanceof StatementHandler) ? Plugin.wrap(o, this) : o;
    }


    @Override
    public void setProperties(Properties properties) {
        //
    }

    public boolean isPrintSql() {
        return printSql;
    }

    public void setPrintSql(boolean printSql) {
        this.printSql = printSql;
    }


    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }
}
