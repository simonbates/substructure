package com.bitstructures.substructure;

import org.joda.time.LocalDate;

public class Page {

	private String slug;
	private String title;
	private LocalDate datePublished;
	private String text;
	
	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public LocalDate getDatePublished() {
		return datePublished;
	}
	
	public void setDatePublished(LocalDate datePublished) {
		this.datePublished = datePublished;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

}
