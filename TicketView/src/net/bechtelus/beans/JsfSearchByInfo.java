package net.bechtelus.beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;

@ManagedBean
public class JsfSearchByInfo implements Serializable {

	private static final long serialVersionUID = 7778841766245989495L;

	private String searchTerm;

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchterm) {
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

	}
}
