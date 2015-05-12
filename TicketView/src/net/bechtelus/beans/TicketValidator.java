package net.bechtelus.beans;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Ticket;

import net.bechtelus.standard.APIAccessObject;

@FacesValidator("net.bechtelus.TicketValidator")
@ManagedBean

public class TicketValidator implements Validator, Serializable {
	private static final long serialVersionUID = 7778841766245989494L;
	private static final String ticket_pattern = "^[0-9]{5,6}$";
	private static Zendesk zd;
	private Matcher matcher;
	private Pattern pattern;
	private static Logger logger;

	public void ticketExists(FacesContext context,
			UIComponent componentToValidate, Object value)
			throws ValidatorException {
		pattern = Pattern.compile(ticket_pattern);
		matcher = pattern.matcher(value.toString());

		if (!matcher.matches()) {
			FacesMessage msg = new FacesMessage(
					"Ticket ID is a 5 to 6 digit numeric number!");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

	
		int id = Integer.parseInt((String) value);
		Ticket ticket = zd.getTicket(id);
	
		if (ticket != null) {
			FacesMessage msg = new FacesMessage(
					"The Zendesk Ticket ID does exist!");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			throw new ValidatorException(msg);
		} else {
			FacesMessage msg = new FacesMessage(
					"The Zendesk Ticket ID does not exist!");
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
		this.logger = LoggerFactory.getLogger(JsfDspTicket.class);
		logger.info("Init");
		zd = APIAccessObject.getAPIAccessObject();
	}
	@PreDestroy
	public void close() {
		zd.close();
		logger.info("destroyed");
	}
	
}