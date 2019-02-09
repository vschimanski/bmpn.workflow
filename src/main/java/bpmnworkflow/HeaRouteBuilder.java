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


/// DON"T update PRERELEASE >>>> working version


import org.apache.camel.builder.RouteBuilder;





import static org.activiti.camel.ActivitiProducer.PROCESS_KEY_PROPERTY;

public class HeaRouteBuilder extends RouteBuilder {

	ActivitiProcess aProcess = new ActivitiProcess();
	

	
    public void configure() throws Exception {
    	
    	
    	
    	   
        ///////
        try {
		//	aProcess.Deploy();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /////
      
        
        from("file:var/bpmn20").to("file:var/bpmn20/bpmn20process")
            .setBody(simple("key 001"))
            .setProperty(PROCESS_KEY_PROPERTY, simple("file:name"))
            .setHeader("PROCESS_ID_PROPERTY", simple("${property.PROCESS_ID_PROPERTY}"))
            .to("activiti:HeaWorkflow")
 
          
            
            ////// update   
            .log("Initiate HEA BPMN process :: ")
            .setHeader("PROCESS_ID_PROPERTY", simple("${property.PROCESS_ID_PROPERTY}"))
            .log("${property.PROCESS_ID_PROPERTY}")

            //.log("${property.PROCESS_ID_PROPERTY}")
            
            .setBody(simple("key 001"))
            .to("activiti:HeaWorkflow:receiveFlow");
        	////// update
           	// .setBody(simple("0001")) .to("smtp://smtpserver:25?mail.smtp.auth=false")
           	// .to("activiti:HeaWorkflow:Route001?copyVariablesToProperties=true");
            ;

            from("file:var/bpmn20/bpmn20process/processedNotificationRoute")
            //.to("smtp://smtp.i.cz?25to=user@domain.com")
            //.to("smtp://smtp.i.cz?25to=user@domain.com")
            .setBody(constant("Testing SMTP mail."))
            //.setHeader("To", constant("user@domain.com"))
            .setHeader("To", constant("user@testdomain.com"))
            .setHeader("Subject",constant("Testing activiti workflow"))
            .setHeader("From", constant("user@testdomain.com")) 
            .to("smtp://smtp.someserver.domain.com:25?mail.smtp.auth=false")            
            .log("Notification sent!")
            ;
            
    
            
            
            from("activiti:HeaWorkflow:dasta2pdf?copyVariablesToProperties=false")
            .setBody(simple("key 001"))
            .setHeader("PROCESS_ID_PROPERTY", simple("${property.PROCESS_ID_PROPERTY}"))
             //.log("Processing order ${property.orderid} created on ${property.timestamp}")
			.log("HEA BPMN process initialized and started, processing OK !!!");
    }

    
}
