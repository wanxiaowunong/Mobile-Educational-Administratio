package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teachers;
import dao.opcourse;
import util.getterm;

/**
 * Servlet implementation class addcourse
 */
@WebServlet("/addcourse")
public class addcourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addcourse() {
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
	    Teachers tea=(Teachers)request.getSession().getAttribute("teacher");
		String coursename=request.getParameter("coursename");
		String coursenumber=request.getParameter("coursenumber");
		String coursetime=request.getParameter("coursetime");
		String coursstart=request.getParameter("coursstart");
		String coursend=request.getParameter("coursend");
		opcourse op=new opcourse();
		if(op.insert(coursenumber,coursename,tea.getTdept(),coursetime,coursstart,coursend,getterm.getTerms())) {
			//表示插入成功
			System.out.println("true");
//			out.print("<script>alert(\"修改成功\");;request.setAttribute(\"teacher\", tea);window.location.href='departadmin.jsp';</script>");
			out.print("<script>alert(\"申请课程成功，点击返回主页面\");</script>");	
			
		}
		else{
			System.out.println("error");
			out.print("<script>alert(\"申请课程失败，点击返回主页面\");;window.location.href='departadmin.jsp'</script>");
		}
		request.setAttribute("teacher", tea);  //把当前用户信息传到jsp
		request.getRequestDispatcher("departadmin.jsp").forward(request, response);	
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
