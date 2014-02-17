package org.fixflow.expand.connector.RunExpression;


import org.fixflow.core.action.ConnectorHandler;
import org.fixflow.core.runtime.ExecutionContext;

public class RunExpression implements ConnectorHandler {

	private java.lang.Object expressionText;

	public void execute(ExecutionContext executionContext) throws Exception {

	}

	public void  setExpressionText(java.lang.Object expressionText){
		this.expressionText = expressionText;
	}

	public java.lang.Object  getOutputObj(){
		return expressionText ;
	}

}