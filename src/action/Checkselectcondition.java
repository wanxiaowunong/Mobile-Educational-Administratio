package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dateop.opcourse;
import dateop.operate;
import dateop.opteacher;

/**
 * Servlet implementation class checkselectcondition
 */
@WebServlet("/Checkselectcondition")   //�����ж��Ƿ��ѿ�ͨѡ��
public class Checkselectcondition extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkselectcondition() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String time=request.getParameter("time");
//		PrintWriter out = response.getWriter();// �õ�����ַ������
		opcourse op=new opcourse();
		
			response.getOutputStream().write(op.selectcondition(time).getBytes());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
