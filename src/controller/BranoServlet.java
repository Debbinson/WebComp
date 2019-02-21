package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import model.Bean;
import model.Brano;
import model.Categoria;
import model.IndirizzoFatturazione;
import model.Utente;
import persistence.AcquistoDAO;
import persistence.BranoDAO;
import persistence.CategoriaDAO;
import persistence.IndirizzoFatturazioneDAO;
import util.AppUtils;

public class BranoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private File file;
	private final int MAX_SIZE_T = 0;
	private final int MAX_SIZE = 10000000;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String whatsend = request.getParameter("whatsend");

		if (whatsend.equalsIgnoreCase("GetBraniUtente")) {
			try {
				Utente utente = AppUtils.getLoginedUser(request.getSession());
				BranoDAO dao = new BranoDAO();
				ArrayList<Brano> result = dao.GetAllByCriteria(utente);

				AppUtils.storeData(request.getSession(), "BRANI_UTENTE", result);
				String brani_utenteJSON = AppUtils.fromObjectToJson(result);
				response.getWriter().append(brani_utenteJSON + '\n');
				response.getWriter().append("200");
			} catch (Exception e) {
				e.printStackTrace();
				AppUtils.Forward(request, response, this, "/error.jsp");
			}
		} else if (whatsend.equalsIgnoreCase("GetDettaglioBrano")) {
			try {
				Brano branoToFind = new Brano();
				branoToFind.setIdBrano(Integer.parseInt(request.getParameter("idBrano")));
				BranoDAO dao = new BranoDAO();
				Brano brano = dao.GetOneByCriteria(branoToFind);
				AppUtils.storeData(request.getSession(), "BRANO", brano);
				System.out.println("\n\n" + brano.getStelle() + "\n\n");

				Categoria categoriaToFind = new Categoria();
				categoriaToFind.setIdCategoria(brano.getBrano_categoria());
				CategoriaDAO categoriaDao = new CategoriaDAO();
				Categoria categoria = (Categoria) categoriaDao.GetOne(categoriaToFind);
				AppUtils.storeData(request.getSession(), "CATEGORIA_BRANO", categoria);
				AppUtils.Forward(request, response, this, "/song.jsp");
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}

		} else if (whatsend.equalsIgnoreCase("GetAcquisti")) {
			try {
				Utente utente = AppUtils.getLoginedUser(request.getSession());
				AcquistoDAO dao = new AcquistoDAO();
				ArrayList<Brano> output = dao.GetAllByCriteria(utente);
				String branoJSON = AppUtils.fromObjectToJson(output);
				response.getWriter().write(branoJSON);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else
			response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String whatsend = request.getParameter("whatsend");

		if (whatsend != null && whatsend.equalsIgnoreCase("ChangeDisponibility")) {
			try {
				ArrayList<Bean> brani_utente = (ArrayList<Bean>) request.getSession().getAttribute("BRANI_UTENTE");
				for (int i = 0; i < brani_utente.size(); i++)
					if (((Brano) brani_utente.get(i)).getIdBrano() == Integer
							.parseInt(request.getParameter("idBrano"))) {
						((Brano) brani_utente.get(i)).setDisponibile(!((Brano) brani_utente.get(i)).isDisponibile());
						BranoDAO dao = new BranoDAO();
						dao.Update(brani_utente.get(i));
						break;
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			try {
				IndirizzoFatturazioneDAO indirizzoDao = new IndirizzoFatturazioneDAO();
				IndirizzoFatturazione indirizzo = (IndirizzoFatturazione) indirizzoDao
						.GetOne(AppUtils.getLoginedUser(request.getSession()));

				if (indirizzo == null || indirizzo.getEmail().equalsIgnoreCase("")) {
					throw new Exception("INDIRIZZO FATTURAZIONE ADMIN VUOTO!");
				}

				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(MAX_SIZE_T);
				factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(MAX_SIZE);
				List<FileItem> fileItems = upload.parseRequest(new ServletRequestContext(request));
				Iterator<FileItem> i = fileItems.iterator();

				response.getWriter().write("");
				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					if (!fi.isFormField()) {
						String ext = fi.getName().substring(fi.getName().lastIndexOf("."));
						String filename = AppUtils.generateUniqueName(this);
						file = new File(AppUtils.getSongUploadPath(this) + filename + ext);
						response.getWriter().append(filename + ext + '\n');
						fi.write(file);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(500);
			}
		}
	}
}
