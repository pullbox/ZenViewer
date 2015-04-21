package net.bechtelus.standard;

import net.bechtelus.common.DAOFactory;
import net.bechtelus.beans.Standard;

public class MaintainStandard {

	private DAOFactory mysqlFactory;
	private StandardDAO standardDAO;

	public MaintainStandard() {
	}

	public void writeStandard(Standard astandard) {
		mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		standardDAO = mysqlFactory.getStandardDAO();
		standardDAO.insert(astandard);
		mysqlFactory = null;
		standardDAO = null;
	}
	
	public void updateStandard(Standard astandard) {
		mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		standardDAO = mysqlFactory.getStandardDAO();
		standardDAO.update(astandard);
		mysqlFactory = null;
		standardDAO = null;
	}

	public void deleteStandard(int id) {
		mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		standardDAO = mysqlFactory.getStandardDAO();
		standardDAO.delete(id);
		mysqlFactory = null;
		standardDAO = null;
	}

	public Standard getStandard(int id) {
		mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		standardDAO = mysqlFactory.getStandardDAO();
		return standardDAO.findbyKey(id);

	}

}
