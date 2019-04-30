package servlet;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dateop.operate;
import pao.Teachers;


/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
	    PrintWriter out=response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("pwd");
		String code=request.getParameter("code");
		HttpSession session= request.getSession();
		String scode= (String)session.getAttribute("code");
		if(!code.equals(scode)){
			out.println("�������֤��<a href='denglu.jsp'>����</a>");
			return;
		}
		operate op=new operate();
		if(op.find5(username, password)!=null) {
			Teachers tea=(Teachers) op.find5(username, password);
			if(tea.getTdept().equals("����")&&tea.getTadmin()==1)  //����ǽ��񴦹���Ա��ݣ�����ת���������ҳ��
				{
				HttpSession sessi=request.getSession();
				
				request.setAttribute("teacher", tea);  
//				request.getRequestDispatcher("/welcome").forward(request, response);
				request.getRequestDispatcher("jiaowu.jsp").forward(request, response);		
				//response.sendRedirect("index.jsp");
				}	
		
			else if(tea.getTadmin()==1)  //������������ŵĹ���Ա�������ѧԺ����ҳ��
				{
			
				HttpSession sessi=request.getSession();
	        
				request.setAttribute("teacher", tea);  //�ѵ�ǰ�û���Ϣ����jsp
//				request.getRequestDispatcher("/welcome").forward(request, response);
	       
				request.getRequestDispatcher("departadmin.jsp").forward(request, response);		
				//response.sendRedirect("index.jsp");
			}
			
		}
		else {
			out.print("<script>alert(\"�û�����������������µ���\");;window.location.href='denglu.jsp';</script>");
//			out.println("�û�����������������µ���<a href='denglu.jsp'>����</a>");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
