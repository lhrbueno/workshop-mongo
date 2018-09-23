package com.home.workshopmongo.domain;

import java.time.LocalDateTime;

public class Comment {

	private String author;
	
	private String comment;
	
	private LocalDateTime instant;

	public Comment(String author, String comment, LocalDateTime instant) {
		this.author = author;
		this.comment = comment;
		this.instant = instant;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getInstant() {
		return instant;
	}

	public void setInstant(LocalDateTime instant) {
		this.instant = instant;
	}
	
}
