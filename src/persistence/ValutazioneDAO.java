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
import model.Valutazione;
import persistence.DAO;
import util.DBConnectorFactory;

public class ValutazioneDAO implements DAO{

	@Override
	public void Insert(Bean newBean) throws SQLException {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DBConnectorFactory.getInstance().makeDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			// Inserisco L'val
			Valutazione val = (Valutazione) newBean;
			
			String insertValutazione = "INSERT INTO valutazione(rating,commento,Brano_idBrano,Utente_nomeUtente, date_ins)";
			insertValutazione += "VALUES('"+val.getRating() + "', '" + val.getCommento() + "', '" + val.getBrano_idBrano()+"', '" + val.getUtente_nomeUtente()+"', SYSDATE());";
			
			System.out.println("INSERT valutazione: " + insertValutazione);
			
			stmt.executeUpdate(insertValutazione);
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
			String query="delete from valutazione where Utente_nomeUtente='";
			query+=((Valutazione)beanToRemove).getUtente_nomeUtente()+"';";
			stmt.executeUpdate(query);
			conn.commit();
		}
		catch (SQLException sqle) {
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
		Connection conn = null;
		PreparedStatement stmt = null;
		List<Bean> valutazioni = new LinkedList<>();
		try {
			conn = DBConnectorFactory.getInstance().makeDBConnection();
			conn.setAutoCommit(false);
			Valutazione val;
			String query="select * from valutazione";
			stmt=conn.prepareStatement(query);
			ResultSet result=stmt.executeQuery();
			while(result.next()){
				val=new Valutazione();
				val.setRating(result.getInt("rating"));
				val.setCommento(result.getString("commento"));
				val.setBrano_idBrano(result.getInt("Brano_idBrano"));
				val.setUtente_nomeUtente(result.getString("Utente_nomeUtente"));
				val.setDate_ins(result.getString("date_ins"));
				valutazioni.add(val);
			}
		}
		catch (SQLException sqle) {
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
		return valutazioni;
	}

	@Override
	public Bean GetOne(Bean beanToGet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Bean> GetAllByCriteria(Bean criteria) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

