package org.fixflow.expand.filter;

import org.fixflow.core.impl.filter.AbstractCommandFilter;
import org.fixflow.core.task.TaskInstance;

public class TerminationSubProcessReturnFilter extends AbstractCommandFilter {

	@Override
	public boolean accept(TaskInstance taskInstance) {
		if(taskInstance==null){
			return false;
		}
		
		if(taskInstance.isSuspended()){
			return false;
		}
		
		if(taskInstance.hasEnded()){
			return false;
		}
		
		if(isProcessTracking()){
			return false;
		}
		
		if(taskInstance.getDelegationState()!=null){
			return false;
		}

		if(taskInstance.getAssignee()!=null){
			return true;
		}
		else{
			if(isAutoClaim()){
				return true;
			}else{
				return false;
			}
		}
	}

}