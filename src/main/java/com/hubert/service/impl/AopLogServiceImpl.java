package com.hubert.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hubert.aop.AopLogService;
import com.hubert.bean.AopLog;
import com.hubert.bean.AopLogExample;
import com.hubert.mapper.AopLogMapper;

@Service
public class AopLogServiceImpl implements AopLogService{
	
	@Autowired
	AopLogMapper AopLogMapper;
	@Override
	public AopLog insertAopLog(AopLog record) {
		// TODO Auto-generated method stub
		if(0!=AopLogMapper.insert(record)){
			return record;
		}
		return null;
	}
	@Override
	public List<AopLog> queryAopLog() {
		AopLogExample example = new AopLogExample();
		return AopLogMapper.selectByExample(example);
	}

}
