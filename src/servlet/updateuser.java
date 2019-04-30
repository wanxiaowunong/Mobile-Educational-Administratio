package servlet;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dateop.operate;
import pao.Student;

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
		String userId = request.getParameter("userId");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String birthday = request.getParameter("birthday");
		out.println("<html>");
		out.println("<body>");
		
		operate op=new operate();
//		if(op.update(Integer.parseInt(userId), username, password, birthday)) {
//			response.sendRedirect("welcome.jsp");}
//		else 
//		out.println(username+"更新失败！！");
		out.println("</body>");out.println("</html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
