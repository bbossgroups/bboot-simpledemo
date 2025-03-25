bboot simple demo,包含：
1. Clickhouse、Mysql通用查询服务（用户可以自行扩展增加其他类型数据库查询服务）；
2. Dify+Deepseek+Clickhouse+Echart渠道订单和退订统计问答工作流DSL：dify/Clickhouse渠道订单和退订分析.yml
3. Clickhouse数据迁移到Mysql作业：org.frameworkset.datatran.DataSynJob
 
   ETL&流处理参考文档   https://esdoc.bbossgroups.com/#/datatran-plugins
#开发调试
设置jvm启动参数
 

# 打运行包：

gradle releaseVersion

# 启动应用
启动前注释application.properties中的web.docBase配置
## windows
startup.bat/restart.bat

## linux/unix/macos

chmod +x startup.sh restart.sh
startup.sh/restart.sh

# 停止应用
## windows
stop.bat

## linux/unix/macos
chmod +x stop.sh

# 访问地址：
1. 通用mysql查询服务

http://localhost:8080/visualops/channelfullview/queryDataMysql.api

2. 通用Clickhouse查询服务
 
http://localhost:8080/visualops/channelfullview/queryData.api