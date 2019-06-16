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
import bean.CourseInfotable;
import dao.opcoursetable;

/**
 * Servlet implementation class CourseTableInfo
 */
@WebServlet("/CourseTableInfo")
public class CourseTableInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseTableInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cyear=request.getParameter("cyear");
		String number=request.getParameter("number");
		String identity=request.getParameter("identity");
		
//		System.out.println("username:"+new String(name.getBytes("iso-8859-1"),"utf-8"));//把从客户端传来的中文转为utf-8
		System.out.println("number:"+number);
		System.out.println("cyear:"+cyear);
		opcoursetable op=new opcoursetable();
		List<CourseInfotable> courses=op.select(cyear,number,identity);
		   //组装成json对象传递给app端
					JSONArray array = new JSONArray();
					for(CourseInfotable bean:courses ) {
					JSONObject obj = new JSONObject();
					try {
						//todo 注意：传过来的对象属性不能有空值，有的话不能把该属性分装到json中
							obj.put("cname", bean.getCname());
							obj.put("cplace", bean.getCplace());
							obj.put("ctname", bean.getCtname());
							obj.put("zhoushu", bean.getZhoushu());
							obj.put("jieshu", bean.getJieshu());
							obj.put("time1", bean.getTime1());	
							obj.put("time2", bean.getTime2());
							obj.put("xingqi", bean.getXingqi());
							obj.put("which",bean.getWhich());
					} catch (Exception e) {
						
					}
					array.put(obj);
					}
		
				
					
					
//					out.println("success");
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
