package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bean;
import model.Utente;
import persistence.UtenteDAO;
import util.AppUtils;

public class ArtistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			UtenteDAO dao = new UtenteDAO();
			List<Bean> result = dao.GetAll();
			List<Utente> casted_result = new LinkedList<Utente>();
			for (Bean b : result)
				casted_result.add((Utente) b);
			String utentiJSON = AppUtils.fromObjectToJson(casted_result);
			response.getWriter().write(utentiJSON);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
