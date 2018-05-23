package com.sathya.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sathya.hibernate.dao.EmployeeDAO;
import com.sathya.hibernate.dao.EmployeeDAOFactory;
import com.sathya.hibernate.model.Employee;

public final class PaginationServlet extends HttpServlet
{
	private  EmployeeDAO  dao;
	public void init(ServletConfig config) throws ServletException 
	{
		dao=EmployeeDAOFactory.getInstance();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		//read  page number
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		int start = pageNumber*3-3;
		List  list = dao.getEmployeesList(start);
		PrintWriter  out = response.getWriter();
		out.println("<center>");
		out.println("<table  border=3  width=80% height=70%>");
		out.println("<tr> <th> EMPNO </th> <th> ENAME </th> <th> SAL </th> <th> DEPTNO </th> </tr>");
		Iterator  it= list.iterator();
		while(it.hasNext())
		{
			Employee e = (Employee)it.next();
			out.println("<tr>");
			out.println("<td>" + e.getEmployeeNumber()+"</td>");
			out.println("<td>" + e.getEmployeeName()+"</td>");
			out.println("<td>" + e.getEmployeeSal()+"</td>");
			out.println("<td>" + e.getDeptNumber()+"</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		int count = dao.getEmployeesCount();
		int pages = count/3;
		if( count > pages*3)
		{
			pages++;
		}
		for(int i=1; i<=pages; i++)
		{
			out.println("<b><a  href=pagination?pageNumber="+i+">"+i+"</a></b>");
			out.println("&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;");
		}
		out.println("</center>");
		out.close();
	}
}



