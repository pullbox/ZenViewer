package net.bechtelus.extended.model;

import net.bechtelus.standard.SearchTickets;

import org.zendesk.client.v2.model.Comment;
import org.zendesk.client.v2.model.Photo;

public class CommentExtended extends Comment {
	private SearchTickets search;

	private String authorName;
	private String photoURL;

	public CommentExtended(SearchTickets stickets, Comment com) {
		super();
		this.search = stickets;
		
		this.setAttachments(com.getAttachments());
		this.setAuthorId(com.getAuthorId());
		this.setBody(com.getBody());
		this.setCreatedAt(com.getCreatedAt());
		this.setHtml_body(com.getHtml_body());
		this.setId(com.getId());
		this.setPublic(com.isPublic());
		this.setUploads(com.getUploads());

	}

	public String getAuthorName() {
		return search.getUser(getAuthorId()).getName();

	}
	
	public void setAuthorName(String name) {
		this.setAuthorName(name);
	}

	public String getPhotoURL() {
		String url = search.getUser(getAuthorId()).getPhoto().getContentUrl();
		System.out.println(url);
		
		if (url.isEmpty()) {
			return "1";
		} else  {
			return url;
		}
		
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

	

	
	

}
