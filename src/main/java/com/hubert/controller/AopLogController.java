package com.hubert.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hubert.aop.AopLogService;
import com.hubert.bean.AopLog;

@Controller
@RequestMapping(value = "/aopLog")
public class AopLogController {
	@Autowired
	AopLogService aopLogService;
	
	@RequestMapping("/query")
	@ResponseBody
	public List<AopLog> queryUser(HttpServletRequest request, HttpServletResponse response){
		return aopLogService.queryAopLog();
	}

}
