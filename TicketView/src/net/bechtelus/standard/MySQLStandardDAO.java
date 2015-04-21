package net.bechtelus.standard;

import static net.bechtelus.common.DAOUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.bechtelus.common.DAOException;
import net.bechtelus.common.DAOFactory;
import net.bechtelus.common.MySQLDAOFactory;
import net.bechtelus.beans.Standard;

public class MySQLStandardDAO implements StandardDAO {
	private static final String	SQL_FIND_BY_ID					= "SELECT id, type, name, description, weight, sort_order, always_applicable FROM items WHERE id = ?";
	private static final String	SQL_FIND_BY_EMAIL_AND_PASSWORD	= "SELECT id, email, firstname, lastname, birthdate FROM User WHERE email = ? AND password = MD5(?)";
	private static final String	SQL_LIST_ORDER_BY_ORDER			= "SELECT id, sort_order, name, description, type, weight, always_applicable FROM items ORDER BY sort_order";
	private static final String	SQL_INSERT						= "INSERT INTO items (type, name, description, weight, sort_order, always_applicable) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String	SQL_UPDATE						= "UPDATE items SET name=?, type=?, description=?, weight=?, sort_order=?, always_applicable=? WHERE id = ?";
	private static final String	SQL_DELETE						= "DELETE FROM items WHERE id = ?";
	private static final String	SQL_EXIST_EMAIL					= "SELECT id FROM User WHERE email = ?";
	private static final String	SQL_CHANGE_PASSWORD				= "UPDATE User SET password = MD5(?) WHERE id = ?";

	private DAOFactory			mysqlFactory;

	public MySQLStandardDAO() {
		mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);

	}

	@Override
	public List<Standard> listStandards() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Standard> standards = new ArrayList<Standard>();

		try {
			con = MySQLDAOFactory.createConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_LIST_ORDER_BY_ORDER);
			if (rs == null) {
				throw new DAOException("No Data");
			}

			try {
				while (rs.next()) {
					Standard std = new Standard();
					std.setId(rs.getLong("id"));
					std.setApplicable(rs.getBoolean("always_applicable"));
					std.setDescription(rs.getString("description"));
					std.setName(rs.getString("name"));
					std.setOrder(rs.getInt("sort_order"));
					std.setType(rs.getString("type"));
					std.setWeight(rs.getInt("weight"));

					standards.add(std);
				}
			} catch (Exception e) {
				throw new DAOException("ARGHh Something went wrong! MYSQLStandardDAO", e);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(con, stmt, rs);
		}
		return standards;
	}

	@Override
	public void insert(Standard standard) throws IllegalArgumentException, DAOException {
		if (standard.getId() != 0) {
			throw new IllegalArgumentException("Standard is already created, the Standard ID is not null.");
		}

		Object[] values = { standard.getType(), standard.getName(), standard.getDescription(), standard.getWeight(),
				standard.getOrder(), standard.isApplicable() };

		Connection con = null;
		PreparedStatement prpstmt = null;
		ResultSet generatedkeys = null;

		try {
			con = MySQLDAOFactory.createConnection();
			prpstmt = prepareStatement(con, SQL_INSERT, true, values);

			int effectedrows = prpstmt.executeUpdate();

			if (effectedrows == 0) {
				throw new DAOException("Creating user failed, no rows affected.");
			}

			generatedkeys = prpstmt.getGeneratedKeys();
			if (generatedkeys.next()) {
				standard.setId(generatedkeys.getLong(1));
			} else {
				throw new DAOException("Creating user failed, no generated key obtained.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(con, prpstmt, generatedkeys);
		}

	}

	@Override
	public void update(Standard standard) throws IllegalArgumentException, DAOException {
		if (standard.getId() == 0) {
			throw new IllegalArgumentException("No ID! Something went terribly wrong!");
		}

		Object[] values = {  standard.getName(), standard.getType(), standard.getDescription(), standard.getWeight(),
				standard.getOrder(), standard.isApplicable() };

		Connection con = null;
		PreparedStatement prpstmt = null;
		ResultSet generatedkeys = null;

		try {
			con = MySQLDAOFactory.createConnection();
			prpstmt = prepareStatement(con, SQL_UPDATE, true, values);

			int effectedrows = prpstmt.executeUpdate();

			if (effectedrows == 0) {
				throw new DAOException("Update failed as no rows were effected.");
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(con, prpstmt, generatedkeys);
		}
	}

	@Override
	public void delete(int id) {
		if (id == 0) {
			throw new IllegalArgumentException("Key is not set. ID is 0.");
		}

		Object[] values = { id };

		Connection con = null;
		PreparedStatement prpstmt = null;
		ResultSet generatedkeys = null;

		try {
			con = MySQLDAOFactory.createConnection();
			prpstmt = prepareStatement(con, SQL_DELETE, true, values);

			int effectedrows = prpstmt.executeUpdate();

			if (effectedrows == 0) {
				throw new DAOException("Deleting standard failed, no rows affected.");
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(con, prpstmt, generatedkeys);
		}

	}

	@Override
	public Standard findbyKey(int aid) {
		Connection con = null;
		PreparedStatement prpstmt = null;
		ResultSet rs = null;
		Standard std = null;
		try {
			con = MySQLDAOFactory.createConnection();
			prpstmt = prepareStatement(con, SQL_FIND_BY_ID, true, aid);

			rs = prpstmt.executeQuery();

			if (rs == null) {
				throw new DAOException("No Records fount!");
			}

			try {
				while (rs.next()) {
					std = new Standard();
					std.setId(rs.getLong("id"));
					std.setApplicable(rs.getBoolean("always_applicable"));
					std.setDescription(rs.getString("description"));
					std.setName(rs.getString("name"));
					std.setOrder(rs.getInt("sort_order"));
					std.setType(rs.getString("type"));
					std.setWeight(rs.getInt("weight"));

				}
			} catch (Exception e) {
				throw new DAOException("ARGHh Something went wrong! MYSQLStandardDAO", e);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(con, prpstmt, rs);
		}
		return std;

	}
}