package net.bechtelus.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.bechtelus.extended.model.TicketExtended;
import net.bechtelus.standard.APIAccessObject;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.primefaces.event.SelectEvent;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Ticket;

@ManagedBean
public class JsfDspResult  implements  Serializable {

	private static final long serialVersionUID = 7778841766245989495L;
	private static Zendesk zd;
	private String searchTerm;
	private TicketExtended zenticket, selectedTicket;
	private List<TicketExtended> tickets;

	public String getSearchTerm() {
		return searchTerm;
	}

	public TicketExtended getSelectedTicket() {
		return selectedTicket;
	}

	public void setSelectedTicket(TicketExtended selectedTicket) {
		this.selectedTicket = selectedTicket;
	}

	public void setSearchTerm(String searchterm) {
		this.searchTerm = searchterm;
	}

	public List<TicketExtended> gettickets() {
		return tickets;
	}

	
	/*public void gotoTicket(ActionEvent actionEvent) {
		Faces.setSessionAttribute("ticketID", selectedTicket.getId());
	
}*/
	

	public String someOtherActionControllerMethod() {
		return ("SearchByInfo"); // Means to go to index.xhtml (since
									// condition is not
		// mapped in faces-config.xml)
	}
	
  /*  public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Ticket Selected", ((TicketExtended) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage("msgs", msg);
        
    }
 */
    

    public String onRowSelectNavigate(SelectEvent event) {  
    	Long id = ((TicketExtended) event.getObject()).getId();
    	Faces.setSessionAttribute("ticketID", id);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectedTicket",event.getObject());  
  
        return "dspTicket?faces-redirect=true";  
    }  
    
	public String searchbyinfo() {
		return ("SearchByInfo"); // Means to go to index.xhtml (since
									// condition is not
		// mapped in faces-config.xml)
	}

	@PostConstruct
	public void init() {
		searchTerm = null;
		searchTerm = Faces.getSessionAttribute("searchTerm");
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
		// TODO Auto-generated method stub
		return null;
	}

	public Object getRowKey(TicketExtended arg0) {
		// TODO Auto-generated method stub
		System.out.print(arg0);
		return null;
	}
	
	

/*	public class Navigator {
	  public void nav(String page) {
	    UIHelper.navigateTo(page);
	  }
	}*/
	
}
