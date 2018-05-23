package com.sathya.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

import com.sathya.hibernate.model.Employee;
import com.sathya.hibernate.util.HibernateUtil;

public class EmployeeDAOImpl implements EmployeeDAO 
{
	private  static  SessionFactory factory;
	public EmployeeDAOImpl() 
	{
		factory=HibernateUtil.getSessionFactory();
	}
	public int getEmployeesCount() 
	{
		Session  session=factory.openSession();
		Criteria  crit=session.createCriteria(Employee.class);
		Projection p1=Projections.rowCount();
		crit.setProjection(p1);
		List  list = crit.list();
		session.close();
		long counts=(Long)list.get(0);
		int count=(int)counts;
		return count;
	}
	public List getEmployeesList(int start)
	{
		Session session=factory.openSession();
		Criteria  crit=session.createCriteria(Employee.class);
		crit.setFirstResult(start);
		crit.setMaxResults(3);
		List  list = crit.list();
		session.close();
		return list;
	}
}


