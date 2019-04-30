package servlet;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dateop.operate;
import pao.Teachers;


/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
	    PrintWriter out=response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("pwd");
		String code=request.getParameter("code");
		HttpSession session= request.getSession();
		String scode= (String)session.getAttribute("code");
		if(!code.equals(scode)){
			out.println("错误的验证码<a href='denglu.jsp'>返回</a>");
			return;
		}
		operate op=new operate();
		if(op.find5(username, password)!=null) {
			Teachers tea=(Teachers) op.find5(username, password);
			if(tea.getTdept().equals("教务处")&&tea.getTadmin()==1)  //如果是教务处管理员身份，则跳转到教务管理页面
				{
				HttpSession sessi=request.getSession();
				
				request.setAttribute("teacher", tea);  
//				request.getRequestDispatcher("/welcome").forward(request, response);
				request.getRequestDispatcher("jiaowu.jsp").forward(request, response);		
				//response.sendRedirect("index.jsp");
				}	
		
			else if(tea.getTadmin()==1)  //如果是其他部门的管理员，则进入学院管理页面
				{
			
				HttpSession sessi=request.getSession();
	        
				request.setAttribute("teacher", tea);  //把当前用户信息传到jsp
//				request.getRequestDispatcher("/welcome").forward(request, response);
	       
				request.getRequestDispatcher("departadmin.jsp").forward(request, response);		
				//response.sendRedirect("index.jsp");
			}
			
		}
		else {
			out.print("<script>alert(\"用户名或密码错误，请重新登了\");;window.location.href='denglu.jsp';</script>");
//			out.println("用户名或密码错误，请重新登了<a href='denglu.jsp'>返回</a>");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
