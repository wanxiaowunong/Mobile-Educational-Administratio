package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dateop.operate;

/**
 * Servlet implementation class delete
 */
@WebServlet("/delete")
public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");// �������ɵ��ĵ�����
		String userId = request.getParameter("userId");
		operate op=new operate();
		PrintWriter out = response.getWriter();// �õ�����ַ������
		if(op.delete(userId)) {
			out.println("<h1>ɾ���ɹ���</h1>");
			out.println("<a href='welcome.jsp'>����˴����ع������</a>");
			}
		else
			{out.println("<h1>ɾ��ʧ�ܣ�</h1>");
		out.println("<a href='welcome.jsp'>����˴����ع������</a>");}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
