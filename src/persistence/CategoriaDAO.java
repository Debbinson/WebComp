package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Bean;
import model.Categoria;
import util.DBConnectorFactory;

public class CategoriaDAO implements DAO {

	@Override
	public void Insert(Bean newBean) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void Delete(Bean beanToRemove) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void Update(Bean beanToUpdate) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Bean> GetAll() throws SQLException {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DBConnectorFactory.getInstance().makeDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			String selectCategorie = "SELECT idCategoria, nome FROM categoria;";
			System.out.println("SELECT categoria: " + selectCategorie);
			ResultSet result = stmt.executeQuery(selectCategorie);

			List<Bean> output = new LinkedList<>();
			while (result.next()) {
				Categoria c = new Categoria();
				c.setIdCategoria(result.getInt("idCategoria"));
				c.setNome(result.getString("nome"));
				output.add(c);
			}

			return output;
		} catch (SQLException sqle) {
			// aggiungere stampa
			throw new SQLException(sqle.getErrorCode() + " :: " + sqle.getMessage());
		} catch (Exception err) {
			throw new SQLException(err.getMessage());
		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				stmt.close();
		}
	}

	@Override
	public Bean GetOne(Bean beanToGet) throws SQLException {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DBConnectorFactory.getInstance().makeDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			Categoria categoriaToFind = (Categoria) beanToGet;
			String selectCategorie = "SELECT idCategoria, nome FROM categoria WHERE idCategoria = '"
					+ categoriaToFind.getIdCategoria() + "';";
			System.out.println("SELECT categoria: " + selectCategorie);
			ResultSet result = stmt.executeQuery(selectCategorie);

			if (!result.next())
				throw new SQLException("Categoria not found");

			Categoria output = new Categoria();
			output.setIdCategoria(result.getInt("idCategoria"));
			output.setNome(result.getString("nome"));
			return output;
		} catch (SQLException sqle) {
			// aggiungere stampa
			throw new SQLException(sqle.getErrorCode() + " :: " + sqle.getMessage());
		} catch (Exception err) {
			throw new SQLException(err.getMessage());
		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				stmt.close();
		}

	}

	public ArrayList<Bean> GetAllByCriteria(Bean criteria) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
