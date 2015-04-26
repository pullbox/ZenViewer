package net.bechtelus.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import net.bechtelus.extended.model.CommentExtended;
import net.bechtelus.extended.model.TicketExtended;
import net.bechtelus.standard.APIAccessObject;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;

import org.omnifaces.util.Faces;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Status;
import org.zendesk.client.v2.model.Ticket;

@ManagedBean
public class JsfSearchByTicketID extends Ticket implements Serializable {

	private static final long serialVersionUID = 7778841766245989495L;

	private String ticketID;

	public String getTicketID() {
		return ticketID;
	}

	public void setTicketID(String ticketid) {
		this.ticketID = ticketid;
	}

	public String showSearchResults() {
		if (!ticketID.isEmpty()) {
			Faces.setSessionAttribute("ticketID", ticketID);
		}
		return ("DspTicket"); // Means to go to page-b.xhtml (since condition
								// is not mapped in faces-config.xml)
	}

	public String someOtherActionControllerMethod() {
		return ("SearchByTicketID"); // Means to go to index.xhtml (since
									// condition is not
		// mapped in faces-config.xml)
	}

	public String searchbyticketid() {
		return ("SearchByTicketID"); // Means to go to index.xhtml (since
									// condition is not
		// mapped in faces-config.xml)
	}

	@PostConstruct
	public void init() {

	}

}