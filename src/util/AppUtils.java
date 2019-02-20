package util;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Brano;
import model.Utente;
import persistence.AcquistoDAO;
import persistence.BranoDAO;

public class AppUtils {

	private static int RENDIRECT_ID = 0;
	private static final Map<Integer, String> id_uri_map = new HashMap<Integer, String>();
	private static final Map<String, Integer> uri_id_map = new HashMap<String, Integer>();
	private static Gson gson = null;

	public static void storeLoginedUser(HttpSession session, Utente loginedUtente) {
		session.setAttribute("LOGINED_USER", loginedUtente);
	}

	public static Utente getLoginedUser(HttpSession session) {
		Utente utente = (Utente) session.getAttribute("LOGINED_USER");
		return utente;
	}

	public static void storeData(HttpSession session, String attribute_name, Object value) {
		if (session.getAttribute(attribute_name) != null)
			session.removeAttribute(attribute_name);
		session.setAttribute(attribute_name, value);
	}

	public static int storeRendirectAfterLoginUrl(HttpSession session, String requestUri) {
		Integer id = uri_id_map.get(requestUri);
		if (id == null) {
			id = RENDIRECT_ID++;

			uri_id_map.put(requestUri, id);
			id_uri_map.put(id, requestUri);
			return id;
		}

		return id;
	}

	public static String getRedirectAfterLoginUrl(HttpSession session, int redirectId) {
		String url = id_uri_map.get(redirectId);
		if (url != null)
			return url;

		return null;
	}

	public static String getSongUploadPath(HttpServlet servlet) {
		return servlet.getServletContext().getRealPath("SONG") + "\\";
	}

	public static String generateUniqueName(HttpServlet servlet) {
		String output = "";
		Random rnd = new Random();
		do {
			output = "";
			for (int i = 0; i < 16; i++) {
				int intChar = 65 + rnd.nextInt(91 - 65);
				output += (char) intChar;
			}
		} while (checkIfExist(output, servlet));

		return output;
	}

	private static boolean checkIfExist(String file, HttpServlet servlet) {
		File folder = new File(AppUtils.getSongUploadPath(servlet));
		File[] listOfFiles = folder.listFiles();
		for (File f : listOfFiles) {
			if (f.isFile() && f.getName().equals(file))
				return true;
		}

		return false;
	}

	public static void InitUser(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			Utente utente = getLoginedUser(session);

			// Recupero brani caricati dall'utente
			BranoDAO branoDao = new BranoDAO();
			ArrayList<Brano> uploaded;
			uploaded = branoDao.GetAllByCriteria(utente);
			if (request.getSession().getAttribute("UPLOADED_SONG") != null)
				request.getSession().removeAttribute("UPLOADED_SONG");
			request.getSession().setAttribute("UPLOADED_SONG", uploaded);

			// Recupero brani acquistati dall'utente
			AcquistoDAO acquistoDao = new AcquistoDAO();
			ArrayList<Brano> purchased = acquistoDao.GetAllByCriteria(utente);
			if (request.getSession().getAttribute("PURCHASED_SONG") != null)
				request.getSession().removeAttribute("PURCHASED_SONG");
			request.getSession().setAttribute("PURCHASED_SONG", purchased);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void Forward(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet,
			String path) throws ServletException, IOException {
		RequestDispatcher rd = servlet.getServletContext().getRequestDispatcher(path);
		rd.forward(request, response);
	}

	public static String fromObjectToJson(Object object) {
		if(gson == null)
			gson = new Gson();
		
		return gson.toJson(object);
	}

	public static <T> T formJsonToObject(String json, Class<T> classType) {
		if(gson == null)
			gson = new Gson();
		
		return gson.fromJson(json, classType);
	}
}
