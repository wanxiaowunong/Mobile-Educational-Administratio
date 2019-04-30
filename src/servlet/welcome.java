package servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class welcome
 */
@WebServlet("/welcome")
public class welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public welcome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session=request.getSession();
		String username=session.getAttribute("user").toString();
		PrintWriter out=response.getWriter();
		out.println("<html>");
		out.println("<body>");
		if(session.getAttribute("user")==null){		
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}else {		
		
			out.println("<h1 >欢迎你，"+username+"</h1>&nbsp;&nbsp");
			out.println("<a href='loadout'>注销</a>");
		}
		if(username.equals("admin")) {
			out.println("<br><br><br><a href='welcome.jsp'>点击此处进入管理界面</a>");
		}
		out.println("</body>");
		out.println("</html>");
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
