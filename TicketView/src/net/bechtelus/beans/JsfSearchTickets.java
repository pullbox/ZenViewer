package net.bechtelus.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.bechtelus.extended.model.TicketExtended;
import net.bechtelus.standard.APIAccessObject;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;

import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Ticket;

@ManagedBean
public class JsfSearchTickets implements Serializable {

	private static final long serialVersionUID = 7778841766245989495L;
	private static Zendesk zd;
	private String searchTerm;
	private List<TicketExtended> tickets;

	public String getSearchTerm() {
		return searchTerm;
	}

	public List<TicketExtended> getTickets() {
		return this.tickets;
	}

	public void setSearchTerm(String searchterm) {
		this.searchTerm = searchterm;
	}

	public String showSearchResults() {
		if (!searchTerm.isEmpty()) {

			tickets = null;
			tickets = new ArrayList<TicketExtended>();
			for (Ticket ticket : zd.getTicketsFromSearch(this.searchTerm)) {
				// System.out.println("Body: " + comment.getBody());
				TicketExtended ticketextended = null;
				ticketextended = new TicketExtended(ticket);
				tickets.add(ticketextended);
			}

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
		zd = APIAccessObject.getAPIAccessObject();
	}
}
