package com.newt.MutationDemo;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class SearchUnitTest {

	@Test
	public void testAuthenticate() {
		
		Search obj = new Search();
		//Test case 1
		obj.setLoginIdPwd("user1", "pass1"); 
		try {
		assertEquals("tc1 :: for valid user id the method returns TRUE", true, obj.authenticate());	
		} catch (IOException ex) {
			System.out.println(ex);
			fail("Problem with file"); 
		}

		//Test case 1b
		obj.setLoginIdPwd("user4", "pass4"); 
		try {
		assertEquals("tc1b :: for valid user id the method returns TRUE", true, obj.authenticate());	
		} catch (IOException ex) {
			System.out.println(ex);
			fail("Problem with file"); 
		}
		
		//Test case 1c
		obj.setLoginIdPwd("user5", "pass5"); 
		try {
		assertEquals("tc1c :: for valid user id the method returns TRUE", true, obj.authenticate());	
		} catch (IOException ex) {
			System.out.println(ex);
			fail("Problem with file"); 
		}		
		
		//Test case 2
		obj.setLoginIdPwd("user1", "pass22"); 
		try {
		assertEquals("tc2 :: for invalid password the method returns FALSE", false, obj.authenticate());	
		} catch (IOException ex) {
			System.out.println(ex);
			fail("Problem with file"); 
		}
		
		//Test case 3
		obj.setLoginIdPwd("userxyz", "pass2"); 
		try {
		assertEquals("tc3 :: for invalid user id the method returns FALSE", false, obj.authenticate());	
		} catch (IOException ex) {
			System.out.println(ex);
			fail("Problem with file"); 
		}
		
		//Test case 4
		obj.setLoginIdPwd("", ""); 
		try {
		assertEquals("tc4 :: for invalid user id the method returns FALSE", false, obj.authenticate());	
		} catch (IOException ex) {
			System.out.println(ex);
			fail("Problem with file"); 
		}
	}


	@Test
	public void testGetBillAmount() {
		
		Search obj = new Search();
		
		//Test case 1
		obj.setLoginIdPwd("user2", "pass2");
		try {
			obj.getBillAmount();
		} catch (IOException ex){
			System.out.println(ex);
		}		
		
		assertEquals("for user2 bill amount is :", "40.03", obj.getAccountBalance());
		
		
		//Test case 2
		obj.setLoginIdPwd("user5", "pass5");
		try {
			obj.getBillAmount();
		} catch (IOException ex){
			System.out.println(ex);
		}		
		
		assertEquals("for user5 bill amount is :", "0", obj.getAccountBalance());

		
		//Test case 3
		obj.setLoginIdPwd("user3", "pass3");
		try {
			obj.getBillAmount();
		} catch (IOException ex){
			System.out.println(ex);
		}
		
		assertEquals("for user3 bill amount is :", "-40.03", obj.getAccountBalance());
	}
	
	
/*	@Test
	public void testTestCase01() {
		Search obj = new Search();
		//Test case 1
		//no set userid,pwd as the user is anonymous
		try {
			obj.getBillAmount();
			assertEquals("for anonymous users :", "", obj.getAccountBalance());
			assertEquals("for anonymous users :", "", obj.getMobileNum());
		} catch (IOException ex){
			System.out.println(ex);
			fail("Error in DB");
		}
	}*/

	@Test
	public void testTestCase02() {
		Search obj = new Search();
		obj.setLoginIdPwd("user3", "pass3");
		try {
			obj.getBillAmount();
			assertEquals("for anonymous users :", "-40.03", obj.getAccountBalance());
			assertEquals("for anonymous users :", "7885667890", obj.getMobileNum());			
		} catch (IOException ex){
			fail("Error in DB");
			System.out.println(ex);
		}
	}
	@Test
	public void testTestCase03() {
		Search obj = new Search();
		obj.setLoginIdPwd("user5", "pass5");
		try {
			obj.getBillAmount();
			assertEquals("for anonymous users :", "0", obj.getAccountBalance());
			assertEquals("for anonymous users :", "7435667890", obj.getMobileNum());			
		} catch (IOException ex){
			fail("Error in DB");
			System.out.println(ex);
		}

	}	


	
}
