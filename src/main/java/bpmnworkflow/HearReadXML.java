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



import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.UnsupportedEncodingException;


import java.util.Properties;







public class HearReadXML implements JavaDelegate {

	

	private static Properties props;
	// public static File file ;
	//private static Path strDirectoryPath;
	private static StringBuffer sb;
	private static String strZDatAB;
	public static String bpmnWFLvariablestr;
	private static String wflnewFileName;
	private static String wflnewConsolePrintFileName;
	private static BufferedWriter bwr;

	public void execute(DelegateExecution execution) {

	
	}

	public static void readFile(String fileName) {

		try {

			String finalFileName = "var/bpmn20/bpmn20process/" + fileName;
			wflnewFileName = "var/bpmn20/bpmn20process/processedNotificationRoute/" + fileName;
			wflnewConsolePrintFileName = "var/bpmn20/bpmn20process/processedNotificationConsole/" + fileName;
			
			File fileDir = new File(finalFileName);
			BufferedReader in;
			in = new BufferedReader(new InputStreamReader(
					// new FileInputStream(fileDir), "UTF8"));
					new FileInputStream(fileDir), "windows-1250"));
			String str;
			sb = new StringBuffer();
			while ((str = in.readLine()) != null) {
				// System.out.println(str);
				sb.append(str);
			}

			try {
				HearReadXML.setstrZDatAB(sb.toString());			
				System.out.println(HearReadXML.getRCValue());
			} catch (Exception e1) {

				e1.printStackTrace();
			}

			in.close();

			char ch = '"';
			if (bpmnWFLvariablestr.equalsIgnoreCase(ch + "NL" + ch)) {
				// System.out.println("Final result : bpmnWFLvariablestr equals
				// >>> " + bpmnWFLvariablestr);
				prepareNotificationRoute();
			} else {
				// System.out.println("Final result : bpmnWFLvariablestr NOT
				// equals >>> " + bpmnWFLvariablestr);				
			}


			try {
				File file = new File(finalFileName);
				if (file.delete()) {
					// System.out.println(file.getName() + " is deleted!");
				} else {
					// System.out.println("Delete operation is failed.");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static String getSexValue() {
		String str = null;
		String subStart = "<sex>";
		String subEnd = "</sex>";
		str = sb.toString();
		str = str.substring(str.indexOf(subStart) + subStart.length(), str.indexOf(subEnd));
		return str;

	}

	public static String getRCValue() {
		String str = null;
		String subStart = "<rodcis>";// rodcis
		String subEnd = "</rodcis>";
		str = sb.substring(sb.indexOf(subStart) + subStart.length(), sb.indexOf(subEnd));
		return str;

	}

	public static String getNameValue() {
		String str = null;
		str = sb.substring(sb.indexOf("<ip id_pac="), sb.length());

		String subStart = "<jmeno>";
		String subEnd = "</jmeno>";
		str = str.substring(str.indexOf(subStart) + subStart.length(), str.indexOf(subEnd));
		return str;

	}

	public static String getSurnameValue() {
		String str = null;
		str = sb.substring(sb.indexOf("<ip id_pac="), sb.length());

		String subStart = "<prijmeni>";
		String subEnd = "</prijmeni>";
		str = str.substring(str.indexOf(subStart) + subStart.length(), str.indexOf(subEnd));
		return str;

	}

	public static String getNazevValue() {
		String str = null;
		str = sb.toString();
		String subStart = "<nazev>";
		String subEnd = "</nazev>";
		str = str.substring(str.indexOf(subStart) + subStart.length(), str.indexOf(subEnd));
		return str;

	}

	public static String getAutorValue() {
		String str = null;
		str = sb.toString();
		String subStart = "<autor>";
		String subEnd = "</autor>";
		str = str.substring(str.indexOf(subStart) + subStart.length(), str.length());
		str = str.substring(str.indexOf(subStart) + subStart.length(), str.indexOf(subEnd));
		return str;

	}

	public static String getPTEXTValue() {
		String str = null;
		str = sb.toString();
		String subStart = "<ptext>";
		str = str.substring(str.indexOf(subStart) + subStart.length(), str.length());

		String subEnd = "</ptext>";
		str = str.substring(str.indexOf(subStart) + subStart.length(), str.indexOf(subEnd));
		return str;

	}

	public static void setstrZDatAB(String strZDatAB) {

		String str = strZDatAB;
		String subStart = "<z ";
		String subEnd = "</z>";
		str = sb.toString();

		str = str.substring(str.indexOf(subStart) + subStart.length(), str.indexOf(subEnd));

		HearReadXML.strZDatAB = str;

		//String strwfl = str;
		String subStartwfl = "obsah=";
		//String subEndwfl = "</z>";

		bpmnWFLvariablestr = str.substring(str.indexOf(subStartwfl) + subStartwfl.length(),
				str.indexOf(subStartwfl) + subStartwfl.length() + 4);
	}

	public static String getStrZDatAB() {
		return HearReadXML.strZDatAB;
	}

	public static void prepareNotificationRoute() {

		BufferedWriter bwr = null;
		try {
			bwr = new BufferedWriter(new FileWriter(new File(wflnewFileName)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// write contents of StringBuffer to a file
		try {
			bwr.write(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// flush the stream
		try {
			bwr.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// close the stream
		try {
			bwr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void prepareNotificationConsole() {

		BufferedWriter bwr = null;
		try {
			bwr = new BufferedWriter(new FileWriter(new File(wflnewConsolePrintFileName)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// write contents of StringBuffer to a file
		try {
			bwr.write(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// flush the stream
		try {
			bwr.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// close the stream
		try {
			bwr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
