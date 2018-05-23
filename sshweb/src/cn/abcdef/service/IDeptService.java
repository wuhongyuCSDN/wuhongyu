package cn.abcdef.service;

import java.util.List;

import cn.abcdef.entity.Dept;

/**
 * 部门模块业务逻辑层接口

 *
 */
public interface IDeptService {

	/**
	 * 查询全部
	 * @return 返回全部信息
	 */
	List<Dept> getAll();

	/**
	 * 根据主键查询
	 * @param id  主键
	 * @return 返回查询后的结果
	 */
	Dept findById(int id);
}
