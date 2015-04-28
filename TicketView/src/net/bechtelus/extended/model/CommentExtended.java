package net.bechtelus.extended.model;

import net.bechtelus.standard.APIAccessObject;

import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Comment;
import org.zendesk.client.v2.model.Photo;

public class CommentExtended extends Comment {
	private Photo photo;
	private static Zendesk zd;

	private String noPhotoURL = "/resources/images/no_photo.jpg";

	public CommentExtended(Comment com) {
		super();
		zd = APIAccessObject.getAPIAccessObject();

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
		return zd.getUser(getAuthorId()).getName();
		}

	public void setAuthorName(String name) {
		this.setAuthorName(name);
	}
	public String getPublicStatement() {
		if (isPublic()) {
			return "Public";
		} else {
			return "Private";	
		}
		
	}

	public String getPhotoURL() {
		photo = null;
		photo = zd.getUser(getAuthorId()).getPhoto();
		String url;
		if (photo != null) {
			url = photo.getContentUrl();

		} else {

			url = noPhotoURL;
		}

		return url;

	}

	public void setPhotoURL(String photoURL) {
	}

}
