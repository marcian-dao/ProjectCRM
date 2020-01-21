package com.mmm.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ThePointCuts {
	
	@Pointcut("execution(* com.mmm.controller.*.*(..))") 
	public void beforeAnyControllerMethod() { }	 

	@Pointcut("execution(* com.mmm.service.CustomerService.*(..))")
	public void beforeAnyServiceMethod() { }
	
	@Pointcut("execution(* com.mmm.dao.CustomerDAO.*(..))")
	public void beforeAnyDaoMethod() { }	
	
	@Pointcut("beforeAnyControllerMethod() || beforeAnyServiceMethod() || beforeAnyDaoMethod()")
	public void beforeAllmethods() { }
}
