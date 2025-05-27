/**
 *  Copyright 2008 biaoping.yin
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.  
 */
package org.frameworkset.mvc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.frameworkset.common.poolman.SQLExecutor;
import com.frameworkset.util.SimpleStringUtil;
import org.frameworkset.util.annotations.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> public class DBQueryController {.java</p>
 * <p> Description: </p>
 * <p> bboss workgroup </p>
 * <p> Copyright (c) 2009 </p>
 * 
 * @Date 2012-5-14 下午3:25:35
 * @author biaoping.yin
 * @version 1.0
 */
public class DBQueryController {
    private static Logger logger = LoggerFactory.getLogger(DBQueryController.class);
    public @ResponseBody List<Map> queryData(String sql){
        if(SimpleStringUtil.isEmpty(sql)){
            return new ArrayList<>();
        }
        List<Map> datas = null;
        try {
			logger.info("Clickhouse data query :");
            datas = SQLExecutor.queryListWithDBName(Map.class, "clickhousedm",sql);

        } catch (SQLException e) {
            logger.error("execute sql failed:"+sql,e);
            datas = new ArrayList<>();
        }

        return datas;

    }
	
	public @ResponseBody List<Map> queryDataMysql(String sql){
		if(SimpleStringUtil.isEmpty(sql)){
			return new ArrayList<>();
		}
		List<Map> datas = null;
		logger.info("Mysql data query :");
		try {
			datas = SQLExecutor.queryListWithDBName(Map.class, "test_dify",sql);
			
		} catch (SQLException e) {
			logger.error("execute sql failed:"+sql,e);
			datas = new ArrayList<>();
		}
		
		return datas;
		
	}
	 

}
