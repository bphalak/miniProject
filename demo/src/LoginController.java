package controller;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDao;
import model.User;
import util.MyConnection;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	HttpSession session;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		connection = MyConnection.getConnection();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getServletContext().setAttribute("connection", connection);
		String userName = request.getParameter("userId");
		String password = request.getParameter("password");
		User user = new User();
		user.setUser(userName);
		user.setPassword(password);
		boolean isValid = new LoginDao(user, connection).authenticate();

		if (isValid) {
			session = request.getSession();
			session.setAttribute("username", userName);
			if("user".equals(user.getType())){
					request.getRequestDispatcher("./UserDashboard.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("./AdminDashboard.jsp").forward(request, response);
			}
		} else {
			response.sendRedirect("index.html");
		}
	}

}
