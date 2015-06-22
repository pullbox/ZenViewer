package net.bechtelus.viewbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(eager = true)
@RequestScoped
public class JsfSearchByInfo implements Serializable {

	private static final long serialVersionUID = 7778841766245989495L;
	private static Logger logger;
	private String searchTerm;

	public String getSearchTerm() {
		logger.info("get: " + searchTerm);
		return searchTerm;
	}

	public void setSearchTerm(String searchterm) {
		logger.info("set: " + searchterm);
		this.searchTerm = searchterm;
	}

	public String someOtherActionControllerMethod() {
		return ("SearchByInfo"); // Means to go to index.xhtml (since
									// condition is not
		// mapped in faces-config.xml)
	}

	public String searchbyinfo() {
		return ("SearchByInfo"); // Means to go to index.xhtml (since
									// condition is not
		// mapped in faces-config.xml)
	}

	@PostConstruct
	public void init() {
		this.logger = LoggerFactory.getLogger(JsfSearchByInfo.class);
		logger.info("Init: ");
	}
}
