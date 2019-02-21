package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Bean;
import model.Brano;
import model.Valutazione;
import persistence.ValutazioneDAO;
import util.AppUtils;

public class CommentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String whatsend = request.getParameter("whatsend");
		if (whatsend != null && whatsend.equalsIgnoreCase("commento")) {
			try {
				
				ValutazioneDAO valutazione = new ValutazioneDAO();
				Brano brano = (Brano) request.getSession().getAttribute("BRANO");
				List<Bean> valutazioni =valutazione.GetAll();
				List<Valutazione> valutazioniBrano = new LinkedList<>();
				for(int i=0;i<valutazioni.size();i++) {
					if(((Valutazione)(valutazioni.get(i))).getBrano_idBrano()==brano.getIdBrano())
						valutazioniBrano.add((Valutazione)(valutazioni.get(i)));
					
				}
				//request.getSession().setAttribute("VALUTAZIONI",valutazioniBrano);
				Gson gson = new Gson();
				AppUtils.storeData(request.getSession(), "VALUTAZIONI", valutazioniBrano);
				response.getWriter().write(gson.toJson(valutazioniBrano) + "\n");
				//response.getWriter().write("200");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				RequestDispatcher  dispatcher=this.getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
