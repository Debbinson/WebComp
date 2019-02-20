package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import persistence.UtenteDAO;
import util.AppUtils;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AppUtils.Forward(request, response, this, "/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		Utente toFind = new Utente();
		toFind.setUsername(username);
		toFind.setPassword(password);

		UtenteDAO dao = new UtenteDAO();
		try {
			Utente utente = (Utente) dao.GetOne(toFind);
			if (utente == null) {
				String errorMessage = "Invalid username or password";
				AppUtils.storeData(request.getSession(), "ERROR_MESSAGE", errorMessage);
				AppUtils.Forward(request, response, this, "/login.jsp");
				return;
			}

			AppUtils.storeLoginedUser(request.getSession(), utente);
			int redirectId = -1;
			try {
				redirectId = Integer.parseInt(request.getParameter("redirectId"));
			} catch (Exception e) {
				System.out.println("PARSING ERROR");
			}

			System.out.println("INIT USER");
			AppUtils.InitUser(request);

			String requestUri = AppUtils.getRedirectAfterLoginUrl(request.getSession(), redirectId);
			if (requestUri != null)
				response.sendRedirect(requestUri);
			else if (!utente.getRoles().contains("ADMIN"))
				response.sendRedirect(request.getContextPath() + "/user");
			else
				response.sendRedirect(request.getContextPath() + "/admin");
		} catch (SQLException e) {
			e.printStackTrace();
			AppUtils.Forward(request, response, this, "/error.jsp");
		}
	}
}
