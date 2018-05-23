package cn.abcdef.dao;

import java.util.List;

import cn.abcdef.entity.Dept;

/**
 * 部门模块dao接口设计
 * 

 * 
 */
public interface IDeptDao {

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
