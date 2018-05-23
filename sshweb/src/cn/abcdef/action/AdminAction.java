package cn.abcdef.action;

import cn.abcdef.entity.Admin;
import cn.abcdef.service.IAdminService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 管理员登陆注册模块
 * 1. 登陆
 *
 */
public class AdminAction extends ActionSupport implements ModelDriven<Admin> {
//public class AdminAction extends ActionSupport {
	// 封装请求数据
	private Admin admin = new Admin();
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Admin getAdmin() {
		return admin;
	}
	
	public Admin getModel() {
		return admin;
	}
	
	// 调用Service
	private IAdminService adminService;
	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}
	
	/**
	 * 登陆
	 */
	public String login(){
		// 登陆验证
		System.out.println("admin"+admin);
		Admin adminInfo = adminService.login(admin);
		System.out.println("adminInfo"+adminInfo);
		// 验证
		if (adminInfo == null){
			// 登陆失败
			return "loginFaild";
		} else {
			// 登陆成功, 保存数据到session
			ActionContext.getContext().getSession().put("adminInfo", adminInfo);
			return "index";
		}
	}
	
	

}