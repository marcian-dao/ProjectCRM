package com.mmm.aspects;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.jboss.logging.Logger;

import com.mmm.customer.CustomerDetails;

/*@Component*/
public class AspectOne {
	
	private final static Logger LOGGER = Logger.getLogger(AspectOne.class.getClass()); //LOGGER.info instead of S.O.P
	
	
	
	@Before("com.mmm.aspects.ThePointCuts.beforeAnyMethodAddingAnyCustomer()")
	public void displayCustomerName(JoinPoint theJoinPoint) {
		
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		
		Object[] args = theJoinPoint.getArgs();
		
		LOGGER.info("POINTCUT REDIRECTION...ADDING CUSTOMER with METHOD SIGNATURE: " +  methodSig);
		
		for(Object tempArgs : args) {
			
			LOGGER.info(tempArgs);
			
			if(tempArgs instanceof CustomerDetails) {
				
				CustomerDetails customerDetails = (CustomerDetails) tempArgs;
				
				LOGGER.info("Hello: " + customerDetails.getLastName());
			}
		}	
	}
	
	@After("com.mmm.aspects.ThePointCuts.afterReturningMethod()")
	public void afterFinally(JoinPoint theJoinPoint) {
		
		LOGGER.info("@After (after finally)----------------------------------");
		
	}
	
	@AfterReturning(
	pointcut="com.mmm.aspects.ThePointCuts.afterReturningMethod()",
	returning="result")
	public void displayAllCustomers(JoinPoint theJoinPoint, List<CustomerDetails> result) {
		
		LOGGER.info("@AfterReturning: ");
		
		if(!result.isEmpty()) {
		
			for(CustomerDetails cd : result) {
				
				LOGGER.info(cd.getFirstName() +" "+ cd.getLastName());
				
				if(cd.getLastName().equals("Dalton")) {
					
					cd.setLastName("DaltonPRO");
				}
			}	
		}else {
			
			LOGGER.info("There are no customers yet.");
		}	
	}
	
	@AfterThrowing(
	pointcut="com.mmm.aspects.ThePointCuts.beforeAnyMethodAddingAnyCustomer()",
	throwing="theException")
	public void afterThrowing(JoinPoint theJoinPoint, Throwable theException) {
		
		String metodSignature = theJoinPoint.getSignature().toString();
		
		LOGGER.info("@AfterThrowing Method Signature: " + metodSignature + "Something went wrong !!!!!!!!!!!!" + "Exception: " +  theException);
		
	}
	
	@Around("com.mmm.aspects.ThePointCuts.deletingMethod()")
	public Object aroundAOP(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {	
		
		Object result = null;
		
		LOGGER.info("@Around-------------Before Proceeding--------------------@Around");
		
		try {
			result = theProceedingJoinPoint.proceed();
		} catch (Exception e) {
			
			
			LOGGER.info("Huston we have problem... " + e );
			
			
			result = "It is OK now!";
		}
		
		LOGGER.info("@Around-------------After Proceeding--------------------@Around");
		
		return result;
		
	}
	
}

	
