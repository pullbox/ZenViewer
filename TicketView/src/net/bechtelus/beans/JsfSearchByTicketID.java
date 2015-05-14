package net.bechtelus.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;

import org.zendesk.client.v2.model.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@ManagedBean(eager=true)
@RequestScoped
public class JsfSearchByTicketID extends Ticket implements Serializable {

	
	
	private static final long serialVersionUID = 7778841766245989495L;
	private static Logger logger;
	private String ticketID;

	public String getTicketID() {
		logger.info("get: " + ticketID);
		return ticketID;
	}

	public void setTicketID(String ticketid) {
		this.ticketID = ticketid;
		logger.info("set: " + this.ticketID);
	}

	/*public String showTicket() {
		if (!ticketID.isEmpty()) {
			Faces.setSessionAttribute("ticketID", Long.parseLong(ticketID));
		}
		return ("DspTicket"); // Means to go to page-b.xhtml (since condition
								// is not mapped in faces-config.xml)
	}*/

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
		
		this.logger = LoggerFactory.getLogger(JsfDspTicket.class);
		logger.info("Init");
	
	}

	
	
	

}
	

