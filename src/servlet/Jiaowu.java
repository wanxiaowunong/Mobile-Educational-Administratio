package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.opcondition;
import util.getterm;

/**
 * Servlet implementation class Jiaowu
 */
@WebServlet("/Jiaowu")
public class Jiaowu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Jiaowu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //用来设置选课系统是否开启
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter out=response.getWriter();
		String s1=request.getParameter("xuankecheck");
		String term="";
		if(s1.equals("on"))  //表示教务管理员请求打开选课系统，此时应该开启选课系统
		{
			
			term=getterm.getTerms();
			opcondition op=new opcondition();
			op.update(term,"true");
			System.out.println(term);
			out.println("开启选课");
		}else {
			term=getterm.getTerms();
			opcondition op=new opcondition();
			op.update(term,"false");
			System.out.println(term);
			out.println("关闭选课");
		}
		
		out.println("<h1 >"+s1+term+" 已注销！</h1><br><br>");	
//		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
