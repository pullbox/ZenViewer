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
public class JsfDspTicket  implements Serializable {

	private static final long serialVersionUID = 7778841766245989494L;
	private static Zendesk zd;
	private Long ticketID;
	private Ticket aticket, selectedTicket;
	private TicketExtended zenticket;
	private List<CommentExtended> comments;

	

	
public Long getTicketID() {
		return  zenticket.getId();
}


	public String getDescription() {
		return zenticket.getDescription();
	}

	
	public String getSubject() {
		return zenticket.getSubject();
	}

	
	public Status getStatus() {
		return zenticket.getStatus();
	}



	public String getGroupName() {
		return zenticket.getGroupName();
	}

	
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

	
	public List<CommentExtended> getcomments() {
		return comments;
	}
	
	public String getRequesterName() {
		return zenticket.getRequesterName();
	}

	public String getAssigneeName() {
		return zenticket.getAssigneeName();
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
		ticketID = null;
		ticketID = Faces.getSessionAttribute("ticketID");
		aticket = null;
		zenticket = null;
		comments = null;
		aticket = zd.getTicket(ticketID);
		zenticket = new TicketExtended(aticket);
		comments = zenticket.getComments();

	}

}
