package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.Student;
import bean.Teachers;
import dao.*;

/**
 * Servlet implementation class Alogin
 */
@WebServlet("/Alogininfor")
public class Alogininfor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Alogininfor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("username");
		String pwd=request.getParameter("password");
		String identity=request.getParameter("identity");
//		PrintWriter out = response.getWriter();// �õ�����ַ������
		
//		System.out.println("username:"+new String(name.getBytes("iso-8859-1"),"utf-8"));//�Ѵӿͻ��˴���������תΪutf-8
		System.out.println("username:"+name);
		System.out.println("password:"+pwd);
		System.out.println("identity:"+pwd);
		
		if(identity.equals("student"))
		{
			opstudent op=new opstudent();
			Student student=op.find2(name,pwd);
			System.out.println(student.getSnumber());
				if(student.getPwd()!=null&&student.getPwd().equals(pwd))
//					response.getOutputStream().write("success".getBytes("utf-8"));
				{
					   //��װ��json���󴫵ݸ�app��
					JSONArray array = new JSONArray();
					JSONObject obj = new JSONObject();
					try {
						obj.put("image","");
							obj.put("number", student.getSnumber());
							obj.put("name", student.getSname());
							obj.put("sex", student.getSsex());
							obj.put("dept", student.getSdept());
							obj.put("pwd", student.getPwd());		
					} catch (Exception e) {
						
					}
					array.put(obj);
//					out.println("success");
//					System.out.println(array.toString());
					response.getOutputStream().write((array.toString()).getBytes("utf-8"));
					
					
				}
				else
					response.getOutputStream().write("fail".getBytes());
//					out.println("login fail");
		}else if(identity.equals("teacher"))
			{
			opteacher op=new opteacher();
			Teachers tea=op.find2(name,pwd);
			System.out.println(tea.getTnumber());
				if(tea.getPwd()!=null&&tea.getPwd().equals(pwd))
//					response.getOutputStream().write("success".getBytes("utf-8"));
				{
					   //��װ��json���󴫵ݸ�app��
					JSONArray array = new JSONArray();
					JSONObject obj = new JSONObject();
					try {
						obj.put("image","");
							obj.put("number", tea.getTnumber());
							obj.put("name", tea.getTname());
							obj.put("dept", tea.getTdept());
							obj.put("pwd", tea.getPwd());		
					} catch (Exception e) {
						
					}
					array.put(obj);
//					out.println("success");
					System.out.println();
					response.getOutputStream().write((array.toString()).getBytes("utf-8"));
					
					
				}
				else
					response.getOutputStream().write("fail".getBytes());
//					out.println("login fail");
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
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
