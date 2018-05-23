package cn.abcdef.service.impl;

import java.util.List;

import cn.abcdef.dao.IDeptDao;
import cn.abcdef.entity.Dept;
import cn.abcdef.service.IDeptService;

public class DeptService implements IDeptService {
	
	private IDeptDao deptDao;
	public void setDeptDao(IDeptDao deptDao) {
		this.deptDao = deptDao;
	}

	@Override
	public Dept findById(int id) {
		return deptDao.findById(id);
	}

	@Override
	public List<Dept> getAll() {
		return deptDao.getAll();
	}

}
