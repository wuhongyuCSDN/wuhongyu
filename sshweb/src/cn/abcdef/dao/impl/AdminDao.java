package cn.abcdef.dao.impl;

import org.hibernate.SessionFactory;
import cn.abcdef.dao.IAdminDao;
import cn.abcdef.entity.Admin;

public class AdminDao implements IAdminDao {

	// IOC容器(依赖)注入SessionFactory对象
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Admin findByAdmin(Admin admin) {
		return (Admin) sessionFactory.getCurrentSession()//
				.createQuery("from Admin where adminName=? and pwd=?")//
				.setString(0, admin.getAdminName())//
				.setString(1, admin.getPwd())//
				.uniqueResult();
	}

	@Override
	public void save(Admin admin) {
		sessionFactory.getCurrentSession().save(admin);
	}

}
