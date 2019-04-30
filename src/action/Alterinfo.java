package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dateop.operate;
import dateop.opteacher;
import pao.Student;
import pao.Teachers;

/**
 * Servlet implementation class alterinfo
 */
@WebServlet("/Alterinfo")
public class Alterinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Alterinfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("username");
		String pwd=request.getParameter("password");
		String identity=request.getParameter("identity");
//		PrintWriter out = response.getWriter();// 得到输出字符输出流
		
//		System.out.println("username:"+new String(name.getBytes("iso-8859-1"),"utf-8"));//把从客户端传来的中文转为utf-8
		System.out.println("username:"+name);
		System.out.println("password:"+pwd);
		System.out.println("identity:"+pwd);
		
		if(identity.equals("student"))
		{
			operate op=new operate();
			if(op.alert1(name,pwd))
				response.getOutputStream().write("success".getBytes("utf-8"));
			else
				response.getOutputStream().write("fail".getBytes());
//					out.println("login fail");
		}else if(identity.equals("teacher"))
			{
			opteacher op=new opteacher();
			if(op.alert1(name,pwd))
				response.getOutputStream().write("success".getBytes("utf-8"));
			else
				response.getOutputStream().write("fail".getBytes());
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
