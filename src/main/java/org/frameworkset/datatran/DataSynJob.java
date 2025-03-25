package org.frameworkset.datatran;
/**
 * Copyright 2025 bboss
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.frameworkset.tran.DataStream;
import org.frameworkset.tran.config.ImportBuilder;
import org.frameworkset.tran.plugin.db.input.DBInputConfig;
import org.frameworkset.tran.plugin.db.output.DBOutputConfig;

/**
 * <p>Description: </p>
 * <p></p>
 *
 * @author biaoping.yin
 * @Date 2025/3/23
 */
public class DataSynJob {
	public static void main(String[] args){
		//订单同步
//		String querySql = "iops_queryChannelOrder";
//		String insertSql = "iops_insertChannelOrder";
		//退订数据同步
		String querySql = "iops_queryChannelbackOrder";
		String insertSql = "iops_insertChannelbackOrder";
		//创建作业构建器
		ImportBuilder importBuilder = new ImportBuilder() ;
		importBuilder.setJobId("job-order");
		importBuilder.setJobName("订单数据同步");
		DBInputConfig dbInputConfig = new DBInputConfig();
		dbInputConfig.setDbName("clickhousedm")
				.setDbDriver("com.github.housepower.jdbc.ClickHouseDriver") //数据库驱动程序，必须导入相关数据库的驱动jar包
				.setDbUrl("jdbc:clickhouse://101.131.6.4:29000,101.131.6.7:29000,101.131.6.6:29000/visualops?b.enableBalance=true&b.balance=roundbin")
				.setDbUser("default")
				.setDbPassword("123456")
				.setValidateSQL("select 1")
				.setShowSql(true)//打印sql语句到日志文件和控制台，结合Log4j
				.setUsePool(true)//是否使用连接池
				.setSqlFilepath("synchannelOrderDBTest.xml")
				.setSqlName(querySql);
		importBuilder.setInputConfig(dbInputConfig);
		

		DBOutputConfig dbOutputConfig = new DBOutputConfig();
		dbOutputConfig
				.setDbName("test-dify")
				.setDbDriver("com.mysql.cj.jdbc.Driver") //数据库驱动程序，必须导入相关数据库的驱动jar包
				.setDbUrl("jdbc:mysql://101.131.6.127:3306/test_dify?useUnicode=true&characterEncoding=utf-8&useSSL=false&rewriteBatchedStatements=true")
				.setDbUser("root")
				.setDbPassword("passwd")
				.setValidateSQL("select 1")
				.setUsePool(true)
				.setDbInitSize(5)
				.setDbMinIdleSize(10)
				.setDbMaxSize(10)
				.setShowSql(true)//是否使用连接池;
				
				.setSqlFilepath("synchannelOrderDBTest.xml")
				.setInsertSqlName(insertSql);//指定新增的sql语句名称，在配置文件中配置：synchannelOrderDBTest.xml
	 
		//作业基础配置
		importBuilder.setFetchSize(1000);//从源库批量fetch数据记录数
		importBuilder.setBatchSize(5000);//可选项,批量导入es的记录数，默认为-1，逐条处理，> 0时批量处理 
		importBuilder.setUseJavaName(true) //可选项,将数据库字段名称转换为java驼峰规范的名称，true转换，false不转换，默认false，例如:doc_id -> docId
				.setPrintTaskLog(true); //可选项，true 打印任务执行日志（耗时，处理记录数） false 不打印，默认值false
		
		importBuilder.setParallel(true);//设置为多线程并行批量导入,false串行
		importBuilder.setQueue(50);//设置批量导入线程池等待队列长度
		importBuilder.setThreadCount(5);//设置批量导入线程池工作线程数量
		importBuilder.setContinueOnError(true);//任务出现异常，是否继续执行作业：true（默认值）继续执行 false 中断作业执行
		
		importBuilder.setOutputConfig(dbOutputConfig);
		
		/**
		 * 执行数据库表数据导入es操作
		 */
		DataStream dataStream = importBuilder.builder();
		dataStream.execute();//执行导入操作
	}
}
