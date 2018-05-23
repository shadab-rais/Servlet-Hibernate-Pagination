package com.sathya.hibernate.dao;
import java.util.List;
public interface EmployeeDAO
{
	int   getEmployeesCount();
	List  getEmployeesList(int start);
}
