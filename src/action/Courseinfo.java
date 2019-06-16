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

import bean.Course;
import bean.Student;
import dao.opcourse;
import dao.opcourseapply;
import dao.opgrade;
import dao.opstudent;

/**
 * Servlet implementation class courseinfo
 */
@WebServlet("/Courseinfo")
public class Courseinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Courseinfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String cdept=request.getParameter("cdept");	
		String cyear=request.getParameter("cyear");
		String snumber=request.getParameter("snumber");
		String identity=request.getParameter("identity");
//		PrintWriter out = response.getWriter();// 得到输出字符输出流
		
//		System.out.println("username:"+new String(name.getBytes("iso-8859-1"),"utf-8"));//把从客户端传来的中文转为utf-8
		System.out.println("cdept:"+cdept);
		System.out.println("cyear:"+cyear);
		

		List<Course> courses=null;
		opcourse op=new opcourse();
		courses=op.select(cdept,cyear,snumber);   //返回某个专业的课程信息
		if(identity.equals("student")) {
			
			for(Course bean:courses ) {  //判断课程是否已选
				opgrade opg=new opgrade();
			   bean.setFlag(opg.find(bean.getCnumber(),snumber));    //flag=-1,表示课程已经选修，flag>0表示课程之前修过，flag=“”，表示课程尚未选修
			}
			
		 }
		else if(identity.equals("teacher")) {   //判断课程是否已申请
			 
			 for(Course bean:courses ) {  //判断课程是否已申请
					opcourseapply opc=new opcourseapply();
					bean.setFlag(opc.find(bean.getCnumber(),snumber));
		 }
		}
			
				   //组装成json对象传递给app端
				JSONArray array = new JSONArray();
				for(Course bean:courses ) {
				JSONObject obj = new JSONObject();
				try {
					//todo 注意：传过来的对象属性不能有空值，有的话不能把该属性分装到json中
						obj.put("cnumber", bean.getCnumber());
						obj.put("cname", bean.getCname());
						obj.put("cdept", bean.getCdept());
						obj.put("ctime", bean.getCtime());
						obj.put("cstart", bean.getCstart());	
						obj.put("cend", bean.getCend());
						obj.put("cyear", bean.getCyear());
						obj.put("flag",bean.getFlag());
				} catch (Exception e) {
					
				}
				array.put(obj);
				}
	
			
				
				
//				out.println("success");
				System.out.println(array.toString());
				response.getOutputStream().write(array.toString().getBytes("utf-8"));
				
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
