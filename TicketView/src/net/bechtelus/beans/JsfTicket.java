package net.bechtelus.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
public class JsfTicket extends Ticket implements Serializable {

	private static final long serialVersionUID = 7778841766245989494L;

	private int ticketID;
	private Ticket zenticket;
	private List<Comment> comments;
	private SearchTickets searchtickets;

	public int getTicketID() {
		return ticketID;
	}

	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}

	public Ticket getZenticket() {
		return zenticket;
	}

	public void setZenticket(Ticket zenticket) {
		this.zenticket = zenticket;
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

	public List<Comment> getComments() {
		return comments;
	}

	public String getGroupName() {
		long id = zenticket.getGroupId();
		Group group = searchtickets.getGroup(id);
		return group.getName();
	}

	@Override
	public Date getCreatedAt() {
		return zenticket.getCreatedAt();
	}

	public Date getUpdateAt() {
		return zenticket.getUpdatedAt();
	}

	public String getOrganization() {
		long id = zenticket.getOrganizationId();
		return searchtickets.getOrganization(id).getName();

	}

	public String getsubmitter() {
		long id = zenticket.getSubmitterId();
		return searchtickets.getUser(id).getName();
	}

	public String getRequesterName() {
		long id = zenticket.getRequesterId();
		User user = searchtickets.getUser(id);
		return user.getName();
	}

	public String getAssigneeName() {
		long id = zenticket.getAssigneeId();
		User user = searchtickets.getUser(id);
		return user.getName();
	}

	public String showTicket() {
		if (ticketID != 0) {
			zenticket = searchtickets.getTicket(ticketID);
			comments = searchtickets.getComments(ticketID);
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
		if (searchtickets != null) {
		} else {
			searchtickets = new SearchTickets();
		}
	}

}
