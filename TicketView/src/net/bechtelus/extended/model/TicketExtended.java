package net.bechtelus.extended.model;

import net.bechtelus.standard.SearchTickets;

import org.zendesk.client.v2.model.Ticket;

public class TicketExtended extends Ticket {
	private SearchTickets search;



	public TicketExtended(SearchTickets stickets, Ticket t) {
		super();
		this.search = stickets;

		
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

	}
	
}
