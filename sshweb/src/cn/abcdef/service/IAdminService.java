package cn.abcdef.service;

import cn.abcdef.entity.Admin;

/**
 * 管理员业务逻辑层接口
 * 

 * 
 */
public interface IAdminService {

	/**
	 * 注册
	 * @param admin
	 */
	void register(Admin admin);

	/**
	 * 登陆
	 * @param admin
	 * @return
	 */
	Admin login(Admin admin);

}
