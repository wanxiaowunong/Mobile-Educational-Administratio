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

import dateop.opcourse;
import dateop.opcourseapply;
import dateop.opgrade;
import pao.Course;
import pao.CourseMultiTeacher;
import pao.Grade;

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
//		String courseplace=new String(request.getParameter("courseplace").getBytes("iso-8859-1"),"utf-8");//�Ѵӿͻ��˴���������תΪutf-8
//		new String(name.getBytes("iso-8859-1"),"utf-8")
//		String coursetime=request.getParameter("coursetime");
//		String coursetime=new String(request.getParameter("coursetime").getBytes("iso-8859-1"),"utf-8");
		String courseplace=request.getParameter("courseplace");	
		String coursetime=request.getParameter("coursetime");	
		String applyterm=request.getParameter("term");

		int flag=Integer.parseInt(request.getParameter("flag"));   //flag��־λ   0��ʾѡ�Σ��ύ�ɼ���1��ʾ��ɼ�
//		PrintWriter out = response.getWriter();// �õ�����ַ������
		
//		System.out.println("username:"+new String(name.getBytes("iso-8859-1"),"utf-8"));//�Ѵӿͻ��˴���������תΪutf-8
		System.out.println("cnumber:"+cnumber);
		System.out.println("snumber:"+snumber);
		System.out.println("tnumber:"+tnumber);//		System.out.println("courseplace:"+courseplace);
//		System.out.println(identity);
		opgrade op=new opgrade();
		if(flag==0)    //ѧ������ѡ�κͲ���ɼ��ͽ�ʦ���뿪��
		{
			System.out.println(identity);
			if(!identity.equals("teacher")) {
				if(op.insert(snumber,cnumber,tnumber, coursetime))
					response.getOutputStream().write("success".getBytes("utf-8"));
				else
					response.getOutputStream().write("login fail".getBytes());
				}else{
					opcourseapply op1=new opcourseapply();
					if(op1.insert(snumber,cnumber,applyterm,courseplace,coursetime))
						response.getOutputStream().write("success".getBytes("utf-8"));
					else
						response.getOutputStream().write("login fail".getBytes());
				}
			
				
		}else if(flag==1)
		{
			if(op.delete(snumber,cnumber))
				response.getOutputStream().write("success".getBytes("utf-8"));
				else
				response.getOutputStream().write("login fail".getBytes());
		}
		else if(flag==2) {  //��ɼ�
			List<Grade> grades=op.select(snumber,cnumber);

			   //��װ��json���󴫵ݸ�app��
			JSONArray array = new JSONArray();
			for(Grade bean:grades ) {
			JSONObject obj = new JSONObject();
			try {
				//todo ע�⣺�������Ķ������Բ����п�ֵ���еĻ����ܰѸ����Է�װ��json��
					obj.put("snumber", bean.getSnumber());
					obj.put("cnumber", bean.getCnumber());
					obj.put("grade", bean.getGrade());
			} catch (Exception e) {
				
			}
			array.put(obj);
		}
			System.out.println(array.toString());
			response.getOutputStream().write(array.toString().getBytes("utf-8"));
		}
		else if(flag==3) {  //������ÿ�ſεĽ�ʦ
			opcourseapply op2=new opcourseapply();
			List<CourseMultiTeacher> cteachers=op2.findby(cnumber);
			for(CourseMultiTeacher bean:cteachers ) {  //�жϿγ��Ƿ���ѡ
				opgrade opg=new opgrade();
			   bean.setCourseselected(""+opg.count(cnumber,bean.getTnumber()));    //flag=-1,��ʾ�γ��Ѿ�ѡ�ޣ�flag>0��ʾ�γ�֮ǰ�޹���flag=��������ʾ�γ���δѡ��
			}
			   //��װ��json���󴫵ݸ�app��
						JSONArray array = new JSONArray();
						for(CourseMultiTeacher bean:cteachers ) {
						JSONObject obj = new JSONObject();
						try {
							//todo ע�⣺�������Ķ������Բ����п�ֵ���еĻ����ܰѸ����Է�װ��json��
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
//		out.println("success");
		
		
	}
		
//		//����1  ֻ������֤�Ƿ���ɵ�½
//		//�˴�Ĭ����ѧ���û������֤
//		if(op.find1(name, pwd)) 
//		{
//			response.getOutputStream().write("success".getBytes("utf-8"));
//		}	
//		else
//			response.getOutputStream().write("login fail".getBytes());
	
		//����2  �����ݿ���н���������student���󣬲��ж��û���Ϣ�Ƿ��½�ɹ�
		
			
			
				
				



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
