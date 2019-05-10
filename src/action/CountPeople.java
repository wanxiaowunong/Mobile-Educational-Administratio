package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dateop.opcourseapply;
import dateop.opgrade;

/**
 * Servlet implementation class CountPeople
 */
@WebServlet("/CountPeople")
public class CountPeople extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountPeople() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cnumber=request.getParameter("cnumber");	
		String snumber=request.getParameter("snumber");
		String identity=request.getParameter("identity");
//		System.out.println("username:"+new String(name.getBytes("iso-8859-1"),"utf-8"));
//		String courseplace=new String(request.getParameter("courseplace").getBytes("iso-8859-1"),"utf-8");//把从客户端传来的中文转为utf-8
//		new String(name.getBytes("iso-8859-1"),"utf-8")
//		String coursetime=request.getParameter("coursetime");
//		String coursetime=new String(request.getParameter("coursetime").getBytes("iso-8859-1"),"utf-8");
		String courseplace=request.getParameter("courseplace");	
		String coursetime=request.getParameter("coursetime");	
		String applyterm=request.getParameter("term");

//		int flag=Integer.parseInt(request.getParameter("flag"));   
		opgrade op=new opgrade();
//		System.out.println(op.count(cnumber,snumber));
					response.getOutputStream().write((""+op.count(cnumber,snumber)).getBytes("utf-8"));
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
