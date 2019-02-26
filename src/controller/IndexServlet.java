package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dropbox.core.DbxException;

import model.Bean;
import model.Brano;
import model.Categoria;
import model.Utente;
import model.Valutazione;
import persistence.BranoDAO;
import persistence.ValutazioneDAO;
import util.AppUtils;
import util.UploadFile;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getSession().getAttribute("DROPBOX")!="DOWNLOAD_EFFETTUATO") {
			request.getSession().setAttribute("DROPBOX", "DOWNLOAD_EFFETTUATO");
			UploadFile up= new UploadFile();
			try {
				up.riempiCartella();
			} catch (DbxException e) {
				e.printStackTrace();
			}
		}
		String whatsend = request.getParameter("whatsend");
		if (whatsend != null && whatsend.equalsIgnoreCase("GetBraniByCategoria")) {
			try {
				BranoDAO dao = new BranoDAO();
				ArrayList<Brano> brani = null;
				int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
				Categoria categoria = new Categoria();
				categoria.setIdCategoria(idCategoria);
				brani = dao.GetAllByCriteria(categoria);
				addRating(brani);
				response.getWriter().write(AppUtils.fromObjectToJson(brani) + "\n");
			} catch (Exception e) {
				e.printStackTrace();
				AppUtils.Forward(request, response, this, "/error.jsp");
				return;
			}

		} else if (whatsend != null && whatsend.equalsIgnoreCase("GetBraniByQuery")) {
			try {
				BranoDAO dao = new BranoDAO();
				ArrayList<Brano> brani = null;
				String titolo = request.getParameter("query");
				Brano brano = new Brano();
				brano.setTitolo(titolo);
				brani = dao.GetAllByCriteria(brano);
				addRating(brani);
				response.getWriter().write(AppUtils.fromObjectToJson(brani) + "\n");
			} catch (Exception e) {
				e.printStackTrace();
				AppUtils.Forward(request, response, this, "/error.jsp");
			}

		} else if (whatsend != null && whatsend.equalsIgnoreCase("GetBraniByPrice")) {
			try {
				BranoDAO dao = new BranoDAO();
				ArrayList<Brano> brani = null;
				String priceMin = request.getParameter("min");
				String priceMax = request.getParameter("max");
				System.out.println(priceMin + " " + priceMax);

				brani = dao.GetAllByPrice(priceMin, priceMax);
				addRating(brani);
				response.getWriter().write(AppUtils.fromObjectToJson(brani) + "\n");
			} catch (Exception e) {
				e.printStackTrace();
				AppUtils.Forward(request, response, this, "/error.jsp");
			}

		} else if (whatsend != null && whatsend.equalsIgnoreCase("GetBraniByArtist")) {
			try {
				BranoDAO dao = new BranoDAO();
				ArrayList<Brano> brani = null;
				int idUtente = Integer.parseInt(request.getParameter("idUtente"));
				Utente utente = new Utente();
				utente.setIdUser(idUtente);
				brani = dao.GetAllByCriteria(utente);
				addRating(brani);
				response.getWriter().write(AppUtils.fromObjectToJson(brani) + "\n");
			} catch (Exception e) {
				e.printStackTrace();
				AppUtils.Forward(request, response, this, "/error.jsp");
			}

		} else {
			try {
				BranoDAO dao = new BranoDAO();
				ArrayList<Brano> brani = new ArrayList<>();
				
				for(Bean b : dao.GetAll()) {
					brani.add((Brano) b);
				}
				addRating(brani);

				AppUtils.storeData(request.getSession(), "CATALOGO", brani);
				AppUtils.Forward(request, response, this, "/index.jsp");

			} catch (SQLException e) {
				e.printStackTrace();
				AppUtils.Forward(request, response, this, "/error.jsp");
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void addRating(ArrayList<Brano> brani) throws SQLException {
		
		ValutazioneDAO valutazione = new ValutazioneDAO();
		List<Bean> valutazioni = valutazione.GetAll();
		
		for (int i = 0; i < brani.size(); i++) {
			int numeroValutazioni = 0;
			int votoComplessivo = 0;
			Brano b = (Brano) brani.get(i);
			for (int j = 0; j < valutazioni.size(); j++) {
				Valutazione v = (Valutazione) valutazioni.get(j);
				if (b.getIdBrano() == v.getBrano_idBrano()) {
					numeroValutazioni++;
					votoComplessivo += v.getRating();
				}
			}

			if (numeroValutazioni != 0) {
				int stella = votoComplessivo / numeroValutazioni;
				b.setStelle(stella);
			}
		}

		Collections.sort(brani, new Comparator<Bean>() {
			@Override
			public int compare(Bean o1, Bean o2) {
				Brano b1 = (Brano) o1;
				Brano b2 = (Brano) o2;
				return b2.getStelle() - b1.getStelle();
			}
		});
	}
}
