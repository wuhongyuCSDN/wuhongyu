package cn.abcdef.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.web.client.HttpServerErrorException;

import cn.abcdef.entity.Dept;
import cn.abcdef.entity.Employee;
import cn.abcdef.service.IDeptService;
import cn.abcdef.service.IEmployeeService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.sun.net.httpserver.HttpServer;

/**
 * 员工模块控制器开发：
 * 1. 员工列表展示
 * 2. 添加员工
 * 3. 修改员工信息
 * 5. 删除
 *
 */
public class EmployeeAction extends ActionSupport implements ModelDriven<Employee>, RequestAware{
	
	/*******一、封装数据********/
	private Employee employee = new Employee();   // 【模型驱动】
	// 封装请求的部门id(下拉列表的实际的值)
	private int deptId;
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public int getDeptId() {
		return deptId;
	}
	
	
	@Override
	public Employee getModel() {
		return employee;   // 返回实例化后的对象
	}
	
	
	/*******二、注入员工Service********/
	private IEmployeeService employeeService;
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	// 部门Service
	private IDeptService deptService;
	public void setDeptService(IDeptService deptService) {
		this.deptService = deptService;
	}
	

	/**
	 * 1. 员工列表展示
	 */
	public String list() {
		// 查询所有员工
		List<Employee> listEmp = employeeService.getAll();
		// 保存到request
		//request.put("listEmp", listEmp);
		System.out.println(listEmp);
		return "list";
	}
	
	/**
	 * 2. 添加员工 - 进入添加页面
	 */
	public String viewAdd(){
		// 查询所有部门信息, 保存到request
		List<Dept> listDept = deptService.getAll();
		request.put("listDept", listDept);
		return "add";
	}
	
	/**
	 * 2. 添加员工 - 添加员工数据
	 */
	public String save(){
		
		// 先根据部门主键查询
		Dept dept = deptService.findById(deptId);
		// 设置到员工对象中
		employee.setDept(dept);
		
		// 调用Service，保存员工
		employeeService.save(employee);
		return "listAction";  // 重定向到Action
	}
	
	/**
	 *  3. 修改员工信息 - 进入修改视图
	 */
	public String viewUpdate(){
		// 获取要修改的记录的id
		int id = employee.getId();
		
		// 1. 根据员工的主键查询  (lazy="false")
		Employee emp = employeeService.findById(id);  // 已经有部门信息
		// 2. 查询所有的部门
		List<Dept> listDept =  deptService.getAll();
		
		// 数据回显
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.pop();// 移除栈顶元素
		vs.push(emp); // 入栈
		
		// 保存
		request.put("listDept", listDept);
		
		return "edit";
	}
	
	/**
	 *  4. 修改员工信息 - 确认修改
	 */
	public String update() {
		
		//1. 先根据部门id， 查询部门对象; 再设置到员工属性中
		Dept dept = deptService.findById(deptId);
		employee.setDept(dept);
		
		//2. 更新员工
		employeeService.update(employee);
		
		return "listAction";  // 重定向到列表
	}
	
	/**
	 *  5. 修改员工信息 - 删除
	 */
	public String delete(){
		// 获取要删除员工的主键
		int empId = employee.getId();
		// 调用service删除
		employeeService.delete(empId);
		return "listAction";
	}
	
	//部分1和部分3，放在框架里被封装了，我们只能处理部分2
	public void myTest() {
		//ServletActionContext sac=ServletActionContext.getServletContext();
		//部分1 与web环境有关
		HttpServletRequest servletRequest=ServletActionContext.getRequest();//得到一个对象
		//存放参数的方式
		//servletRequest.setAttribute();
		
		//部分2 与web环境无关
		Map<String,Object> myMap=new HashMap<String,Object>();
		myMap.put("myEmp", new Employee());
		myMap.put("empName", "abc");
		
		//部分3 与web环境有关
		//把map里的键值存入HttpServletRequest
		Set<String> keySet=myMap.keySet();
		for(String key:keySet) {
			servletRequest.setAttribute(key, myMap.get(key));
		}
	}
	
	
	
	
	// 接收框架运行时候传入的代表request对象的map
	private Map<String, Object> request;
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
}