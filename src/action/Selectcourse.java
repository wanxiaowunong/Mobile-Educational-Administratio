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
			if(!identity.equals("teacher")) 
			{
				response.getOutputStream().write(op.insert(snumber,cnumber,tnumber, coursetime).getBytes("utf-8"));
//				if(op.insert(snumber,cnumber,tnumber, coursetime))  //ѧ��ѡ�γɹ�
//				{
//					response.getOutputStream().write("success".getBytes());
//					System.out.println("ѡ�γɹ�");
//				}else    //ѧ��ѡ��ʧ��
//				{
//					response.getOutputStream().write("login fail".getBytes());
//					System.out.println("ѡ��ʧ��");
//				}
			}else  //��ʦ����γ�   ��ʱ�����������1��������ſγ̺󣬱���������������Ϣ����Ҫɾ��֮ǰ������ 2�״�����γ̣�����Ҫ�жϵص�ʱ���Ƿ��ͻ
				{
					opcourseapply op1=new opcourseapply();
					response.getOutputStream().write((op1.insert(snumber,cnumber,applyterm,courseplace,coursetime)).getBytes("utf-8"));
//					if(op1.insert(snumber,cnumber,applyterm,courseplace,coursetime))
//						response.getOutputStream().write("success".getBytes("utf-8"));
//					else
//						response.getOutputStream().write("login fail".getBytes("utf-8"));
					}
					
			}else if(flag==1)  //flag=1����ʾɾ��ѡ��
				{
				if(op.delete(snumber,cnumber))
					response.getOutputStream().write("success".getBytes("utf-8"));
				else
					response.getOutputStream().write("login fail".getBytes());
				}else if(flag==2) {  //��ɼ�
					System.out.println(snumber);
					if(!snumber.equals("null")) 
					{
						System.out.println("ѧ����ɼ�");
						List<Grade> grades=op.select(snumber,cnumber,applyterm);
						//��װ��json���󴫵ݸ�app��
						JSONArray array = new JSONArray();
						for(Grade bean:grades ) {
							JSONObject obj = new JSONObject();
							try {
								//todo ע�⣺�������Ķ������Բ����п�ֵ���еĻ����ܰѸ����Է�װ��json��
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
					else {  //ͨ���γ̱�źͽ�ʦ�Ų�ѯ�ɼ�
						System.out.println("��ʦ��ɼ�");
						List<Grade> grades=op.select1(cnumber,tnumber,applyterm);
						//��װ��json���󴫵ݸ�app��
						JSONArray array = new JSONArray();
						for(Grade bean:grades ) {
							JSONObject obj = new JSONObject();
							try {
								//todo ע�⣺�������Ķ������Բ����п�ֵ���еĻ����ܰѸ����Է�װ��json��
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
				else if(flag==3) {  //������ÿ�ſεĽ�ʦ
					opcourseapply op2=new opcourseapply();
					List<CourseMultiTeacher> cteachers=op2.findby(cnumber);
					for(CourseMultiTeacher bean:cteachers ) {  //�жϿγ��Ƿ���ѡ
						opgrade opg=new opgrade();
						bean.setCourseselected(""+opg.count(cnumber,bean.getTnumber(),getterm.getTerms()).size());    //flag=-1,��ʾ�γ��Ѿ�ѡ�ޣ�flag>0��ʾ�γ�֮ǰ�޹���flag=��������ʾ�γ���δѡ��
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
				else if(flag==4)  //����ɾ���γ���Ϣ
				{
					opcourseapply op3=new opcourseapply();
					response.getOutputStream().write((op3.delete(snumber,cnumber,applyterm)).getBytes("utf-8"));
					
				}
				else if(flag==5) //���������ʦ�Ƿ�ͬ����߲���ѧ����ѡ������
				{
					opgrade op4=new opgrade();
					if(op4.update(snumber,cnumber,applyterm,0,0,0)) {
						opmess op5=new opmess();
						op5.insert(tnumber,snumber,"��ʦ�������ѡ�Σ��뾡��ѡ��������ʦ����",getterm.getdate());
						response.getOutputStream().write("success".getBytes("utf-8"));
					}
						
					else
						response.getOutputStream().write("fail".getBytes("utf-8"));
						
				}else if(flag==6)//��ʦ������ѯ�Լ���ѧ��������Ŀγ̣����е���
				{
			
				}
//		out.println("success");
		
		
	}
		
private String getterm() {
		// TODO Auto-generated method stub
		return null;
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
