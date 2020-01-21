package com.mmm.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

	private final static Logger LOGGER = Logger.getLogger(AspectOne.class.getClass());

	@Before("com.mmm.aspects.ThePointCuts.beforeAllmethods()")
	public void beforeMethod(JoinPoint theJoinPoint) {

		String metodSignature = theJoinPoint.getSignature().toString();

		Object[] args = theJoinPoint.getArgs();	

		for (Object tempArgs : args) {
			if(tempArgs != null) {
				LOGGER.info("@Before any method: " + "\n" 
						  + "Method signature: 		" + metodSignature + "\n" 
						  + "Arguments: 			" + tempArgs.toString());
			}else {
				
				LOGGER.info("Some null args!");
			}			
		}
	}
}
