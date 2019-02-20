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
import model.Utente;
import util.DBConnectorFactory;

public class UtenteDAO implements DAO {

	public UtenteDAO() {
	}

	@Override
	public void Insert(Bean newBean) throws SQLException {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DBConnectorFactory.getInstance().makeDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			// Inserisco L'Utente
			Utente utente = (Utente) newBean;
			String insertUtente = "INSERT INTO UTENTE(nome, cognome, email, username, password, date_ins)";
			insertUtente += "VALUES('" + utente.getNome() + "', '" + utente.getCognome() + "', '" + utente.getEmail()
					+ "', '" + utente.getUsername() + "', '" + utente.getPassword() + "', " + "SYSDATE()); ";
			System.out.println("INSERT UTENTE: " + insertUtente);
			stmt.executeUpdate(insertUtente);
			conn.commit();

			// Inserisco il ruolo
			String getLastUser = "SELECT IDUTENTE FROM UTENTE WHERE USERNAME = '" + ((Utente) newBean).getUsername()
					+ "';";
			ResultSet resultU = stmt.executeQuery(getLastUser);
			resultU.next();
			String idUser = resultU.getString("idutente");

			String insertRuolo = "INSERT INTO ROLE_UTENTE (IDROLE, IDUTENTE)";
			insertRuolo += "VALUES('" + 1 + "', '" + idUser + "');";
			System.out.println("INSERT ROLE_USER: " + insertRuolo);
			stmt.executeUpdate(insertRuolo);
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

			Utente utente = (Utente) beanToUpdate;
			String updateUtente = "UPDATE UTENTE ";
			updateUtente += "SET USERNAME = '" + utente.getUsername() + "', NOME = '" + utente.getNome()
					+ "', COGNOME = '" + utente.getCognome() + "', EMAIL = '" + utente.getEmail() + "'";
			updateUtente += "WHERE IDUTENTE = '" + utente.getIdUser() + "';";
			System.out.println("UPDATE UTENTE: " + updateUtente);
			stmt.executeUpdate(updateUtente);
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
		Connection conn = null;
		PreparedStatement stmt = null;
		List<Bean> utenti = new LinkedList<>();
		try {
			conn = DBConnectorFactory.getInstance().makeDBConnection();
			conn.setAutoCommit(false);
			Utente utente;
			String query = "SELECT DISTINCT USERNAME,idUtente FROM BRANO, UTENTE WHERE brano_utente=idUtente;";
			System.out.println("SELECT UTENTI: " + query);
			stmt = conn.prepareStatement(query);
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				utente = new Utente();
				utente.setUsername(result.getString("username"));
				utente.setIdUser(result.getInt("idUtente"));
				utenti.add(utente);
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
			if (stmt != null)
				stmt.close();
			if (conn != null)
				stmt.close();
		}
		return utenti;
	}

	@Override
	public Bean GetOne(Bean beanToGet) throws SQLException {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DBConnectorFactory.getInstance().makeDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			Utente utente = (Utente) beanToGet;
			String selectUtente = "SELECT * FROM UTENTE WHERE (USERNAME = '" + utente.getUsername()
					+ "' AND PASSWORD = '" + utente.getPassword() + "') OR (IDUTENTE = '" + utente.getIdUser() + "')";
			System.out.println("SELECT UTENTE: " + selectUtente);
			ResultSet result = stmt.executeQuery(selectUtente);

			if (!result.next())
				return null;

			Utente output = new Utente();
			output.setIdUser(result.getInt("idutente"));
			output.setNome(result.getString("nome"));
			output.setCognome(result.getString("cognome"));
			output.setEmail(result.getString("email"));
			output.setUsername(result.getString("username"));
			output.setPassword(result.getString("password"));

			String selectRoleId = "SELECT * FROM ROLE_UTENTE WHERE IDUTENTE = '" + result.getString("idutente") + "'";
			System.out.println("SELECT ROLE_UTENTE: " + selectRoleId);
			ResultSet resultRU = stmt.executeQuery(selectRoleId);
			List<String> idRoles = new ArrayList<String>();
			while (resultRU.next())
				idRoles.add(resultRU.getString("idrole"));

			String selectRoleName = "SELECT * FROM ROLE WHERE IDROLE = '";
			for (int i = 0; i < idRoles.size() - 1; i++)
				selectRoleName += idRoles.get(i) + "' OR '";
			selectRoleName += idRoles.get(idRoles.size() - 1) + "'";
			System.out.println("SELECT ROLE: " + selectRoleName);
			ResultSet resultR = stmt.executeQuery(selectRoleName);

			List<String> roles = new LinkedList<String>();
			while (resultR.next())
				roles.add(resultR.getString("role"));

			output.setRoles(roles);

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
