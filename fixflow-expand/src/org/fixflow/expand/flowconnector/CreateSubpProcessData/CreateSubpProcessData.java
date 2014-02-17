package org.fixflow.expand.flowconnector.CreateSubpProcessData;


import java.util.HashMap;

import org.fixflow.core.action.ConnectorHandler;
import org.fixflow.core.impl.db.SqlCommand;
import org.fixflow.core.runtime.ExecutionContext;

public class CreateSubpProcessData implements ConnectorHandler {

	private java.lang.String table;

	private java.util.HashMap param;

	public void execute(ExecutionContext executionContext) throws Exception {
		SqlCommand sqlCommand=executionContext.getSqlCommand();
		// 构建查询参数
		HashMap<String, Object> objectParam = (HashMap<String, Object>)param;
		// 执行插入语句
		sqlCommand.insert(table, objectParam);
	}

	public void  setTable(java.lang.String table){
		this.table = table;
	}

	public void  setParam(java.util.HashMap param){
		this.param = param;
	}

}