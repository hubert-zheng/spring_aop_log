package com.hubert.aop;

import java.util.List;

import com.hubert.bean.AopLog;

public interface AopLogService {
	AopLog insertAopLog(AopLog record);
	
	List<AopLog> queryAopLog();
}
