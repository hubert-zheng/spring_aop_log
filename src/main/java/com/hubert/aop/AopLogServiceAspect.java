package com.hubert.aop;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hubert.bean.AopLog;
import com.hubert.mapper.AopLogMapper;
import com.hubert.util.FastJsonUtil;

//声明这是一个组件
@Component
//声明这是一个切面Bean
@Aspect
public class AopLogServiceAspect {
	private final static Log log = LogFactory.getLog(AopLogServiceAspect.class);
	
//	@Autowired
//	AopLogService aopLogService;   //切入点在service层，记录日志时会形成逻辑循环
	@Autowired
	AopLogMapper aopLogMapper;
	
		//配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
		@Pointcut("execution(* com.hubert.service..*(..))")
		public void aspect(){	}
		
		/*
		 * 配置前置通知,使用在方法aspect()上注册的切入点
		 * 同时接受JoinPoint切入点对象,可以没有该参数
		 */
		@Before("aspect()")
		public void before(JoinPoint joinPoint){
			if(log.isInfoEnabled()){
				log.info("before " + joinPoint);
			}
		}
		
		//配置后置通知,使用在方法aspect()上注册的切入点
		@After("aspect()")
		public void after(JoinPoint joinPoint){
			if(log.isInfoEnabled()){
				log.info("after " + joinPoint);
			}
		}
		
//		//配置环绕通知,使用在方法aspect()上注册的切入点
//		@Around("aspect()")
//		public void around(JoinPoint joinPoint){
//			long start = System.currentTimeMillis();
//			try {
//				((ProceedingJoinPoint) joinPoint).proceed();
//				long end = System.currentTimeMillis();
//				if(log.isInfoEnabled()){
//					log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
//				}
//			} catch (Throwable e) {
//				long end = System.currentTimeMillis();
//				if(log.isInfoEnabled()){
//					log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
//				}
//			}
//		}
		
		//配置后置返回通知,使用在方法aspect()上注册的切入点    --  即正常返回。完成service方法
		@AfterReturning("aspect()")
		public void afterReturn(JoinPoint joinPoint){
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			// 获取请求ip
			String ip = request.getRemoteAddr();
			String method = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()";
			// 获取用户请求方法的参数并序列化为JSON格式字符串
			String params = "";
			if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
				for (int i = 0; i < joinPoint.getArgs().length; i++) {
					params += FastJsonUtil.bean2Json(joinPoint.getArgs()[i]);
				}
			}
			if(log.isInfoEnabled()){
				/* ========控制台输出========= */
				System.out.println("=====后置通知开始=====");
				System.out.println("方法:" + method);
				System.out.println("请求IP:" + ip);
				System.out.println("请求参数:" + params);
				AopLog aopLog = new AopLog();
				String logSid = UUID.randomUUID().toString().replace("-","");
				aopLog.setSid(logSid.substring(0, 19));
				aopLog.setCreatedate(new Date());;
				aopLog.setContent("ip地址：" + ip + ",访问了方法--" + method + "参数为：" + params);
				aopLog.setOperation("调用service层方法");
				aopLogMapper.insert(aopLog);
				log.info("调用方法日志记录操作存表完成");
				log.info("afterReturn " + joinPoint);
			}
		}
		
		//配置抛出异常后通知,使用在方法aspect()上注册的切入点
		@AfterThrowing(pointcut="aspect()", throwing="ex")
		public void afterThrow(JoinPoint joinPoint, Exception ex){
			if(log.isInfoEnabled()){
				log.info("afterThrow " + joinPoint + "\t" + ex.getMessage());
			}
		}
		
	
}
