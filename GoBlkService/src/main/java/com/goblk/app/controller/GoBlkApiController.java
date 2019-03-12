/**
 * 
 */
package com.goblk.app.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author adityapratap
 *
 */
@RestController
public class GoBlkApiController implements GoBlkApi {
	
	private static final Logger LOOGER = Logger.getLogger(GoBlkApiController.class);

	public ResponseEntity<String> getShortcutMap(@PathVariable String query, HttpServletRequest request) {
		LOOGER.info("Fetching shortcut map");
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

}
