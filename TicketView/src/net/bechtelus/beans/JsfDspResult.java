package net.bechtelus.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.bechtelus.extended.model.CommentExtended;
import net.bechtelus.extended.model.TicketExtended;
import net.bechtelus.standard.APIAccessObject;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;

import org.omnifaces.util.Faces;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Ticket;

@ManagedBean
public class JsfDspResult implements Serializable {

	private static final long serialVersionUID = 7778841766245989495L;
	private static Zendesk zd;
	private String searchTerm;
	private TicketExtended zenticket;
	private List<TicketExtended> tickets;

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchterm) {
		this.searchTerm = searchterm;
	}

	public List<TicketExtended> gettickets() {
		return tickets;
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
		searchTerm = null;
		searchTerm = Faces.getSessionAttribute("searchTerm");
		zd = APIAccessObject.getAPIAccessObject();

		tickets = null;
		tickets = new ArrayList<TicketExtended>();
		for (Ticket ticket : zd.getTicketsFromSearch(this.searchTerm)) {
			// System.out.println("Body: " + comment.getBody());
			TicketExtended ticketextended = null;
			ticketextended = new TicketExtended(ticket);
			tickets.add(ticketextended);
		
		}
	}
}
