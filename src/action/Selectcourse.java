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
import bean.CourseMultiTeacher;
import bean.Courseapply;
import bean.Grade;
import dao.opcourse;
import dao.opcourseapply;
import dao.opgrade;
import dao.opmess;
import util.getterm;

/**
 * Servlet implementation class selectcourse
 */
@WebServlet("/Selectcourse")
public class Selectcourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Selectcourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cnumber=request.getParameter("cnumber");	
		String snumber=request.getParameter("snumber");
		String identity=request.getParameter("identity");
		String tnumber=request.getParameter("tnumber");
//		System.out.println("username:"+new String(name.getBytes("iso-8859-1"),"utf-8"));
//		String courseplace=new String(request.getParameter("courseplace").getBytes("iso-8859-1"),"utf-8");//把从客户端传来的中文转为utf-8
//		new String(name.getBytes("iso-8859-1"),"utf-8")
//		String coursetime=request.getParameter("coursetime");
//		String coursetime=new String(request.getParameter("coursetime").getBytes("iso-8859-1"),"utf-8");
		String courseplace=request.getParameter("courseplace");	
		String coursetime=request.getParameter("coursetime");	
		String applyterm=request.getParameter("term");

		int flag=Integer.parseInt(request.getParameter("flag"));   //flag标志位   0表示选课，提交成绩，1表示查成绩
//		PrintWriter out = response.getWriter();// 得到输出字符输出流
		
//		System.out.println("username:"+new String(name.getBytes("iso-8859-1"),"utf-8"));//把从客户端传来的中文转为utf-8
		System.out.println("cnumber:"+cnumber);
		System.out.println("snumber:"+snumber);
		System.out.println("tnumber:"+tnumber);//		System.out.println("courseplace:"+courseplace);
