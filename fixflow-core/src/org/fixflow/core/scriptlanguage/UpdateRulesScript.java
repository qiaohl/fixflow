package org.fixflow.core.scriptlanguage;

import org.fixflow.core.impl.ProcessEngineConfigurationImpl;
import org.fixflow.core.impl.db.SqlCommand;

public interface UpdateRulesScript {
	
	/**
	 * 执行更新规则
	 * @param parameter 参数
	 * @return
	 */
	void execute(Object parameter,SqlCommand sqlCommand,ProcessEngineConfigurationImpl processEngineConfiguration);

}
