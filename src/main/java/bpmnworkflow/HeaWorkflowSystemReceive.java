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


import java.io.File;



import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class HeaWorkflowSystemReceive implements JavaDelegate {

	HearReadXML rXMLbpmnfile;
	//private static Logger logger = LoggerFactory.getLogger(HeaWorkflowSystemReceive.class);
	public void execute(DelegateExecution execution) {
		// System.out.println("\n" + ":: System worklfow receive Camel route
		// initiated >> start > Java Delegate");
		//String filename = "";
		rXMLbpmnfile = new HearReadXML(); /// var\bpmn20\bpmn20process
		File folder = new File("var/bpmn20/bpmn20process/");
		File[] listOfFiles = folder.listFiles();
		try {

			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {

					listOfFiles[i].getAbsolutePath();
					HearReadXML.readFile(listOfFiles[i].getName());
				}

			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String wflCompare;
		int nRoute = 0;
		char ch = '"';
		wflCompare = HearReadXML.bpmnWFLvariablestr;

		if (wflCompare.equalsIgnoreCase(ch + "NL" + ch)) {
		
			System.out.println(":: Workflow param: NL >>> " + wflCompare + " ROUTE 2 e -mail notification ::");
			nRoute = 8;
		} else {
		
			System.out.println(":: Workflow param: NL  - false - >>> " + wflCompare + " ROUTE 1 log to console ::");
			nRoute = 2;
		}

		if (nRoute > 5) {
			// System.out.println("Random decision : Route 1");
			// logger.info(" Random decision : Route 1");
			execution.setVariable("input", 1);

		} else {
			// System.out.println("Random decision : Route 2");
			// logger.info(" Random decision : Route 2");
			execution.setVariable("input", 2);
		}

		

	}
}
