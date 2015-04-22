package net.bechtelus.common;


import java.sql.SQLException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.mysql.jdbc.Connection;

import net.bechtelus.standard.StandardDAO;
import net.bechtelus.standard.MySQLStandardDAO;

public class MySQLDAOFactory extends DAOFactory {
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String DBURL = "jdbc:mysql://localhost/tr";

	// method to create Mysql connections
	public static Connection createConnection() {

		Connection con = null;
		try {
			MysqlDataSource ds = new MysqlDataSource();
			ds.setServerName("localhost");
			ds.setPortNumber(3306);
			ds.setDatabaseName("tr");
			ds.setUser("Daniel");
			ds.setPassword("pentaho");
			
			try {
				con = (Connection) ds.getConnection();
			} catch (SQLException e) {
				
				throw new DAOException("No Connection! Is the SQL Server started?", e); 
			}
		}
		finally {
			
		}
		
		return con;

	}

	@Override
	public StandardDAO getStandardDAO() {
		// MysqlItemDAO implements StandardDAO
		return new MySQLStandardDAO();
	}

}
