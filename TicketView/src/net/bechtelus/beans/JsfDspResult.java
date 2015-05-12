package net.bechtelus.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.bechtelus.extended.model.TicketExtended;
import net.bechtelus.standard.APIAccessObject;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.omnifaces.util.Faces;
import org.primefaces.event.SelectEvent;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





@ManagedBean(eager = true)
@RequestScoped
public class JsfDspResult implements Serializable {

	private static final long serialVersionUID = 7778841766245989495L;
	private static Zendesk zd;
	private String theTicketID;
	private static Logger logger;
	private String searchTerm;
	private TicketExtended zenticket, selectedTicket;
	private List<TicketExtended> tickets;

	public String getSearchTerm() {
		log("get searchterm:" + searchTerm);
		return searchTerm;
	}

	public TicketExtended getSelectedTicket() {
		log("get selectticket");
		return selectedTicket;
	}

	public void setSelectedTicket(TicketExtended selectedTicket) {
		this.selectedTicket = selectedTicket;
		log("setSelectedTicket " + selectedTicket);
	}

	public void setSearchTerm(String searchterm) {
		log("set searchterm:" + searchTerm);
		this.searchTerm = searchterm;
	}

	public List<TicketExtended> gettickets() {
		return tickets;
	}

	/*
	 * public void gotoTicket(ActionEvent actionEvent) {
	 * Faces.setSessionAttribute("ticketID", selectedTicket.getId());
	 * 
	 * }
	 */

	public String getTheTicketID() {
		return theTicketID;
	}

	public void setTheTicketID(String theTicketID) {
		this.theTicketID = theTicketID;
	}

	public String someOtherActionControllerMethod() {
		return ("SearchByInfo"); // Means to go to index.xhtml (since
									// condition is not
		// mapped in faces-config.xml)
	}

	/*
	 * public void onDblClick(SelectEvent event) {
	 * 
	 * 
	 * FacesMessage msg = new FacesMessage("Ticket Selected", ((TicketExtended)
	 * event.getObject()).getId().toString());
	 * FacesContext.getCurrentInstance().addMessage(null, msg); log("msg ");
	 * 
	 * 
	 * @SuppressWarnings("unused") String test = "test"; Long id =
	 * ((TicketExtended) event.getObject()).getId();
	 * 
	 * log("onDblselect: " + id);
	 * 
	 * theTicketID = id.toString();
	 * 
	 * ConfigurableNavigationHandler configurableNavigationHandler =
	 * (ConfigurableNavigationHandler) FacesContext
	 * .getCurrentInstance().getApplication().getNavigationHandler();
	 * 
	 * configurableNavigationHandler
	 * .performNavigation("DspTicket?faces-redirect=true&includeViewParams=true"
	 * );
	 * 
	 * }
	 */

	public String searchbyinfo() {
		return ("SearchByInfo"); // Means to go to index.xhtml (since
									// condition is not
		// mapped in faces-config.xml)
	}

	@PostConstruct
	public void init() {
		
		this.logger = LoggerFactory.getLogger(JsfDspResult.class);
	
	
		logger.debug("Init: " + time());
		searchTerm = null;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		this.searchTerm = facesContext.getExternalContext()
				.getRequestParameterMap().get("searchTerm");
		
		logger.debug("seachTerm: " + this.searchTerm);
		logger.debug("before zd: " + time());
		zd = APIAccessObject.getAPIAccessObject();
		logger.debug("after zd: " + time());
		tickets = null;
		tickets = new ArrayList<TicketExtended>();
		logger.debug("before for: " + time());
		for (Ticket ticket : zd.getTicketsFromSearch(this.searchTerm)) {
			// System.out.println("Body: " + comment.getBody());
			TicketExtended ticketextended = null;
			ticketextended = new TicketExtended(ticket);
			tickets.add(ticketextended);

		}
		logger.debug("after for: " + time());
	}

	@PreDestroy
	public void close() {
		zd.close();
		log("destroyed");
	}

	public TicketExtended getRowData(String arg0) {
		log("getRowData " + arg0);
		return null;
	}

	public Object getRowKey(TicketExtended arg0) {

		log("getRowkey " + arg0);
		return null;
	}

	private void log(String a) {
		System.out.println(getClass().getName() + " " + a);

	}

	private String time() {

		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
				.format(new Date());
		return timestamp;
	}

}
