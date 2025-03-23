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


mysql
http://localhost:8080/visualops/channelfullview/queryDataMysql.api
Clickhouse
http://localhost:8080/visualops/channelfullview/queryData.api