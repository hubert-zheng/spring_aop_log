package com.hubert.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class IndexController {
	
	@RequestMapping("/index")  
	public String login(HttpServletRequest request, HttpServletResponse response){
		
	    return "index";  
	}  
	
}
