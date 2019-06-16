package servlet;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.opstudent;

/**
 * Servlet implementation class updateuser
 */
@WebServlet("/updateuser")
public class updateuser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateuser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");// 设置生成的文档类型
		PrintWriter out = response.getWriter();// 得到输出字符输出流
		String snumber = request.getParameter("snumber");
		String pwd = request.getParameter("pwd");
		String dept = request.getParameter("dept");
		String mess="";
		opstudent op=new opstudent();
		if(op.alert1(snumber,pwd)) {
//			request.setAttribute("dept",dept);
//			out.print("<script>alert(\"修改成功\");;window.location.href='userlist3.jsp';</script>");
//			out.print("<script>alert(\"修改成功\");;window.close();</script>");
			mess="修改成功！";
//			request.setAttribute("mess",mess);
			request.getRequestDispatcher("userlist3.jsp").forward(request, response);	
			}
		else 
		{
			mess="修改失败！";
			out.print("<script>alert(\"修改失败\");;window.location.href='userlist3.jsp';</script>");
		}
		}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
