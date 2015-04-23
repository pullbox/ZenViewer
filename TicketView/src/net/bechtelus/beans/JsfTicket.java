package net.bechtelus.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import net.bechtelus.extended.model.CommentExtended;
import net.bechtelus.extended.model.TicketExtended;
import net.bechtelus.standard.APIAccessObject;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;

import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Status;
import org.zendesk.client.v2.model.Ticket;

@ManagedBean
public class JsfTicket extends Ticket implements Serializable {

	private static final long serialVersionUID = 7778841766245989494L;
	private static Zendesk zd;
	private int ticketID;
	private Ticket aticket;
	private TicketExtended zenticket;
	private List<CommentExtended> comments;

	public int getTicketID() {
		return ticketID;
	}

	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}

	@Override
	public String getDescription() {
		return zenticket.getDescription();
	}

	@Override
	public String getSubject() {
		return zenticket.getSubject();
	}

	@Override
	public Status getStatus() {
		return zenticket.getStatus();
	}

	public List<CommentExtended> getComments() {
		return comments;
	}

	public String getGroupName() {
		return zenticket.getGroupName();
	}

	@Override
	public Date getCreatedAt() {
		return zenticket.getCreatedAt();
	}

	public Date getUpdateAt() {
		return zenticket.getUpdatedAt();
	}

	public String getOrganization() {
		return zenticket.getOrganizationName();
	}

	public String getsubmitter() {
		return zenticket.getsubmitterName();
	}

	public String getRequesterName() {
		return zenticket.getRequesterName();
	}

	public String getAssigneeName() {
		return zenticket.getAssigneeName();
	}
	
	public String showTicket() {
		if (ticketID != 0) {
			aticket = null;
			zenticket = null;
			comments = null;
			aticket = zd.getTicket(ticketID);
			zenticket = new TicketExtended(aticket);
			comments = zenticket.getComments();
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

		zd = APIAccessObject.getAPIAccessObject();

	}

}
