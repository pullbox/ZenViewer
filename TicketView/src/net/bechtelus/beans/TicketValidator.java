package net.bechtelus.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Ticket;

import net.bechtelus.standard.APIAccessObject;

@FacesValidator("net.bechtelus.TicketValidator")
@ManagedBean
@SessionScoped
public class TicketValidator implements Validator, Serializable {
	private static final long serialVersionUID = 7778841766245989494L;
	
	private static Zendesk zd;

	public void ticketExists(FacesContext context,
			UIComponent componentToValidate, Object value)
			throws ValidatorException {

		Ticket ticket = zd.getTicket((Integer) value);
		if (ticket != null) {
		} else {
			FacesMessage msg = new FacesMessage("The Zendesk Ticket ID does not exist!");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
		
		
	}

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		// TODO Auto-generated method stub

	}
	
	@PostConstruct
	public void init() {
		zd = APIAccessObject.getAPIAccessObject();
	}


}
