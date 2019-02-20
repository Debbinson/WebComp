package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Bean;
import model.IndirizzoFatturazione;
import model.Utente;
import util.DBConnectorFactory;

public class IndirizzoFatturazioneDAO implements DAO {

	@Override
	public void Insert(Bean newBean) throws SQLException {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DBConnectorFactory.getInstance().makeDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			IndirizzoFatturazione indirizzo = (IndirizzoFatturazione) newBean;
			String insertIndirizzo = "INSERT INTO INDIRIZZOFATTURAZIONE (INDIRIZZO, NOME, COGNOME, EMAIL, CITTA, CAP, TELEFONO, NAZIONE, INDIRIZZOFATTURAZIONE_UTENTE, DATE_INS)";
			insertIndirizzo += "VALUES('" + indirizzo.getIndirizzo() + "', '" + indirizzo.getNome() + "', '"
					+ indirizzo.getCognome() + "', '" + indirizzo.getEmail() + "', '" + indirizzo.getCitta() + "', '"
					+ indirizzo.getCap() + "', '" + indirizzo.getTelefono() + "', '" + indirizzo.getNazione() + "', '"
					+ indirizzo.getIndirizzoFatturazione_Utente() + "', SYSDATE())";
			System.out.println("INSERT INDIRIZZO: " + insertIndirizzo);
			int id = stmt.executeUpdate(insertIndirizzo, Statement.RETURN_GENERATED_KEYS);
			indirizzo.setIdIndirizzo(id);
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
		// TODO Auto-generated method stub

	}

	@Override
	public void Update(Bean beanToUpdate) throws SQLException {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DBConnectorFactory.getInstance().makeDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			IndirizzoFatturazione indirizzo = (IndirizzoFatturazione) beanToUpdate;
			String updateIndirizzo = "UPDATE INDIRIZZOFATTURAZIONE ";
			updateIndirizzo += "SET INDIRIZZO = '" + indirizzo.getIndirizzo() + "', NOME = '" + indirizzo.getNome()
					+ "', COGNOME = '" + indirizzo.getCognome() + "', EMAIL = '" + indirizzo.getEmail() + "', CITTA = '"
					+ indirizzo.getCitta() + "', CAP = '" + indirizzo.getCap() + "', TELEFONO = '"
					+ indirizzo.getTelefono() + "', NAZIONE = '" + indirizzo.getNazione() + "' ";
			updateIndirizzo += "WHERE IDINDIRIZZO = '" + indirizzo.getIdIndirizzo() + "';";
			System.out.println("UPDATE INDIRIZZO: " + updateIndirizzo);
			stmt.executeUpdate(updateIndirizzo);
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
	public List<Bean> GetAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bean GetOne(Bean beanToGet) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		IndirizzoFatturazione output = null;

		try {
			conn = DBConnectorFactory.getInstance().makeDBConnection();
			stmt = conn.createStatement();
			output = new IndirizzoFatturazione();
			String selectQuery;

			if (beanToGet instanceof IndirizzoFatturazione)
				selectQuery = "SELECT * FROM INDIRIZZOFATTURAZIONE WHERE IDINDIRIZZO = '"
						+ ((IndirizzoFatturazione) beanToGet).getIdIndirizzo() + "'";
			else if (beanToGet instanceof Utente)
				selectQuery = "SELECT * FROM INDIRIZZOFATTURAZIONE WHERE INDIRIZZOFATTURAZIONE_UTENTE = '"
						+ ((Utente) beanToGet).getIdUser() + "'";
			else
				throw new SQLException("PARAMS INPUT NOT ALLOWED");

			System.out.println("SELECT INDIRIZZO FATTURAZIONE: " + selectQuery);
			ResultSet resultI = stmt.executeQuery(selectQuery);
			if (!resultI.next()) {
				output.setNome("");
				output.setCognome("");
				output.setEmail("");
				output.setCitta("");
				output.setIndirizzo("");
				output.setNazione("");
				output.setCap("");
				output.setTelefono("");
				return output;
			}
			output.setIdIndirizzo(resultI.getInt("idindirizzo"));
			output.setNome(resultI.getString("nome"));
			output.setCognome(resultI.getString("cognome"));
			output.setEmail(resultI.getString("email"));
			output.setCitta(resultI.getString("citta"));
			output.setIndirizzo(resultI.getString("indirizzo"));
			output.setNazione(resultI.getString("nazione"));
			output.setCap(resultI.getString("cap"));
			output.setTelefono(resultI.getString("telefono"));
			output.setIndirizzoFatturazione_Utente(resultI.getInt("indirizzofatturazione_utente"));
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

	public ArrayList<Bean> GetAllByCriteria(Bean criteria) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
