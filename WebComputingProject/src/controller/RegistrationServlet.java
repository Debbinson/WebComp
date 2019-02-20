package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import persistence.UtenteDAO;
import util.AppUtils;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AppUtils.Forward(request, response, this, "/registration.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("POST RegistrationServlet");

		// Not required
		String nome = request.getParameter("nome").trim();
		String cognome = request.getParameter("cognome").trim();

		// Required
		String email = request.getParameter("email").trim();
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();

		Utente utente = new Utente();
		utente.setNome(nome);
		utente.setCognome(cognome);
		utente.setEmail(email);
		utente.setUsername(username);
		utente.setPassword(password);

		// Insert nel db
		try {
			UtenteDAO dao = new UtenteDAO();
			dao.Insert(utente);
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/login");
			rd.forward(request, response);
		} catch (SQLException e) {
			// Alert
			doGet(request, response);
			e.printStackTrace();
		}
	}
}