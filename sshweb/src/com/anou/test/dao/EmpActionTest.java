package com.anou.test.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.abcdef.action.EmployeeAction;

public class EmpActionTest {
	ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
	
	@Test
	public void testList() {
		EmployeeAction action =(EmployeeAction) ac.getBean("employeeAction");
		String result=action.list();
		System.out.println(result);
	}
}
