package com.unicredit.cap.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.springframework.core.io.ClassPathResource;

public class EmailTemplateHelper {
	
	public static String processEmailTemplate(String templateName, HashMap templateModel) throws IOException {
		ClassPathResource classPathResource = new ClassPathResource("email_templates\\" + templateName);
		InputStream inputStream = classPathResource.getInputStream();
		
		 
			
		 BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		 StringBuilder sb = new StringBuilder();
		 String line;
		 
		 while ((line = br.readLine()) != null) 
		 {
			 sb.append(line);
		 }

		 br.close();

		 String templateContent = sb.toString();
		 
		 for(Object modelKey : templateModel.keySet())
		 {
			 String variableName = "${" + modelKey.toString() + "}";
			 String variableValue = templateModel.get(modelKey).toString();
			 
			 templateContent = templateContent.replace(variableName, variableValue);
		 }
		 return templateContent;
	}
}
