package net.bechtelus.beans;

import java.io.Serializable;
import java.util.List;

import net.bechtelus.common.*;
import net.bechtelus.standard.StandardDAO;

import javax.faces.bean.*;

@ManagedBean
public class MaintainStandards implements Serializable {

	private static final long serialVersionUID = -7647844681278204157L;
	private DAOFactory mysqlFactory;
	private StandardDAO standardDAO;

	private List<Standard> standards;

	public List<Standard> getStandards() {
		mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		standardDAO = mysqlFactory.getStandardDAO();
		this.standards = standardDAO.listStandards();
		mysqlFactory = null;
		standardDAO = null;
		return standards;
	}

	public void setStandards(List<Standard> standards) {
		this.standards = standards;
	}

	public String showValues() {

		return ("DspStandard"); // Means to go to page-b.xhtml (since condition
								// is not mapped in faces-config.xml)
	}

	public String someOtherActionControllerMethod() {
		return ("index"); // Means to go to index.xhtml (since condition is not
							// mapped in faces-config.xml)
	}

}
