/**
 * 
 */
package com.goblk.app.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.goblk.app.constant.AppConstant;
import com.goblk.app.constant.Command;

/**
 * @author adityapratap
 *
 */
@RestController
public class GoBlkApiController implements GoBlkApi {
	
	private static final Logger LOOGER = Logger.getLogger(GoBlkApiController.class);

	public ResponseEntity<String> getShortcutMap(@PathVariable String query, HttpServletRequest request) {
		LOOGER.info("Fetching shortcut map");
		
		String command = Command.DEFAULT.getCommand(); 
		//query parser
		if (StringUtils.isNotBlank(query)) {
			String [] tokenizedStr = StringUtils.split(query, AppConstant.SPACE_STR);
			
			if (tokenizedStr.length > 1 && Command.findCommandByAbbr(tokenizedStr[0]) != null) {
				//get command
				command = Command.findCommandByAbbr(tokenizedStr[0]).getCommand();
			}
		}
		
		
		Map<String, Map<Integer, String>> data = new HashMap<>();
		
		//get parameters
		
		//get related url
		
		//prediction policy
		
		Map<String, String> result = new HashMap<String, String>();
		result.put("toi", "https://timesofindia.indiatimes.com/");
		result.put("times of india", "https://timesofindia.indiatimes.com/");
		result.put("hindu news", "https://www.thehindu.com/");
		result.put("the hindu", "https://www.thehindu.com/");
		result.put("passport", "https://portal2.passportindia.gov.in/");
		String url = "";
		try {
			 url = result.get(query);
		} catch (Exception e) {
			LOOGER.error(e.getMessage(), e);
			return new ResponseEntity<>(url, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(url, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Set<String>> loadHelp(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Set<String> result = new HashSet<>();
		result.add("toi");
		result.add("passport");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
