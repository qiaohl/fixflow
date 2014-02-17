package org.fixflow.core.scriptlanguage;

import org.fixflow.core.impl.ProcessEngineConfigurationImpl;
import org.fixflow.core.impl.db.SqlCommand;

public interface SelectRulesScript {
	

	/**
	 * 执行查询规则
	 * @param parameter 参数
	 * @return
	 */
	Object execute(Object parameter,SqlCommand sqlCommand,ProcessEngineConfigurationImpl processEngineConfiguration);

}
