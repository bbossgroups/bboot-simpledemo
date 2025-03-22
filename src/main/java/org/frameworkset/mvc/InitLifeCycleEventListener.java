package org.frameworkset.mvc;
/**
 * Copyright 2008 biaoping.yin
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
 
import com.frameworkset.common.poolman.util.DBConf;
import com.frameworkset.common.poolman.util.SQLManager;
import com.frameworkset.common.poolman.util.SQLUtil;
import org.frameworkset.spi.BaseApplicationContext;
import org.frameworkset.spi.event.IocLifeCycleEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * <p>Description: 终端报文web配置管理工程</p>
 * <p></p>
 * <p>Copyright (c) 2018</p>
 * @Date 2019/4/8 10:42
 * @author biaoping.yin
 * @version 1.0
 */
public class InitLifeCycleEventListener implements IocLifeCycleEventListener {
	private static Logger log = LoggerFactory.getLogger(InitLifeCycleEventListener.class);

	@Override
	public void init(Map<String, String> params) {

	}

	@Override
	public void beforestart() {
        DBConf tempConf = new DBConf();
        tempConf.setPoolname("clickhousedm");//数据源名称  
        tempConf.setDriver("com.github.housepower.jdbc.ClickHouseDriver");//clickhouse驱动  
        tempConf.setJdbcurl("jdbc:clickhouse://10.13.6.4:29000,10.13.6.7:29000,10.13.6.6:29000/visualops?b.enableBalance=true&b.balance=roundbin");
        //数据库账号和口令  
        tempConf.setUsername("default");
        tempConf.setPassword("123456");
        tempConf.setValidationQuery("select 1 ");//数据库连接校验sql  
        //tempConf.setTxIsolationLevel("READ_COMMITTED");
        tempConf.setJndiName("jndi-clickhousedm");
        tempConf.setInitialConnections(3);
        tempConf.setMinimumSize(10);
        tempConf.setMaximumSize(10);
        tempConf.setUsepool(true);
        tempConf.setShowsql(true);
        tempConf.setQueryfetchsize(1000);
        SQLManager.startPool(tempConf);


	}

	@Override
	public void afterstart(BaseApplicationContext context) {
	}
}
