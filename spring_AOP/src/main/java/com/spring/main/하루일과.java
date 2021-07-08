package com.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.spring.pointcut.Behavior;

public class 하루일과 {

	public static void main(String[] args) {
		
		
		ApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context.xml");
		
		Behavior behavior = ctx.getBean("behavior",Behavior.class);
		
		behavior.밥먹기();
		behavior.정신수양();
		behavior.밥먹기();
		behavior.공부하기();
		behavior.놀기();
		behavior.데이트();
		behavior.운동();
		behavior.밥먹기();
		behavior.잠자기();

	}

}
