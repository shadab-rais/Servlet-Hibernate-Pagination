package com.sathya.hibernate.dao;
public class EmployeeDAOFactory
{
	public  static  EmployeeDAO   getInstance()
	{
		return  new  EmployeeDAOImpl();
	}
}
