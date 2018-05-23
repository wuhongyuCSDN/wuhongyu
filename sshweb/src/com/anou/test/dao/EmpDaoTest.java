package com.anou.test.dao;

import java.util.List;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import cn.abcdef.entity.Employee;
import cn.abcdef.service.IEmployeeService;

public class EmpDaoTest {
	ApplicationContext ac=new ClassPathXmlApplicationContext("");
	
	@Test
	public void getEmp() {
		IEmployeeService service =(IEmployeeService) ac.getBean("employeeService");
		List<Employee> all=service.getAll();
		System.out.println(all);
	}
	

}