//		System.out.println(identity);
		opgrade op=new opgrade();
		if(flag==0)    //学生用于选课和插入成绩和教师申请开课
		{
			System.out.println(identity);
			if(!identity.equals("teacher")) 
			{
				response.getOutputStream().write(op.insert(snumber,cnumber,tnumber, coursetime).getBytes("utf-8"));
//				if(op.insert(snumber,cnumber,tnumber, coursetime))  //学生选课成功
//				{
//					response.getOutputStream().write("success".getBytes());
//					System.out.println("选课成功");
//				}else    //学生选课失败
//				{
//					response.getOutputStream().write("login fail".getBytes());
//					System.out.println("选课失败");
//				}
			}else  //老师申请课程   此时有两种情况，1已申请该门课程后，本次申请对其更改信息，需要删除之前的申请 2首次申请课程，但需要判断地点时间是否冲突
				{
					opcourseapply op1=new opcourseapply();
					response.getOutputStream().write((op1.insert(snumber,cnumber,applyterm,courseplace,coursetime)).getBytes("utf-8"));
//					if(op1.insert(snumber,cnumber,applyterm,courseplace,coursetime))
//						response.getOutputStream().write("success".getBytes("utf-8"));
//					else
//						response.getOutputStream().write("login fail".getBytes("utf-8"));
					}
					
			}else if(flag==1)  //flag=1，表示删除选课
				{
				if(op.delete(snumber,cnumber))
					response.getOutputStream().write("success".getBytes("utf-8"));
				else
					response.getOutputStream().write("login fail".getBytes());
				}else if(flag==2) {  //查成绩
					System.out.println(snumber);
					if(!snumber.equals("null")) 
					{
						System.out.println("学生查成绩");
						List<Grade> grades=op.select(snumber,cnumber,applyterm);
						//组装成json对象传递给app端
						JSONArray array = new JSONArray();
						for(Grade bean:grades ) {
							JSONObject obj = new JSONObject();
							try {
								//todo 注意：传过来的对象属性不能有空值，有的话不能把该属性分装到json中
								obj.put("snumber", bean.getSnumber());
								obj.put("sname", bean.getSname());
								obj.put("cnumber", bean.getCnumber());
								obj.put("cname", bean.getCname());
								obj.put("tnumber", bean.getTnumber());
								obj.put("tname", bean.getTname());
								obj.put("term", bean.getTerm());
								obj.put("grade", bean.getGrade());
								obj.put("score", bean.getScore());
							} catch (Exception e) {
					
							}
							array.put(obj);
						}
						System.out.println(array.toString());
						response.getOutputStream().write(array.toString().getBytes("utf-8"));
					}
					else {  //通过课程编号和教师号查询成绩
						System.out.println("教师查成绩");
						List<Grade> grades=op.select1(cnumber,tnumber,applyterm);
						//组装成json对象传递给app端
						JSONArray array = new JSONArray();
						for(Grade bean:grades ) {
							JSONObject obj = new JSONObject();
							try {
								//todo 注意：传过来的对象属性不能有空值，有的话不能把该属性分装到json中
								obj.put("snumber", bean.getSnumber());
								obj.put("sname", bean.getSname());
								obj.put("cnumber", bean.getCnumber());
								obj.put("cname", bean.getCname());
								obj.put("tnumber", bean.getTnumber());
								obj.put("tname", bean.getTname());
								obj.put("term", bean.getTerm());
								obj.put("grade", bean.getGrade());
								obj.put("score", bean.getScore());
							} catch (Exception e) {
					
							}
							array.put(obj);
						}
						System.out.println(array.toString());
						response.getOutputStream().write(array.toString().getBytes("utf-8"));
					}
				}
				else if(flag==3) {  //用来查每门课的教师
					opcourseapply op2=new opcourseapply();
					List<CourseMultiTeacher> cteachers=op2.findby(cnumber);
					for(CourseMultiTeacher bean:cteachers ) {  //判断课程是否已选
						opgrade opg=new opgrade();
						bean.setCourseselected(""+opg.count(cnumber,bean.getTnumber(),getterm.getTerms()).size());    //flag=-1,表示课程已经选修，flag>0表示课程之前修过，flag=“”，表示课程尚未选修
					}
					//组装成json对象传递给app端
						JSONArray array = new JSONArray();
						for(CourseMultiTeacher bean:cteachers ) {
							JSONObject obj = new JSONObject();
							try {
							//todo 注意：传过来的对象属性不能有空值，有的话不能把该属性分装到json中
								obj.put("tname", bean.getTname());
								obj.put("tnumber", bean.getTnumber());
								obj.put("courseplace", bean.getCourseplace());
								obj.put("coursetime", bean.getCoursetime());
								obj.put("courseselected", bean.getCourseselected());

						} catch (Exception e) {
							
						}
						array.put(obj);
					}
						System.out.println(array.toString());
						response.getOutputStream().write(array.toString().getBytes("utf-8"));
			
				}
				else if(flag==4)  //用来删除课程信息
				{
					opcourseapply op3=new opcourseapply();
					response.getOutputStream().write((op3.delete(snumber,cnumber,applyterm)).getBytes("utf-8"));
					
				}
				else if(flag==5) //用来处理教师是否同意或者驳回学生的选课请求
				{
					opgrade op4=new opgrade();
					if(op4.update(snumber,cnumber,applyterm,0,0,0)) {
						opmess op5=new opmess();
						op5.insert(tnumber,snumber,"教师驳回你的选课，请尽快选择其他教师！！",getterm.getdate());
						response.getOutputStream().write("success".getBytes("utf-8"));
					}
						
					else
						response.getOutputStream().write("fail".getBytes("utf-8"));
						
				}else if(flag==6)//教师用来查询自己本学期已申请的课程，进行点名
				{
			
				}
//		out.println("success");
		
		
	}
		
private String getterm() {
		// TODO Auto-generated method stub
		return null;
	}

//		//方法1  只用来验证是否完成登陆
//		//此处默认是学生用户完成认证
//		if(op.find1(name, pwd)) 
//		{
//			response.getOutputStream().write("success".getBytes("utf-8"));
//		}	
//		else
//			response.getOutputStream().write("login fail".getBytes());
	
		//方法2  与数据库进行交互，返回student对象，并判断用户信息是否登陆成功
		
			
			
				
				



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
