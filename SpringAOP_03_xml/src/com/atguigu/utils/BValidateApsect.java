package com.atguigu.utils;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

public class BValidateApsect {
	
	
	public void logStart(JoinPoint joinPoint){
		Object[] args = joinPoint.getArgs();
		Signature signature = joinPoint.getSignature();
		String name = signature.getName();
		System.out.println("[VaApsect-前置]【"+name+"】方法开始执行，用的参数列表【"+Arrays.asList(args)+"】");
	}
	
	public void logReturn(JoinPoint joinPoint,Object result){
		Signature signature = joinPoint.getSignature();
		String name = signature.getName();
		System.out.println("[VaApsect-返回]【"+name+"】方法正常执行完成，计算结果是："+result);
	}
	
	public void logException(JoinPoint joinPoint,Exception exception) {
		System.out.println("[VaApsect-异常]【"+joinPoint.getSignature().getName()+"】方法执行出现异常了，异常信息是【"+exception+"】：；这个异常已经通知测试小组进行排查");
	}

	public int logEnd(JoinPoint joinPoint) {
		System.out.println("[VaApsect-后置]【"+joinPoint.getSignature().getName()+"】方法最终结束了");
		return 0;
	}
}
