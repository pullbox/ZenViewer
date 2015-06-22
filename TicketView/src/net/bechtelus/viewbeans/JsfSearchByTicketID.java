package net.bechtelus.viewbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import net.bechtelus.controlbeans.TicketSearchController;
import net.bechtelus.standard.APIAccessObject;

import org.zendesk.client.v2.model.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@RequestScoped
public class JsfSearchByTicketID extends Ticket implements Serializable {

	@ManagedProperty(value = "#{TicketSearchController}")
	private TicketSearchController ticketSearchController;

	private static final long serialVersionUID = 7778841766245989495L;
	private static Logger logger;
	private String ticketID;

	public void setTicketSearchController(TicketSearchController ctlticket) {
		this.ticketSearchController = ctlticket;
	}

	public String getTicketID() {
		logger.info("get: " + ticketID);
		return ticketID;
	}

	public void setTicketID(String ticketid) {
		this.ticketID = ticketid;
		logger.info("set: " + this.ticketID);
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
	
	public  String searchAction() {
		logger.info("searchbutton action");

//		logger.info("theTicketID: " + theTicketID);

	return ("DspTicket");
		
	}
	

	@PostConstruct
	public void init() {

		this.logger = LoggerFactory.getLogger(JsfSearchByTicketID.class);
		logger.info("Init");
		logger.info("CTL: " + ticketSearchController);
		

	}

}
