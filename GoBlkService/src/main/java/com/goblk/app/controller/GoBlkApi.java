package com.goblk.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;

@Api(value = "goblk")
@RequestMapping("/")
public interface GoBlkApi {

	@RequestMapping(value ="data/{query}", method = RequestMethod.GET)
	ResponseEntity<String> getShortcutMap(@PathVariable("query") String query, HttpServletRequest request);
}
