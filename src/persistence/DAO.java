package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Bean;

public interface DAO {
	public void Insert(Bean newBean) throws SQLException;

	public void Delete(Bean beanToRemove) throws SQLException;

	public void Update(Bean beanToUpdate) throws SQLException;

	public List<Bean> GetAll() throws SQLException;

	public Bean GetOne(Bean beanToGet) throws SQLException;

	// Ad esempio nel caso di catalogo, recupererà brani in base alla cetegoria (o
	// filtri)
	// public void GetByParams();
}
