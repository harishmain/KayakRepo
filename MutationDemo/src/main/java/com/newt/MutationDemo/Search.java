package com.newt.MutationDemo;

import java.util.Scanner;
import java.io.IOException;
import java.util.*;

class Search {
	//Data Base Information of the user
	private String loginID;
	private String password;
	private String mobileNumber;
	private String accountBalance;

	public boolean authenticate() throws IOException {//begin function authenticate
		boolean result = false;
		FileArrayProvider read = new FileArrayProvider();
        String[] lines = read.readLines("C:\\devops\\Workspace\\MutationDemo\\data\\Test.csv");
        for (String line : lines) { //begin for
            List<String> test = Arrays.asList(line.split(","));
            if (loginID.equals(test.get(0).toString())) { //Begin if2
            	if (password.equals(test.get(1).toString())) { //Begin if1
            		result = true;
            		break;//exit for loop            		
            	}//end if1
            } //end if2         
        }//end for
		return result;
	}//end function authenticate

	public void getBillAmount() throws IOException {//begin function BillPay
		String formattedResult = "";
		FileArrayProvider read = new FileArrayProvider();
        String[] lines = read.readLines("C:\\devops\\Workspace\\MutationDemo\\data\\Test.csv");
        for (String line : lines) { //begin for
            List<String> test = Arrays.asList(line.split(","));
            if (loginID.equals(test.get(0).toString())) { //Begin if
            	mobileNumber =  test.get(2);
            	accountBalance = test.get(3);            	       
            	break;//out of for loop
            } //end if
        }//end for
	}//end function BillAmount


	//Default values for anonymous users
	public Search() {
		loginID = "Anonymous";
		password = "N/A";
		mobileNumber = "";
		accountBalance = "";
	}
	
	public void  setLoginIdPwd(String lId, String pwd) {
		loginID = lId;
		password = pwd;
	}
		
	public void setData() {
		mobileNumber = "";
		accountBalance = "";
	}
	public void printBillAmount() {
		System.out.println("---------------------------------------------------");
		System.out.println("                   WELCOME                         ");
		System.out.println("---------------------------------------------------");
		System.out.println("Mobile number is ");
		System.out.println(mobileNumber);
		System.out.println("---------------------------------------------------");
		System.out.println("Account Balance ");
		System.out.println(accountBalance);
		System.out.println("\n\n                 Click here for Bill Pay ");
		System.out.println("                   ########                         ");
		System.out.println("                   BILLPAY                          ");
		System.out.println("                   ########                         ");
	}

	public String getMobileNum() {
		return mobileNumber;
	}
	public String getAccountBalance() {
		return accountBalance;
	}
	public static void main(String args[]) {
		
		Search userSession = new Search();
		
		// create a scanner so we can read the command-line input
	    Scanner scanner = new Scanner(System.in);
	    
	    //  prompt for the user's name
	    System.out.print("Enter your LoginID: ");
	    
	    // get their input as a String
	    String loginId = scanner.next().toString();
	    
	    // prompt for their password
	    System.out.print("Enter your password: ");
	    
	    //get their input as a String
	    String password = scanner.next().toString();
	    
	    //Set the login ID password
	    userSession.setLoginIdPwd(loginId, password);
	
	    try {
		    if(userSession.authenticate()) {//begin if1
		    	userSession.setData();
		    	System.out.print("Enter your Search Query: ");
		    	String searchQuery = scanner.next();
		    	if (searchQuery.toUpperCase().equals("BILLPAY")) { //Begin If2
		    		try {//begin exception
		    			userSession.getBillAmount();
		    			userSession.printBillAmount();
		    		} 
		    		catch(IOException exbillpay){
		    			System.out.println(exbillpay.toString());
		    		}//end exception
		    	}//end if 2
		    	else {
				    System.out.println("Functionality Yet to be implemented: ");
		    	}//End else2
		    }//END if1
		    else {
		    	System.out.println("Invalid user please try again: ");
		    }//End else1 
	    }  
	    catch(IOException ex) {
	    	System.out.println(ex.toString());
	    	System.out.println("DB Error. Unvailable to authenticate. ");
	    }
	  }
}
