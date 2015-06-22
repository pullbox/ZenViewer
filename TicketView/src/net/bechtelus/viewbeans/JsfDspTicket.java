package net.bechtelus.viewbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import net.bechtelus.controlbeans.TicketSearchController;
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

@ManagedBean
@RequestScoped
public class JsfDspTicket implements Serializable {

	
		

	private static final long serialVersionUID = 7778841766245989494L;
	private static Zendesk zd;

	private String theTicketID;
	private Ticket aticket, selectedTicket;
	private TicketExtended zenticket;
	private List<CommentExtended> comments;
	private FieldDefinition fielddef;
	private List<CustomFieldValue> cfieldvalues;
	private static Logger logger;

	
		
	public Long getTicketID() {
		return zenticket.getId();
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

	public List<CustomFieldValue> getcfieldvalues() {
		return cfieldvalues;
	}

	public List<Field> getFields() {
		return fielddef.getFields();
	}

	public String getRequesterName() {
		return zenticket.getRequesterName();
	}

	public String getAssigneeName() {
		return zenticket.getAssigneeName();
	}

	public String getFirstComment() {
		return comments.get(0).getHtml_body();
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

		this.logger = LoggerFactory.getLogger(JsfDspTicket.class);
		logger.info("Init");
		zd = APIAccessObject.getAPIAccessObject();
	}

	@PreDestroy
	public void close() {
		zd.close();
		logger.info("destroyed");
	}

	public String searchAction(String bTicketID) {
		logger.info("searchbutton action");

		theTicketID = bTicketID;
		//theTicketID = FacesContext.getCurrentInstance().getExternalContext()
		//		.getRequestParameterMap().get("theTicketID");

		logger.info("theTicketID: " + theTicketID);
		zd = APIAccessObject.getAPIAccessObject();

		aticket = null;
		zenticket = null;
		comments = null;
		aticket = zd.getTicket(Long.parseLong(theTicketID));
		zenticket = new TicketExtended(aticket);
		comments = zenticket.getComments();
		fielddef = zenticket.getfieldDef();
		cfieldvalues = zenticket.getcfieldvalues();

		logger.info("field values: " + zenticket.getcfieldvalues().toString());
		logger.info("custom fields: " + zenticket.getCustomFields().toString());
		
		
		return ("DspTicket");
	}

	public String gettheTicketID() {
		logger.info("get: " + theTicketID);
		return theTicketID;
	}

	public void settheTicketID(String aticketID) {
		this.theTicketID = aticketID;
		logger.info("setter: " + theTicketID);

		aticket = null;
		zenticket = null;
		comments = null;
		logger.info("zd close? " + zd.isClosed());
		zd = APIAccessObject.getAPIAccessObject();
		aticket = zd.getTicket(Long.parseLong(theTicketID));
		zenticket = new TicketExtended(aticket);
		comments = zenticket.getComments();
		fielddef = zenticket.getfieldDef();
		cfieldvalues = zenticket.getcfieldvalues();

		logger.info("field values: " + zenticket.getcfieldvalues().toString());
		logger.info("custom fields: " + zenticket.getCustomFields().toString());
	}

}
