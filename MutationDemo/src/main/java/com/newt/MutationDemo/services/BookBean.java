package com.newt.MutationDemo.services;

import java.util.UUID;

import com.newt.MutationDemo.entity.Book;

public class BookBean {


	private static final int MIN_LENGTH = 3;
 
	public Book create(final String title) {
		if (title == null) {
			throw new IllegalArgumentException("title must be set");
		}
		if (title.length() <= MIN_LENGTH) {
			throw new IllegalArgumentException("title must have a minimal length of " + MIN_LENGTH);
		}
		return new Book(title, UUID.randomUUID().toString().toUpperCase());
	}
	
}
