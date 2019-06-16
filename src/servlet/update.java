package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.opcourseapply;
import dao.opstudent;


/**
 * Servlet implementation class update
 */
@WebServlet("/update")
public class update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");// 设置生成的文档类型
		request.setCharacterEncoding("utf-8");
		
	    PrintWriter out=response.getWriter();
		int id = Integer.parseInt(request.getParameter("userId"));
		String coursetime=request.getParameter("coursetime");
		String courseplace =request.getParameter("courseplace");
		String tnumber =request.getParameter("tnumber");
		System.out.println(tnumber);
		int flag = Integer.parseInt(request.getParameter("flag"));
		System.out.println(id+"  "+flag);
		opcourseapply op=new opcourseapply();
		if(op.update(id,flag,tnumber,coursetime,courseplace).equals("success"))
				out.print("<script>alert(\"修改成功\");;window.location.href='userlist2.jsp';</script>");
		else
			out.print("<script>alert(\"课程申请失败，已把错误信息发送给教师\");;window.location.href='userlist2.jsp';</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
