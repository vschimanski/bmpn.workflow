/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package bpmnworkflow;

import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;

import org.activiti.engine.runtime.ProcessInstance;

import org.activiti.engine.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivitiProcess {

	ProcessInstance processInstance;
	ProcessEngine processEngine;
	private static Logger logger = LoggerFactory.getLogger(ActivitiProcess.class);

	public void Deploy() throws Exception {

		logger.info("::: Info log message Activit Process Start with slf4j logger :::");

		processEngine = ProcessEngines.getDefaultProcessEngine();

		RuntimeService runtimeService = processEngine.getRuntimeService();
		RepositoryService repositoryService = processEngine.getRepositoryService();

		System.out.println("HeaCamelTask 1");
		repositoryService.createDeployment().addClasspathResource("GenericWorkFlowProcess.bpmn20.xml").deploy();
		processInstance = runtimeService.startProcessInstanceByKey("GenericWorkFlowProcess");

		logger.info("::: Info log message Activit Process End with slf4j logger :::");
	}

	protected UserTask createUserTask(String id, String name, String assignee) {
		UserTask userTask = new UserTask();
		userTask.setName(name);
		userTask.setId(id);
		userTask.setAssignee(assignee);
		return userTask;
	}

	protected SequenceFlow createSequenceFlow(String from, String to) {
		SequenceFlow flow = new SequenceFlow();
		flow.setSourceRef(from);
		flow.setTargetRef(to);
		return flow;
	}

	protected StartEvent createStartEvent() {
		StartEvent startEvent = new StartEvent();
		startEvent.setId("start");
		return startEvent;
	}

	protected EndEvent createEndEvent() {
		EndEvent endEvent = new EndEvent();
		endEvent.setId("end");
		return endEvent;
	}
	
}