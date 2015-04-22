package net.bechtelus.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import net.bechtelus.extended.model.CommentExtended;
import net.bechtelus.extended.model.TicketExtended;
import net.bechtelus.standard.SearchTickets;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;

import org.zendesk.client.v2.model.Comment;
import org.zendesk.client.v2.model.Group;
import org.zendesk.client.v2.model.Status;
import org.zendesk.client.v2.model.Ticket;
import org.zendesk.client.v2.model.User;

@ManagedBean
@SessionScoped
public class JsfSearchTickets  implements Serializable {

	private static final long serialVersionUID = 7778841766245989494L;

	private String searchterm;
	private SearchTickets searchtickets;
	private List<TicketExtended> tickets;

	
	public String getSearchterm() {
		return searchterm;
	}

	public void setSearchterm(String searchterm) {
		this.searchterm = searchterm;
	}

	public String showSearchResults() {
		if (!searchterm.isEmpty()) {
			tickets = searchtickets.getTickets(searchterm);
		
		}
		return ("DspResult"); // Means to go to page-b.xhtml (since condition
								// is not mapped in faces-config.xml)
	}

	public String someOtherActionControllerMethod() {
		return ("SearchByInfo"); // Means to go to index.xhtml (since
										// condition is not
		// mapped in faces-config.xml)
	}

	public String searchbyticketid() {
		return ("SearchByInfo"); // Means to go to index.xhtml (since
										// condition is not
		// mapped in faces-config.xml)
	}

	@PostConstruct
	public void init() {
		if (searchtickets != null) {
		} else {
			searchtickets = new SearchTickets();
		}
	}

}
