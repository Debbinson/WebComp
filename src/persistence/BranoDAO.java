package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Bean;
import model.Brano;
import model.Categoria;
import model.Utente;
import util.DBConnectorFactory;

public class BranoDAO implements DAO {

	@Override
	public void Insert(Bean newBean) throws SQLException {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DBConnectorFactory.getInstance().makeDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			Brano brano = (Brano) newBean;
			int disponibile = brano.isDisponibile() == true ? 1 : 0;
			String insertBrano = "INSERT INTO brano(titolo, descrizione, prezzo, filepath, Brano_Categoria, Brano_Utente, disponibile, date_ins)";
			insertBrano += "VALUES('" + brano.getTitolo() + "', '" + brano.getDescrizione() + "', '" + brano.getPrezzo()
					+ "', '" + brano.getPath() + "', '" + brano.getBrano_categoria() + "', '" + brano.getBrano_utente()
					+ "', '" + disponibile + "', " + "SYSDATE()); ";
			System.out.println("INSERT brano: " + insertBrano);
			stmt.executeUpdate(insertBrano);
			conn.commit();
		} catch (SQLException sqle) {
			if (conn != null)
				conn.rollback();
			System.out.println("GENERIC ERROR: Transaction is being rolled back");
			throw new SQLException(sqle.getErrorCode() + " :: " + sqle.getMessage());
		} catch (Exception err) {
			if (conn != null)
				conn.rollback();
			System.out.println("GENERIC ERROR: Transaction is being rolled back");
			throw new SQLException(err.getMessage());
		} finally {
			try {
				conn.close();
			}
			catch(SQLException sqle){
				sqle.printStackTrace();
			}
			if (stmt != null)
				stmt.close();
			if (conn != null)
				stmt.close();
		}
	}

	@Override
	public void Delete(Bean beanToRemove) throws SQLException {
		// TODO Auto-generated method stub
	}
	
	public void updateCategory(Bean beanToUpdate) throws SQLException {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DBConnectorFactory.getInstance().makeDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			Brano brano = (Brano) beanToUpdate;
			
			String updateBrano = "UPDATE brano ";
			updateBrano += "SET Brano_Categoria ='"+ "3" + "';";
			System.out.println("UPDATE brano: " + updateBrano);
			stmt.executeUpdate(updateBrano);
			conn.commit();
		} catch (Exception err) {
			if (conn != null)
				conn.rollback();
			System.out.println("GENERIC ERROR: Transaction is being rolled back");
			throw new SQLException(err.getMessage());
		}
		finally {
			try {
				conn.close();
			}
			catch(SQLException sqle){
				sqle.printStackTrace();
			}
			if (stmt != null)
				stmt.close();
			if (conn != null)
				stmt.close();
		}
	}

	@Override
	public void Update(Bean beanToUpdate) throws SQLException {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DBConnectorFactory.getInstance().makeDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			Brano brano = (Brano) beanToUpdate;
			int disponibile = brano.isDisponibile() == true ? 1 : 0;
			String updateBrano = "UPDATE brano ";
			updateBrano += "SET titolo = '" + brano.getTitolo() + "', descrizione = '" + brano.getDescrizione()
					+ "', prezzo = '" + brano.getPrezzo() + "', filepath = '" + brano.getPath()
					+ "', Brano_Categoria = '" + brano.getBrano_categoria() + "', Brano_Utente = '"
					+ brano.getBrano_utente() + "', disponibile = '" + disponibile + "'";
			updateBrano += "WHERE idBrano = '" + brano.getIdBrano() + "';";
			System.out.println("UPDATE brano: " + updateBrano);
			stmt.executeUpdate(updateBrano);
			conn.commit();
		} catch (SQLException sqle) {
			if (conn != null)
				conn.rollback();
			System.out.println("GENERIC ERROR: Transaction is being rolled back");
			throw new SQLException(sqle.getErrorCode() + " :: " + sqle.getMessage());
		} catch (Exception err) {
			if (conn != null)
				conn.rollback();
			System.out.println("GENERIC ERROR: Transaction is being rolled back");
			throw new SQLException(err.getMessage());
		} finally {
			try {
				conn.close();
			}
			catch(SQLException sqle){
				sqle.printStackTrace();
			}
			if (stmt != null)
				stmt.close();
			if (conn != null)
				stmt.close();
		}
	}

	@Override
	public List<Bean> GetAll() throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		List<Bean> brani = new LinkedList<>();
		try {
			conn = DBConnectorFactory.getInstance().makeDBConnection();
			conn.setAutoCommit(false);
			Brano brano;
			String query = "SELECT * FROM brano";
			stmt = conn.prepareStatement(query);
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				brano = new Brano();
				brano.setIdBrano(result.getInt("idBrano"));
				brano.setTitolo(result.getString("titolo"));
				brano.setDescrizione(result.getString("descrizione"));
				brano.setPrezzo(result.getInt("prezzo"));
				brano.setPath(result.getString("filepath"));
				brano.setBrano_categoria(result.getInt("Brano_Categoria"));
				brano.setBrano_utente(result.getInt("Brano_Utente"));
				brano.setDisponibile(result.getBoolean("disponibile"));
				brano.setDate_ins(result.getString("date_ins"));
				brani.add(brano);
			}
		} catch (SQLException sqle) {
			if (conn != null)
				conn.rollback();
			System.out.println("GENERIC ERROR: Transaction is being rolled back");
			throw new SQLException(sqle.getErrorCode() + " :: " + sqle.getMessage());
		} catch (Exception err) {
			if (conn != null)
				conn.rollback();
			System.out.println("GENERIC ERROR: Transaction is being rolled back");
			throw new SQLException(err.getMessage());
		} finally {
			try {
				conn.close();
			}
			catch(SQLException sqle){
				sqle.printStackTrace();
			}
			if (stmt != null)
				stmt.close();
			if (conn != null)
				stmt.close();
		}
		return brani;
	}

	public ArrayList<Brano> GetAllByCriteria(Bean criteria) throws SQLException {
		Connection conn = null;
		Statement stmt = null;

		try {
			ArrayList<Brano> output = new ArrayList<>();
			String selectBrani = "";
			conn = DBConnectorFactory.getInstance().makeDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			if (criteria instanceof Utente) {
				Utente utente = (Utente) criteria;
				selectBrani = "SELECT * FROM brano ";
				selectBrani += "WHERE Brano_Utente = '" + utente.getIdUser() + "';";
			} else if (criteria instanceof Brano) {
				Brano brano = (Brano) criteria;
				selectBrani = "SELECT * FROM brano ";
				selectBrani += "WHERE TITOLO LIKE '%" + brano.getTitolo() + "%'";
			} else if (criteria instanceof Categoria) {
				Categoria categoria = (Categoria) criteria;
				selectBrani = "SELECT * FROM brano ";
				selectBrani += "WHERE Brano_Categoria = '" + categoria.getIdCategoria() + "';";
			}

			System.out.println("SELECT brano: " + selectBrani);
			ResultSet result = stmt.executeQuery(selectBrani);
			while (result.next()) {
				Brano brano = new Brano();
				brano = new Brano();
				brano.setIdBrano(result.getInt("idBrano"));
				brano.setTitolo(result.getString("titolo"));
				brano.setDescrizione(result.getString("descrizione"));
				brano.setPrezzo(result.getInt("prezzo"));
				brano.setPath(result.getString("filepath"));
				brano.setBrano_categoria(result.getInt("Brano_Categoria"));
				brano.setBrano_utente(result.getInt("Brano_Utente"));
				brano.setDisponibile(result.getBoolean("disponibile"));
				brano.setDate_ins(result.getString("date_ins"));

				output.add(brano);
			}

			return output;
		} catch (SQLException sqle) {
			throw new SQLException(sqle.getErrorCode() + " :: " + sqle.getMessage());
		} catch (Exception err) {
			throw new SQLException(err.getMessage());
		} finally {
			try {
				conn.close();
			}
			catch(SQLException sqle){
				sqle.printStackTrace();
			}
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

			Brano canzone = (Brano) beanToGet;
			String selectCanzone = "SELECT * FROM brano WHERE (idBrano = '" + canzone.getIdBrano() + "' OR titolo = '"
					+ canzone.getTitolo() + "')";
			System.out.println("SELECT brano: " + selectCanzone);
			ResultSet result = stmt.executeQuery(selectCanzone);

			if (!result.next())
				return null;

			Brano output = new Brano();
			output.setIdBrano(result.getInt("idBrano"));
			output.setTitolo(result.getString("titolo"));
			output.setDescrizione(result.getString("descrizione"));
			output.setPrezzo(result.getInt("prezzo"));
			output.setPath(result.getString("filepath"));
			output.setBrano_categoria(result.getInt("Brano_Categoria"));
			output.setBrano_utente(result.getInt("Brano_Utente"));
			output.setDisponibile(result.getBoolean("disponibile"));
			output.setDate_ins(result.getString("date_ins"));
			return output;
		} catch (SQLException sqle) {
			// aggiungere stampa
			throw new SQLException(sqle.getErrorCode() + " :: " + sqle.getMessage());
		} catch (Exception err) {
			throw new SQLException(err.getMessage());
		} finally {
			try {
				conn.close();
			}
			catch(SQLException sqle){
				sqle.printStackTrace();
			}
			if (stmt != null)
				stmt.close();
			if (conn != null)
				stmt.close();
		}
	}

	public Brano GetOneByCriteria(Brano brano) throws SQLException {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DBConnectorFactory.getInstance().makeDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			String selectBrano = "SELECT * FROM brano WHERE idBrano = '" + brano.getIdBrano() + "'";
			System.out.println("SELECT BRANO: " + selectBrano);
			ResultSet result = stmt.executeQuery(selectBrano);

			if (!result.next()) {
				throw new SQLException("Brano not found");
			}

			Brano output = new Brano();
			output.setIdBrano(result.getInt("idBrano"));
			output.setTitolo(result.getString("titolo"));
			output.setDescrizione(result.getString("descrizione"));
			output.setPrezzo(result.getInt("prezzo"));
			output.setPath(result.getString("filepath"));
			output.setBrano_categoria(result.getInt("Brano_Categoria"));
			output.setBrano_utente(result.getInt("Brano_Utente"));
			output.setDisponibile(result.getBoolean("disponibile"));
			output.setDate_ins(result.getString("date_ins"));
			return output;
		} catch (SQLException sqle) {
			// aggiungere stampa
			throw new SQLException(sqle.getErrorCode() + " :: " + sqle.getMessage());
		} catch (Exception err) {
			throw new SQLException(err.getMessage());
		} finally {
			try {
				conn.close();
			}
			catch(SQLException sqle){
				sqle.printStackTrace();
			}
			if (stmt != null)
				stmt.close();
			if (conn != null)
				stmt.close();
		}
	}

	public ArrayList<Brano> GetAllByPrice(String priceMin, String priceMax) throws SQLException {
		Connection conn = null;
		Statement stmt = null;

		try {
			ArrayList<Brano> output = new ArrayList<>();
			String selectBrani = "";
			conn = DBConnectorFactory.getInstance().makeDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			selectBrani = "SELECT * FROM brano ";
			selectBrani += "WHERE prezzo >= '" + priceMin + "' AND prezzo <='" + priceMax + "';";

			System.out.println("SELECT brano: " + selectBrani);
			ResultSet result = stmt.executeQuery(selectBrani);
			while (result.next()) {
				Brano brano = new Brano();
				brano = new Brano();
				brano.setIdBrano(result.getInt("idBrano"));
				brano.setTitolo(result.getString("titolo"));
				brano.setDescrizione(result.getString("descrizione"));
				brano.setPrezzo(result.getInt("prezzo"));
				brano.setPath(result.getString("filepath"));
				brano.setBrano_categoria(result.getInt("Brano_Categoria"));
				brano.setBrano_utente(result.getInt("Brano_Utente"));
				brano.setDisponibile(result.getBoolean("disponibile"));
				brano.setDate_ins(result.getString("date_ins"));
				// SET STELLE CON JOIN DELLE VALUTAZIONI
				output.add(brano);
			}

			return output;
		} catch (SQLException sqle) {
			throw new SQLException(sqle.getErrorCode() + " :: " + sqle.getMessage());
		} catch (Exception err) {
			throw new SQLException(err.getMessage());
		} finally {
			try {
				conn.close();
			}
			catch(SQLException sqle){
				sqle.printStackTrace();
			}
			if (stmt != null)
				stmt.close();
			if (conn != null)
				stmt.close();
		}

	}
}
