package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Bean;
import model.Brano;
import model.Carrello;
import model.Utente;
import util.DBConnectorFactory;

public class CarrelloDAO implements DAO {

	@Override
	public void Insert(Bean newBean) throws SQLException {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DBConnectorFactory.getInstance().makeDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			Carrello carrello = (Carrello) newBean;

			String selectAcquisto = "SELECT * FROM ACQUISTO ";
			selectAcquisto += "WHERE ACQUISTO_BRANO = '" + carrello.getCarrello_brano() + "' AND ACQUISTO_UTENTE = '"
					+ carrello.getCarrello_utente() + "';";
			System.out.println("SELECT ACQUISTO: " + selectAcquisto);
			ResultSet resultAcquisto = stmt.executeQuery(selectAcquisto);
			if (resultAcquisto.next()) {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					stmt.close();
				System.out.println("BRANO ALREADY IN TABLE ACQUISTO");
				throw new SQLException("BRANO ALREADY IN TABLE ACQUISTO");
			}

			String selectBrano = "SELECT * FROM BRANO ";
			selectBrano += "WHERE IDBRANO = '" + carrello.getCarrello_brano() + "';";
			System.out.println("CHECK DISPONIBILITY BRANO: " + selectBrano);
			ResultSet resultBrano = stmt.executeQuery(selectBrano);
			if (resultBrano.next() && resultBrano.getBoolean("disponibile") == false) {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					stmt.close();
				System.out.println("BRANO NOT AVAILABLE");
				throw new SQLException("BRANO NOT AVAILABLE");
			}

			String insertCarrello = "INSERT INTO CARRELLO(carrello_utente, carrello_brano, data_ins)";
			insertCarrello += "VALUES('" + carrello.getCarrello_utente() + "', '" + carrello.getCarrello_brano() + "', "
					+ "SYSDATE());";
			System.out.println("INSERT CARRELLO: " + insertCarrello);
			stmt.executeUpdate(insertCarrello);
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
			if (stmt != null)
				stmt.close();
			if (conn != null)
				stmt.close();
		}
	}

	@Override
	public void Delete(Bean beanToRemove) throws SQLException {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DBConnectorFactory.getInstance().makeDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			Carrello carrello = (Carrello) beanToRemove;
			String deleteCarrello = "DELETE FROM CARRELLO ";
			deleteCarrello += "WHERE CARRELLO_UTENTE = '" + carrello.getCarrello_utente() + "' AND CARRELLO_BRANO = '"
					+ carrello.getCarrello_brano() + "';";
			System.out.println("DELETE CARRELLO: " + deleteCarrello);
			stmt.executeUpdate(deleteCarrello);
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
			if (stmt != null)
				stmt.close();
			if (conn != null)
				stmt.close();
		}
	}

	@Override
	public void Update(Bean beanToUpdate) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Bean> GetAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bean GetOne(Bean beanToGet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Brano> GetAllByCriteria(Bean criteria) throws SQLException {
		Connection conn = null;
		Statement stmt = null;

		try {
			String selectCarrello = "";
			conn = DBConnectorFactory.getInstance().makeDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			Utente utente = (Utente) criteria;
			selectCarrello = "SELECT * FROM CARRELLO ";
			selectCarrello += "WHERE CARRELLO_UTENTE = '" + utente.getIdUser() + "';";
			System.out.println("SELECT CARRELLO_BRANO: " + selectCarrello);
			ResultSet resultIDBrani = stmt.executeQuery(selectCarrello);

			if (!resultIDBrani.next())
				return new ArrayList<Brano>();

			ArrayList<String> ids = new ArrayList<String>();
			do {
				ids.add(resultIDBrani.getString("carrello_brano"));
			} while ((resultIDBrani.next()));

			String selectBrani = "SELECT * FROM BRANO WHERE ";
			for (int i = 0; i < ids.size() - 1; i++)
				selectBrani += "IDBRANO = '" + ids.get(i) + "' OR ";
			selectBrani += "IDBRANO = '" + ids.get(ids.size() - 1) + "';";
			System.out.println("SELECT BRANI: " + selectBrani);
			ResultSet result = stmt.executeQuery(selectBrani);
			ArrayList<Brano> output = new ArrayList<>();
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
			if (stmt != null)
				stmt.close();
			if (conn != null)
				stmt.close();
		}
	}
}
