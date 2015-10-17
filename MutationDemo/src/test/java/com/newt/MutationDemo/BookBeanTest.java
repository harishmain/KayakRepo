package com.newt.MutationDemo;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.Test;

import com.newt.MutationDemo.entity.Book;
import com.newt.MutationDemo.services.BookBean;

public class BookBeanTest {


	private static final String TITLE = "Some book";
 
	BookBean bookBean = new BookBean();
 
	@Test
	public void shouldCreateBook() throws Exception {
		Book book = bookBean.create(TITLE);
		//assertEquals("get some book... :", "Some book", book.getTitle());
		assertThat(book, notNullValue());
		assertThat(book.getTitle(), equalTo(TITLE));
		assertThat(book.getId(), not(isEmptyOrNullString()));
	}
 
	@Test(expected = IllegalArgumentException.class)
	public void shouldFailCreateBookWithNoTitleGiven() throws Exception {
		bookBean.create(null);
	}
 
	@Test(expected = IllegalArgumentException.class)
	public void shouldFailCreateBookWithTooShortTitleGiven() throws Exception {
		bookBean.create("ab");
	}

}
