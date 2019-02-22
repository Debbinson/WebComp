package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import model.Acquisto;
import model.Bean;
import model.Brano;
import model.Carrello;
import model.Utente;
import persistence.AcquistoDAO;
import persistence.CarrelloDAO;
import util.AppUtils;

public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (((Utente) request.getSession().getAttribute("LOGINED_USER")).getRoles().contains("ADMIN")) {
			AppUtils.Forward(request, response, this, "/admin");
			return;
		}

		String whatsend = request.getParameter("whatsend");
		if (whatsend != null && whatsend.equalsIgnoreCase("GetQtyCart")) {
			try {
				Utente utente = ((Utente) request.getSession().getAttribute("LOGINED_USER"));
				CarrelloDAO dao = new CarrelloDAO();
				ArrayList<Brano> result = dao.GetAllByCriteria((Bean) utente);
				response.getWriter().append(Integer.toString(result.size()) + "\n");
				response.getWriter().append("200");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (whatsend != null && whatsend.equalsIgnoreCase("VerifyDisponibility")) {
			try {
				Utente utente = (Utente) request.getSession().getAttribute("LOGINED_USER");
				CarrelloDAO carrelloDAO = new CarrelloDAO();
				ArrayList<Brano> brani;
				brani = carrelloDAO.GetAllByCriteria(utente);
				verifyDisponibilita(brani, utente, carrelloDAO);

				if (brani.isEmpty()) {
					response.sendError(500);
				}

				response.getWriter().write(AppUtils.fromObjectToJson(brani));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				Utente utente = ((Utente) request.getSession().getAttribute("LOGINED_USER"));
				CarrelloDAO dao = new CarrelloDAO();
				ArrayList<Brano> result = dao.GetAllByCriteria((Bean) utente);

				if (result.isEmpty()) {
					AppUtils.Forward(request, response, this, "/index.jsp");
					return;
				}

				AppUtils.storeData(request.getSession(), "CARRELLO_UTENTE", result);
				AppUtils.Forward(request, response, this, "/checkout.jsp");
			} catch (Exception e) {
				e.printStackTrace();
				AppUtils.Forward(request, response, this, "/error.jsp");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String whatsend = request.getParameter("whatsend");
		String idBrano = request.getParameter("idBrano");

		if (whatsend != null && idBrano != null && whatsend.equalsIgnoreCase("AddBrano")) {
			try {
				if (((Utente) request.getSession().getAttribute("LOGINED_USER")).getRoles().contains("ADMIN"))
					return;

				CarrelloDAO dao = new CarrelloDAO();
				Carrello carrello = new Carrello();
				carrello.setCarrello_brano(Integer.parseInt(idBrano));
				carrello.setCarrello_utente(((Utente) request.getSession().getAttribute("LOGINED_USER")).getIdUser());
				dao.Insert(carrello);
				response.getWriter().write("200");
			} catch (Exception e) {
				e.printStackTrace();
				AppUtils.Forward(request, response, this, "/error.jsp");
				return;
			}
		} else if (whatsend != null && idBrano != null && whatsend.equalsIgnoreCase("RemoveBrano")) {
			try {
				CarrelloDAO dao = new CarrelloDAO();
				Carrello carrello = new Carrello();
				carrello.setCarrello_brano(Integer.parseInt(idBrano));
				carrello.setCarrello_utente(((Utente) request.getSession().getAttribute("LOGINED_USER")).getIdUser());
				dao.Delete(carrello);
				response.getWriter().write("200");
			} catch (Exception e) {
				e.printStackTrace();
				AppUtils.Forward(request, response, this, "/error.jsp");
				return;
			}
		} else if (whatsend != null && whatsend.equalsIgnoreCase("StripePayment")) {
			try {
				Utente utente = (Utente) request.getSession().getAttribute("LOGINED_USER");
				CarrelloDAO carrelloDAO = new CarrelloDAO();
				ArrayList<Brano> brani = carrelloDAO.GetAllByCriteria(utente);

				Stripe.apiKey = "sk_test_ryWzYyIS1GMIhZNF8k8TJxKJ";
				String token = request.getParameter("stripeToken");

				int totale = (int) Float.parseFloat((String) request.getParameter("total"));
				String description = "Transazione Utente: " + utente.getCognome() + " " + utente.getNome();
				for (int i = 0; i < brani.size(); i++) {
					description += "\n" + brani.get(i).getTitolo() + " - " + brani.get(i).getPrezzo() + "€";
				}

				Map<String, Object> params = new HashMap<String, Object>();
				params.put("amount", totale);
				params.put("currency", "eur");
				params.put("description", description);
				params.put("source", token);
				Charge charge = Charge.create(params);

				if (charge == null) {
					AppUtils.Forward(request, response, this, "/paymentFailure.jsp");
					return;
				}

				AcquistoDAO acquistoDAO = new AcquistoDAO();
				for (int i = 0; i < brani.size(); i++) {
					Carrello carrello = new Carrello();
					carrello.setCarrello_brano(brani.get(i).getIdBrano());
					carrello.setCarrello_utente(utente.getIdUser());
					carrelloDAO.Delete(carrello);

					Acquisto acquisto = new Acquisto();
					acquisto.setAcquisto_Brano(brani.get(i).getIdBrano());
					acquisto.setAcquisto_Utente(utente.getIdUser());
					acquistoDAO.Insert(acquisto);
				}

				AppUtils.storeData(request.getSession(), "REDIRECT_LINK", charge.getReceiptUrl());
				AppUtils.InitUser(request);
				response.sendRedirect("index");
			} catch (StripeException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean verifyDisponibilita(ArrayList<Brano> brani, Utente utente, CarrelloDAO dao) throws SQLException {
		boolean output = true;
		Iterator<Brano> it = brani.iterator();
		while (it.hasNext()) {
			Brano b = it.next();
			if (!b.isDisponibile()) {
				output = false;
				it.remove();

				Carrello carrello = new Carrello();
				carrello.setCarrello_brano(b.getIdBrano());
				carrello.setCarrello_utente(utente.getIdUser());
				dao.Delete(carrello);
			}
		}

		return output;
	}
}
