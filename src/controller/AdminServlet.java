package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.IndirizzoFatturazione;
import model.Utente;
import persistence.IndirizzoFatturazioneDAO;
import util.AppUtils;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recupero indirizzo di fatturazione associato
		Utente utente = (Utente) request.getSession().getAttribute("LOGINED_USER");
		if (request.getSession().getAttribute("INDIRIZZO_FATTURAZIONE") == null
				|| request.getParameter("refresh_indirizzo_fatturazione") != null) {
			request.getSession().removeAttribute("INDIRIZZO_FATTURAZIONE");
			IndirizzoFatturazioneDAO dao = new IndirizzoFatturazioneDAO();
			try {
				IndirizzoFatturazione indirizzoFatturazione = (IndirizzoFatturazione) dao.GetOne(utente);
				AppUtils.storeData(request.getSession(), "INDIRIZZO_FATTURAZIONE", indirizzoFatturazione);
			} catch (SQLException e) {
				e.printStackTrace();
				AppUtils.Forward(request, response, this, "/error.jsp");
			}
		}
		
		request.getSession().setAttribute("ADMIN", utente);


		AppUtils.Forward(request, response, this, "/admin.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
