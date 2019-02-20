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
import model.Categoria;
import persistence.CategoriaDAO;
import util.AppUtils;

public class CategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			CategoriaDAO dao = new CategoriaDAO();
			List<Bean> result = dao.GetAll();
			List<Categoria> casted_result = new LinkedList<Categoria>();
			for (Bean b : result)
				casted_result.add((Categoria) b);
			String categorieJSON = AppUtils.fromObjectToJson(casted_result);
			response.getWriter().write(categorieJSON);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
