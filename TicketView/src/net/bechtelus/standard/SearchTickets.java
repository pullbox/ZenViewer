package net.bechtelus.standard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Comment;
import org.zendesk.client.v2.model.Group;
import org.zendesk.client.v2.model.Organization;
import org.zendesk.client.v2.model.Ticket;
import org.zendesk.client.v2.model.User;

import net.bechtelus.common.DAOFactory;
import net.bechtelus.common.ZendeskConfig;
import net.bechtelus.extended.model.CommentExtended;
import net.bechtelus.extended.model.TicketExtended;
import net.bechtelus.beans.Standard;

public class SearchTickets implements Serializable {
	
	private static final long serialVersionUID = 7778841766245989494L;
	
	private DAOFactory mysqlFactory;
	private StandardDAO standardDAO;
	private Zendesk zd;
	private List<CommentExtended> comments;
	private List<TicketExtended> tickets;
	private Properties config;


	
	

	public SearchTickets() {
		config = ZendeskConfig.load();

		Zendesk.Builder builder = new Zendesk.Builder(config.getProperty("url"))
				.setUsername(config.getProperty("username"));
		if (config.getProperty("token") != null) {
			builder.setToken(config.getProperty("token"));
		} else if (config.getProperty("password") != null) {
			builder.setPassword(config.getProperty("password"));
		}

		
		
		zd = builder.build();
	}

	
	public Ticket getTicket(int id) {
		return zd.getTicket(id);
	}

	public Organization getOrganization(long id) {
		return zd.getOrganization(id);
	}

	public User getUser(long id) {
		return zd.getUser(id);
	}
	

	public Group getGroup(long id) {
		return zd.getGroup(id);

	}

	public List<CommentExtended> getComments(long id) {
		comments = new ArrayList<CommentExtended>();
		for (Comment comment : zd.getTicketComments(id)) {
			//System.out.println("Body: " + comment.getBody());
			CommentExtended commentext = null;
			commentext = new CommentExtended(this, comment);
			comments.add(commentext);
		}

		return comments;
	}
	
	public List<TicketExtended> getTickets(String searchterm) {
		tickets = new ArrayList<TicketExtended>();
		for (Ticket ticket : zd.getTicketsFromSearch(searchterm)) {
			//System.out.println("Body: " + comment.getBody());
			TicketExtended ticketext = null;
			ticketext = new TicketExtended(this, ticket);
			tickets.add(ticketext);
		}

		return tickets;
	}
	
	

}
