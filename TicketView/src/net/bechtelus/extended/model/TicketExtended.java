package net.bechtelus.extended.model;

import java.util.ArrayList;
import java.util.List;

import net.bechtelus.standard.APIAccessObject;

import org.omg.PortableServer.ForwardRequestHelper;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Comment;
import org.zendesk.client.v2.model.CustomFieldValue;
import org.zendesk.client.v2.model.Field;
import org.zendesk.client.v2.model.Group;
import org.zendesk.client.v2.model.Ticket;
import org.zendesk.client.v2.model.User;

public class TicketExtended extends Ticket {
	private static Zendesk zd;
	private static List<CommentExtended> comments;
	private static List<CustomFieldValue> cfieldvalues;
	private static List<Field> cfields;
	
	public TicketExtended(Ticket t) {
		super();

		zd = APIAccessObject.getAPIAccessObject();

		
		this.setAssigneeId(t.getAssigneeId());
		this.setCollaboratorIds(t.getCollaboratorIds());
		this.setComment(t.getComment());
		this.setCreatedAt(t.getCreatedAt());
		this.setCustomFields(t.getCustomFields());
		this.setDescription(t.getDescription());
		this.setDueAt(t.getDueAt());
		this.setExternalId(t.getExternalId());
		this.setFollowupIds(t.getFollowupIds());
		this.setForumTopicId(t.getForumTopicId());
		this.setGroupId(t.getGroupId());
		this.setHasIncidents(t.isHasIncidents());
		this.setId(t.getId());
		this.setOrganizationId(t.getOrganizationId());
		this.setPriority(t.getPriority());
		this.setProblemId(t.getProblemId());
		this.setRecipient(t.getRecipient());
		this.setRequester(t.getRequester());
		this.setRequesterId(t.getRequesterId());
		this.setSatisfactionRating(t.getSatisfactionRating());
		this.setSharingAgreementIds(t.getSharingAgreementIds());
		this.setStatus(t.getStatus());
		this.setSubject(t.getSubject());
		this.setSubmitterId(t.getSubmitterId());
		this.setTags(t.getTags());
		this.setForumTopicId(t.getForumTopicId());
		this.setFollowupIds(t.getFollowupIds());
		this.setGroupId(t.getGroupId());
		this.setType(t.getType());
		this.setTicketFormId(t.getTicketFormId());
		this.setUpdatedAt(t.getUpdatedAt());
		this.setUrl(t.getUrl());
		this.setVia(t.getVia());
		
		
		cfieldvalues = this.getCustomFields();
			
		cfields = null;
		cfields = new ArrayList<Field>();
		
			for (int i = 0; i < cfieldvalues.size(); i++) {
				CustomFieldValue cf = cfieldvalues.get(i);
				long fid = cf.getId();
				cfields.add(zd.getTicketField(fid));
				
				Field f = cfields.get(i);
				System.out.println("field: " + f.getTitle());
			}
		

	}

	
	
	public String getOrganizationName() {
		long id = this.getOrganizationId();
		return zd.getOrganization(id).getName();

	}
	
		
	public String getGroupName() {
		long id = getGroupId();
		Group group = zd.getGroup(id);
		return group.getName();
	}

	public String getsubmitterName() {
		long id = getSubmitterId();
		return zd.getUser(id).getName();
	}

	public String getRequesterName() {
		long id = getRequesterId();
		User user = zd.getUser(id);
		return user.getName();
	}

	public String getAssigneeName() {
		long id = getAssigneeId();
		User user = zd.getUser(id);
		return user.getName();
	}
	
	public  List<CommentExtended> getComments() {
		comments = null;
		comments = new ArrayList<CommentExtended>();
		for (Comment comment : zd.getTicketComments(this.id)) {
			// System.out.println("Body: " + comment.getBody());
			CommentExtended commentext = null;
			commentext = new CommentExtended(comment);
			comments.add(commentext);
		}

		return comments;
	}
	
	
}
