package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Acquisto;
import model.Bean;
import model.Brano;
import model.Utente;
import util.DBConnectorFactory;

public class AcquistoDAO implements DAO {

	@Override
	public void Insert(Bean newBean) throws SQLException {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DBConnectorFactory.getInstance().makeDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			// Inserisco Checkout
			Acquisto acquisto = (Acquisto) newBean;
			String insertAcquisto = "INSERT INTO acquisto(Acquisto_Utente, Acquisto_Brano, date_ins)";
			insertAcquisto += "VALUES('" + acquisto.getAcquisto_Utente() + "', '" + acquisto.getAcquisto_Brano() + "', "
					+ "SYSDATE()); ";
			System.out.println("INSERT acquisto: " + insertAcquisto);
			stmt.executeUpdate(insertAcquisto);
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
			ArrayList<Brano> output = new ArrayList<>();
			conn = DBConnectorFactory.getInstance().makeDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			Utente utente = (Utente) criteria;
			String selectAcquisti = "SELECT * FROM acquisto ";
			selectAcquisti += "WHERE Acquisto_Utente = '" + utente.getIdUser() + "';";

			System.out.println("SELECT acquisto: " + selectAcquisti);
			ResultSet resultAcquisto = stmt.executeQuery(selectAcquisti);
			String selectBrano = "SELECT * FROM brano WHERE ";
			List<String> idbrani = new ArrayList<String>();
			while (resultAcquisto.next())
				idbrani.add(resultAcquisto.getString("Acquisto_Brano"));
			if (idbrani.isEmpty())
				return output;
			for (int i = 0; i < idbrani.size() - 1; i++)
				selectBrano += "idBrano = '" + idbrani.get(i) + "' OR ";
			selectBrano += "idBrano = '" + idbrani.get(idbrani.size() - 1) + "';";
			System.out.println("SELECT brano: " + selectBrano);

			ResultSet result = stmt.executeQuery(selectBrano);
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
