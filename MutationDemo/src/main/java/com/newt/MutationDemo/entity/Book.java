package com.newt.MutationDemo.entity;

public class Book {

	private final String title;
	private final String id;
 
	public Book(final String title, final String id) {
		this.title = title;
		this.id = id;
	}
 
	public String getTitle() {
		return title;
	}

	public String getId() {
		return id;
	}
		
 
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [title=").append(title).append(", id=").append(id).append("]");
		return builder.toString();
	}
	
}
