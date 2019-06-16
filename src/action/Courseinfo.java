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
//		PrintWriter out = response.getWriter();// �õ�����ַ������
		
//		System.out.println("username:"+new String(name.getBytes("iso-8859-1"),"utf-8"));//�Ѵӿͻ��˴���������תΪutf-8
		System.out.println("cdept:"+cdept);
		System.out.println("cyear:"+cyear);
		

		List<Course> courses=null;
		opcourse op=new opcourse();
		courses=op.select(cdept,cyear,snumber);   //����ĳ��רҵ�Ŀγ���Ϣ
		if(identity.equals("student")) {
			
			for(Course bean:courses ) {  //�жϿγ��Ƿ���ѡ
				opgrade opg=new opgrade();
			   bean.setFlag(opg.find(bean.getCnumber(),snumber));    //flag=-1,��ʾ�γ��Ѿ�ѡ�ޣ�flag>0��ʾ�γ�֮ǰ�޹���flag=��������ʾ�γ���δѡ��
			}
			
		 }
		else if(identity.equals("teacher")) {   //�жϿγ��Ƿ�������
			 
			 for(Course bean:courses ) {  //�жϿγ��Ƿ�������
					opcourseapply opc=new opcourseapply();
					bean.setFlag(opc.find(bean.getCnumber(),snumber));
		 }
		}
			
				   //��װ��json���󴫵ݸ�app��
				JSONArray array = new JSONArray();
				for(Course bean:courses ) {
				JSONObject obj = new JSONObject();
				try {
					//todo ע�⣺�������Ķ������Բ����п�ֵ���еĻ����ܰѸ����Է�װ��json��
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
