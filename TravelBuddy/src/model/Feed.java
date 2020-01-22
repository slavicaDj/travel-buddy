package model;

import java.io.Serializable;
import java.util.Date;

public class Feed implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1059950851656294729L;

	private String title;
	private String link;
	private String description;
	private String descriptionLink;
	private String descriptionImageLink;
	private Date pubDate;
	
	public Feed() {
		
	}

	public Feed(String title, String link, String description, String descriptionLink, String descriptionImageLink,
			Date pubDate) {
		this.title = title;
		this.link = link;
		this.description = description;
		this.descriptionLink = descriptionLink;
		this.descriptionImageLink = descriptionImageLink;
		this.pubDate = pubDate;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionLink() {
		return descriptionLink;
	}

	public void setDescriptionLink(String descriptionLink) {
		this.descriptionLink = descriptionLink;
	}

	public String getDescriptionImageLink() {
		return descriptionImageLink;
	}

	public void setDescriptionImageLink(String descriptionImageLink) {
		this.descriptionImageLink = descriptionImageLink;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	@Override
	public String toString() {
		return "Feed [title=" + title + ", link=" + link + ", description=" + description + ", descriptionLink="
				+ descriptionLink + ", descriptionImageLink=" + descriptionImageLink + ", pubDate=" + pubDate + "]";
	}
	
	
}
