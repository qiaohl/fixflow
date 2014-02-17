package org.fixflow.core.scriptlanguage;

import org.fixflow.core.impl.ProcessEngineConfigurationImpl;
import org.fixflow.core.impl.db.SqlCommand;

public interface BusinessRulesScript {
	
	/**
	 * 执行业务规则
	 * @param parameter 参数
	 * @return
	 */
	Object execute(Object parameter,SqlCommand sqlCommand,ProcessEngineConfigurationImpl processEngineConfiguration);
	
	
	

}
