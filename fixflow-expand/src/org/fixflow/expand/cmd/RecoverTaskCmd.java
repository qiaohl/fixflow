/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
package org.fixflow.expand.cmd;

import java.util.List;

import org.fixflow.core.ProcessEngineManagement;
import org.fixflow.core.RuntimeService;
import org.fixflow.core.TaskService;
import org.fixflow.core.exception.FixFlowException;
import org.fixflow.core.factory.ProcessObjectFactory;
import org.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import org.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import org.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import org.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import org.fixflow.core.impl.identity.Authentication;
import org.fixflow.core.impl.interceptor.CommandContext;
import org.fixflow.core.impl.runtime.ProcessInstanceEntity;
import org.fixflow.core.impl.runtime.TokenEntity;
import org.fixflow.core.impl.task.TaskInstanceEntity;
import org.fixflow.core.impl.util.StringUtil;
import org.fixflow.core.runtime.ExecutionContext;
import org.fixflow.core.runtime.Token;
import org.fixflow.core.task.TaskInstance;
import org.fixflow.expand.command.RecoverTaskCommand;

public class RecoverTaskCmd extends AbstractExpandTaskCmd<RecoverTaskCommand, Void> {

	protected String recoverNodeId;

	// protected String processInstanceId;

	public RecoverTaskCmd(RecoverTaskCommand recoverTaskCommand) {
		super(recoverTaskCommand);
		// TODO 自动生成的构造函数存根

		this.recoverNodeId = recoverTaskCommand.getRecoverNodeId();
		// this.processInstanceId=recoverTaskCommand.getProcessInstanceId();
	}

	public Void execute(CommandContext commandContext) {
		// TODO 自动生成的方法存根

		TaskService taskService = ProcessEngineManagement.getDefaultProcessEngine().getTaskService();

		RuntimeService runtimeService = ProcessEngineManagement.getDefaultProcessEngine().getRuntimeService();

		// 初始化任务命令执行所需要的常用对象
		loadProcessParameter(commandContext);

		// 将外部变量注册到流程实例运行环境中
		addVariable();

		// 执行处理命令中的开发人员设置的表达式
		runCommandExpression();

		// 获取正在操作的任务实例对象
		TaskInstanceEntity taskInstanceTemp = getTaskInstanceEntity();

		// 获取正在操作的任务命令对象实例
		TaskCommandInst taskCommand = getTaskCommandInst();

		ProcessInstanceEntity processInstanceImpl = getProcessInstance();

		String userId = null;

		if (StringUtil.isEmpty(admin)) {
			if (StringUtil.isEmpty(agent)) {
				userId = Authentication.getAuthenticatedUserId();
			} else {
				userId = agent;
			}

		}

		List<TaskInstance> endTaskInstances = null;
		if (userId == null) {
			endTaskInstances = taskService.createTaskQuery().processInstanceId(taskInstanceTemp.getProcessInstanceId()).taskIsEnd().nodeId(recoverNodeId)
					.list();

		} else {
			endTaskInstances = taskService.createTaskQuery().processInstanceId(taskInstanceTemp.getProcessInstanceId()).taskAssignee(userId).taskIsEnd()
					.nodeId(recoverNodeId).list();

		}

		TaskInstance recoverToTask = endTaskInstances.get(0);

		String tokenId = recoverToTask.getTokenId();
		Token recoverToToken = runtimeService.createTokenQuery().tokenId(tokenId).singleResult();
		if (recoverToToken.getEndTime() == null) {

		

	
			ProcessDefinitionBehavior processDefinition = processInstanceImpl.getProcessDefinition();

			UserTaskBehavior userTask = (UserTaskBehavior) processDefinition.getDefinitions().getElement(recoverNodeId);

			

			TokenEntity tokenEntity = processInstanceImpl.getTokenMap().get(tokenId);

			tokenEntity.terminationChildTokenWithTask(taskCommand.getTaskCommandType(), taskCommand.getName(), this.taskComment, userId, this.agent, this.admin);

			//TokenEntity token = processInstanceImpl.getTokenMap().get(tokenId);

			ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(tokenEntity);

			//taskInstanceTemp.setAssigneeWithoutCascade(userId);
			taskInstanceTemp.customEnd(taskCommand, taskComment);

			tokenEntity.removeTaskInstanceSynchronization(taskCommand.getTaskCommandType(), taskCommand.getName(), this.taskComment, userId, this.agent, this.admin);

			if (recoverToTask.getTaskGroup() != null) {

				if (taskCommand != null) {

					userTask.enter(executionContext);

				}

				try {
					saveProcessInstance(commandContext);
				} catch (Exception e) {
					throw new FixFlowException("流程实例持久化失败!", e);
				}
				return null;

			} else {

				if (taskCommand != null) {

					executionContext.setRollBackAssignee(recoverToTask.getAssignee());
					userTask.enter(executionContext);

				}

				try {
					saveProcessInstance(commandContext);
				} catch (Exception e) {
					throw new FixFlowException("流程实例持久化失败!", e);
				}
				return null;

			}

		} else {
			
			
			ProcessDefinitionBehavior processDefinition = processInstanceImpl.getProcessDefinition();

			UserTaskBehavior userTask = (UserTaskBehavior) processDefinition.getDefinitions().getElement(recoverNodeId);

			
			

			TokenEntity tokenEntity = processInstanceImpl.getRootToken();

			tokenEntity.terminationChildTokenWithTask(taskCommand.getTaskCommandType(), taskCommand.getName(), this.taskComment, userId, this.agent, this.admin);

			//TokenEntity token = processInstanceImpl.getTokenMap().get(tokenId);

			ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(tokenEntity);

			//taskInstanceTemp.setAssigneeWithoutCascade(userId);
			taskInstanceTemp.customEnd(taskCommand, taskComment);

			tokenEntity.removeTaskInstanceSynchronization(taskCommand.getTaskCommandType(), taskCommand.getName(), this.taskComment, userId, this.agent, this.admin);

			if (recoverToTask.getTaskGroup() != null) {

				if (taskCommand != null) {

					userTask.enter(executionContext);

				}

				try {
					saveProcessInstance(commandContext);
				} catch (Exception e) {
					throw new FixFlowException("流程实例持久化失败!", e);
				}
				return null;

			} else {

				if (taskCommand != null) {

					executionContext.setRollBackAssignee(recoverToTask.getAssignee());
					userTask.enter(executionContext);

				}

				try {
					saveProcessInstance(commandContext);
				} catch (Exception e) {
					throw new FixFlowException("流程实例持久化失败!", e);
				}
				return null;

			}

		}



	}

}
