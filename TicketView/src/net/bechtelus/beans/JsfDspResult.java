package net.bechtelus.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.bechtelus.extended.model.TicketExtended;
import net.bechtelus.standard.APIAccessObject;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.omnifaces.util.Faces;
import org.primefaces.event.SelectEvent;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Ticket;

@ManagedBean
public class JsfDspResult implements Serializable {

	private static final long serialVersionUID = 7778841766245989495L;
	private static Zendesk zd;
	private String theTicketID;
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

	public void onDblClick(SelectEvent event) {
		@SuppressWarnings("unused")
		String test = "test";
		Long id = ((TicketExtended) event.getObject()).getId();

		log("onRowSelect: " + id);

		theTicketID = id.toString();

		ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext
				.getCurrentInstance().getApplication().getNavigationHandler();

		configurableNavigationHandler
				.performNavigation("DspTicket?faces-redirect=true&includeViewParams=true");

	}

	public String searchbyinfo() {
		return ("SearchByInfo"); // Means to go to index.xhtml (since
									// condition is not
		// mapped in faces-config.xml)
	}

	@PostConstruct
	public void init() {
		log("init");

		searchTerm = null;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		this.searchTerm = facesContext.getExternalContext()
				.getRequestParameterMap().get("searchTerm");

		log("seachTerm: " + this.searchTerm);

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

}
