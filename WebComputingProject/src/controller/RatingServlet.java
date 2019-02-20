package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Bean;
import model.Brano;
import model.Categoria;
import model.IndirizzoFatturazione;
import model.Utente;
import model.Valutazione;
import persistence.UtenteDAO;
import persistence.ValutazioneDAO;
import util.AppUtils;

public class RatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String valutazioneJSON = request.getParameter("valutazione");
		String whatsend = request.getParameter("whatsend");
		
		if (valutazioneJSON != null && whatsend.equalsIgnoreCase("valuta")) {
			try {
				Valutazione new_valutazione = AppUtils.formJsonToObject(valutazioneJSON, Valutazione.class);
				ValutazioneDAO dao = new ValutazioneDAO();
				Brano brano = (Brano) request.getSession().getAttribute("BRANO");
				Utente user = AppUtils.getLoginedUser(request.getSession());
				
				boolean alreadycomment=false;
				List<Bean> lista=dao.GetAll();
				for(int i = 0; i < lista.size(); i++) {
					if(((Valutazione)(lista.get(i))).getUtente_nomeUtente().equals(user.getUsername())&&((Valutazione)(lista.get(i))).getBrano_idBrano()==brano.getIdBrano()) {
						alreadycomment = true;
						new_valutazione.setUtente_nomeUtente(user.getUsername());
						new_valutazione.setBrano_idBrano(brano.getIdBrano());
						dao.Delete(new_valutazione);
						dao.Insert(new_valutazione);
						response.getWriter().write("200");
					}
				}
				
				if(alreadycomment == false) {
					new_valutazione.setUtente_nomeUtente(user.getUsername());
					new_valutazione.setBrano_idBrano(brano.getIdBrano());
					dao.Insert(new_valutazione);
					response.getWriter().write("200");
				}
				
			} catch (SQLException e) {
				response.sendError(500);
				e.printStackTrace();
			}
		}
	}
}