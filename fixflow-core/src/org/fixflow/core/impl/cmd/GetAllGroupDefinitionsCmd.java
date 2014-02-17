package org.fixflow.core.impl.cmd;

import java.util.List;

import org.fixflow.core.impl.Context;
import org.fixflow.core.impl.identity.GroupDefinition;
import org.fixflow.core.impl.interceptor.Command;
import org.fixflow.core.impl.interceptor.CommandContext;

public class GetAllGroupDefinitionsCmd implements Command<List<GroupDefinition>>{

	public List<GroupDefinition> execute(CommandContext commandContext) {
		List<GroupDefinition> groupDefinitions = Context.getProcessEngineConfiguration().getGroupDefinitions();
		return groupDefinitions;
	}
}
