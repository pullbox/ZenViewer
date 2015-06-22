package net.bechtelus.controlbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import net.bechtelus.extended.model.CommentExtended;
import net.bechtelus.extended.model.FieldDefinition;
import net.bechtelus.extended.model.TicketExtended;
import net.bechtelus.standard.APIAccessObject;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.CustomFieldValue;
import org.zendesk.client.v2.model.Field;
import org.zendesk.client.v2.model.Status;
import org.zendesk.client.v2.model.Ticket;

@ManagedBean(name = "ticketSearchController")
@RequestScoped
public class TicketSearchController implements Serializable {

	
	private static final long serialVersionUID = 7778841766245989494L;
	private static Zendesk zd;
	private String theTicketID;
	
	private static Logger logger;



	@PostConstruct
	public void init() {

		this.logger = LoggerFactory.getLogger(TicketSearchController.class);
		logger.info("Init");
		zd = APIAccessObject.getAPIAccessObject();
	}

	@PreDestroy
	public void close() {
		zd.close();
		logger.info("destroyed");
	}

	public  String searchAction() {
		logger.info("searchbutton action");

		theTicketID = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("theTicketID");

		logger.info("theTicketID: " + theTicketID);
		zd = APIAccessObject.getAPIAccessObject();

	return ("DspTicket");
		
	}

	public String gettheTicketID() {
		logger.info("get: " + theTicketID);
		return theTicketID;
	}



}
