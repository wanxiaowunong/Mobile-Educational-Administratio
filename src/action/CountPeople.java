package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.Grade;
import bean.Student;
import dao.opcourseapply;
import dao.opgrade;

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
		String cyear=request.getParameter("cyear");	
		String coursetime=request.getParameter("coursetime");	
		String applyterm=request.getParameter("term");

//		int flag=Integer.parseInt(request.getParameter("flag"));   
		opgrade op=new opgrade();
//		System.out.println(op.count(cnumber,snumber));
		List<Student> students=op.count(cnumber,snumber,cyear);
		JSONArray array = new JSONArray();
		for(Student bean:students ) {
			JSONObject obj = new JSONObject();
			try {
				//todo 注意：传过来的对象属性不能有空值，有的话不能把该属性分装到json中
				obj.put("number", bean.getSnumber());
				obj.put("name", bean.getSname());
//				obj.put("ssex", bean.getSsex());
				obj.put("dept", bean.getSdept());
				obj.put("image", null+"");
				obj.put("pwd", bean.getSsex());
			} catch (Exception e) {
	
			}
			array.put(obj);
		}
		System.out.println(array.toString());
		response.getOutputStream().write(array.toString().getBytes("utf-8"));
//					response.getOutputStream().write((""+op.count(cnumber,snumber,cyear)).getBytes("utf-8"));
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
