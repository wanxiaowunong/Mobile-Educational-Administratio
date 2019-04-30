package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dateop.operate;

/**
 * Servlet implementation class regiser
 */
@WebServlet("/regiser")
public class regiser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public regiser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");// 设置生成的文档类型
		
		String username=request.getParameter("username");
		String pwd=request.getParameter("pwd");
		String birthday=request.getParameter("birthday").trim();
		PrintWriter out = response.getWriter();// 得到输出字符输出流
		out.println("<HTML>");// 输出相应的HTML源文件
		out.println("<BODY>");
		out.println(username);
		operate op=new operate();
//		if(op.insert(username, pwd,birthday)) {
//			out.println("<h1>注册成功！</h1>");
//			out.println("<a href='index.jsp'>点击此处返回首页</a>");
//			}
//		else
//			out.println("<h1>注册失败！</h1>");
		
		
//		if(username==""||username.equals("admin"))
//		{
//			request.getRequestDispatcher("resfaill").forward(request, response);
//			}
//		else if(pwd.length()>6||pwd.length()>12)
//		{
//			response.sendRedirect("resfaill");}
//	       else if(pwd.length()!=pwd1.length()||!pwd.equals(pwd1)) {
//	    	   response.sendRedirect("resfaill");}
//	       else
//	    	   response.sendRedirect("resuccess"); ; 
		//out.println("<H2>用户输入信息如下：</H2>");
		//out.println(username+"  "+pwd);
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();// 关闭输出流


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
