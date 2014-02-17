package org.fixflow.expand.flowconnector.FixRunDataBase;


import org.fixflow.core.action.ConnectorHandler;
import org.fixflow.core.exception.FixFlowException;
import org.fixflow.core.impl.Context;
import org.fixflow.core.impl.db.SqlCommand;
import org.fixflow.core.runtime.ExecutionContext;

public class FixRunDataBase implements ConnectorHandler {

	private java.lang.String sqlText;

	public void execute(ExecutionContext executionContext) throws Exception {
		

		if(sqlText!=null){
			SqlCommand sqlCommand=new SqlCommand(Context.getDbConnection());
			sqlCommand.execute(sqlText.toString());
		}
		else {
			throw new FixFlowException("执行数据库语句不正确!");
		}
		
	}

	public void  setSqlText(java.lang.String sqlText){
		this.sqlText = sqlText;
	}

}