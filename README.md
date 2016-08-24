# Spring+Springmvc+Mybatis框架
构建Spring+Springmvc+Mybatis框架，丰富框架的功能，后续集成日志插件log4j、logback，集成Druid连接池监控，集成Redis缓存，office文件操作插件poi等。

1.基于mvc实现基本的数据增删改查操作。


2.已经分别集成log4j和logback日志插件。


3.已经集成Druid连接池监控。


4.已经集成的Redis缓存，基于jedis使用缓存，实现客户端分片。


5.添加CommonInterceptor类实现HandlerInterceptorAdapter接口拦截请求，基于注解添加RedisAspect拦截类监视缓存方法调用。


6.集成office文件操作插件poi，实现excel数据操作。

7.mybatis-plugin插件实现分页查询和扩展mapper，集成mybatis-plugin插件。

8.添加mybatis执行sql打印功能。

9.正在集成框架异常处理。
