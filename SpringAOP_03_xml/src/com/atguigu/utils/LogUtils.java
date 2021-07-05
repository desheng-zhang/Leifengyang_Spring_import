package com.atguigu.utils;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

public class LogUtils {
	
	
	public static void logStart(JoinPoint joinPoint){
		//获取到目标方法运行是使用的参数
		Object[] args = joinPoint.getArgs();
		//获取到方法签名
		Signature signature = joinPoint.getSignature();
		String name = signature.getName();
		System.out.println("[LogUtils-前置]【"+name+"】方法开始执行，用的参数列表【"+Arrays.asList(args)+"】");
	}
	public static void logReturn(JoinPoint joinPoint,Object result){
		Signature signature = joinPoint.getSignature();
		String name = signature.getName();
		System.out.println("[LogUtils-返回]【"+name+"】方法正常执行完成，计算结果是："+result);
	}

	
	public static void logException(JoinPoint joinPoint,Exception exception) {
		System.out.println("[LogUtils-异常]【"+joinPoint.getSignature().getName()+"】方法执行出现异常了，异常信息是【"+exception+"】：；这个异常已经通知测试小组进行排查");
	}

	public int logEnd(JoinPoint joinPoint) {
		System.out.println("[LogUtils-后置]【"+joinPoint.getSignature().getName()+"】方法最终结束了");
		return 0;
	}
	
	public Object myAround(ProceedingJoinPoint pjp) throws Throwable{
		
		Object[] args = pjp.getArgs();
		String name = pjp.getSignature().getName();
		//args[0] = 100;
		Object proceed = null;
		try {
			//@Before
			System.out.println("【环绕前置通知】【"+name+"方法开始】");
			//就是利用反射调用目标方法即可，就是method.invoke(obj,args)
			proceed = pjp.proceed(args);
			//@AfterReturing
			System.out.println("【环绕返回通知】【"+name+"方法返回，返回值"+proceed+"】");
		} catch (Exception e) {
			//@AfterThrowing
			System.out.println("【环绕异常通知】【"+name+"】方法出现异常，异常信息："+e);
			//为了让外界能知道这个异常，这个异常一定抛出去
			throw new RuntimeException(e);
		} finally{
			//@After
			System.out.println("【环绕后置通知】【"+name+"】方法结束");
		}
		
		//反射调用后的返回值也一定返回出去
		return 1000;
	}
}
