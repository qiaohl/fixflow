package org.fixflow.core.impl.cmd;

import java.util.List;

import org.fixflow.core.impl.Context;
import org.fixflow.core.impl.identity.GroupTo;
import org.fixflow.core.impl.identity.UserDefinition;
import org.fixflow.core.impl.interceptor.Command;
import org.fixflow.core.impl.interceptor.CommandContext;

public class GetUserInGroupsCmd implements Command<List<GroupTo>>{

	protected String userId;
	
	public GetUserInGroupsCmd(String userId){
		this.userId=userId;
	}
	
	
	public List<GroupTo> execute(CommandContext commandContext) {
		UserDefinition userDefinition=Context.getProcessEngineConfiguration().getUserDefinition();
		return userDefinition.getUserInGroups(this.userId);
	}

}
