package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Brano;
import model.IndirizzoFatturazione;
import model.Utente;
import persistence.BranoDAO;
import persistence.IndirizzoFatturazioneDAO;
import persistence.UtenteDAO;
import util.AppUtils;

public class UserServlet extends HttpServlet {
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
				request.getSession().setAttribute("INDIRIZZO_FATTURAZIONE", indirizzoFatturazione);
			} catch (SQLException e) {
				e.printStackTrace();
				AppUtils.Forward(request, response, this, "/error.jsp");
			}
		}

		AppUtils.Forward(request, response, this, "/user.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String indirizzoJSON = request.getParameter("indirizzo");
		String utenteJSON = request.getParameter("utente");
		String file_infoJSON = request.getParameter("file_to_upload_info");
		String whatsend = request.getParameter("whatsend");

		if (indirizzoJSON != null && whatsend.equalsIgnoreCase("UpdateIndirizzo")) {
			IndirizzoFatturazione new_indirizzo = AppUtils.formJsonToObject(indirizzoJSON, IndirizzoFatturazione.class);
			IndirizzoFatturazioneDAO dao = new IndirizzoFatturazioneDAO();
			try {

				if (((IndirizzoFatturazione) request.getSession().getAttribute("INDIRIZZO_FATTURAZIONE"))
						.getIdIndirizzo() == 0) {
					new_indirizzo.setIndirizzoFatturazione_Utente(
							((Utente) request.getSession().getAttribute("LOGINED_USER")).getIdUser());
					dao.Insert(new_indirizzo);
				} else if (!new_indirizzo.equals(request.getSession().getAttribute("INDIRIZZO_FATTURAZIONE"))) {
					new_indirizzo.setIdIndirizzo(
							((IndirizzoFatturazione) request.getSession().getAttribute("INDIRIZZO_FATTURAZIONE"))
									.getIdIndirizzo());
					dao.Update(new_indirizzo);
				}

				AppUtils.storeData(request.getSession(), "INDIRIZZO_FATTURAZIONE", new_indirizzo);
				response.getWriter().write("200");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (utenteJSON != null && whatsend.equalsIgnoreCase("UpdateUtente")) {
			try {
				Utente new_utente = AppUtils.formJsonToObject(utenteJSON, Utente.class);
				Utente utente = (Utente) request.getSession().getAttribute("LOGINED_USER");
				boolean update = false;
				if (new_utente.getUsername() != utente.getUsername()) {
					utente.setUsername(new_utente.getUsername());
					update = true;
				}
				if (new_utente.getNome() != utente.getNome()) {
					utente.setNome(new_utente.getNome());
					update = true;
				}
				if (new_utente.getCognome() != utente.getCognome()) {
					utente.setCognome(new_utente.getCognome());
					update = true;
				}
				if (new_utente.getEmail() != utente.getEmail()) {
					utente.setEmail(new_utente.getEmail());
					update = true;
				}
				if (update) {
					UtenteDAO dao = new UtenteDAO();
					dao.Update(utente);
					request.getSession().removeAttribute("LOGINED_USER");
					request.getSession().setAttribute("LOGINED_USER", utente);
					response.getWriter().write("200");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (whatsend.equalsIgnoreCase("InsertFileToUpload") && file_infoJSON != null) {
			try {
				Brano[] brani = AppUtils.formJsonToObject(file_infoJSON, Brano[].class);
				for (int i = 0; i < brani.length; i++) {
					brani[i].setBrano_utente(((Utente) request.getSession().getAttribute("LOGINED_USER")).getIdUser());
					brani[i].setDisponibile(true);
					if (brani[i].getPath() == null || brani[i].getPath().equalsIgnoreCase(""))
						throw new Exception("FILEPATH VUOTO!");
				}
				BranoDAO dao = new BranoDAO();
				for (int i = 0; i < brani.length; i++)
					dao.Insert(brani[i]);
				response.getWriter().write("200");

				AppUtils.InitUser(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			doGet(request, response);
		}
	}
}
