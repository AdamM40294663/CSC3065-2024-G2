package tests;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

import model.Account;
import utils.TestUtils;

public class AccountTest {

	public static void main(String[] args) throws ParseException{
		testConstructor();
		testSetters();

	}
	
	//public Account(String account_number, String username_of_account_holder, String account_type,Date account_opening_date)
	//anAccount = new Account("5495-1234", "mike", "Standard", new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019"));#
	
	
	public static void testConstructor() throws ParseException{
		
		String testAccount_number = "5495-1234";
		String testUsername = "mike";
		String testAccount_type = "Standard";
		Date testAccount_opening_date = new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019");

		//Create account
		Account testAccount = new Account(testAccount_number , testUsername , testAccount_type , testAccount_opening_date);
		
		
		//TC1 getAccount_number test
		if(testAccount.getAccount_number() == testAccount_number) {
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC1-getAccount_number-Passed" + TestUtils.TEXT_COLOR_RESET);
        }
        else {
        	System.out.println(TestUtils.TEXT_COLOR_RED + "TC1-getAccount_number-Failed: Account Number did not match" + TestUtils.TEXT_COLOR_RESET);
        }
		
		//TC2 getUsername test
		if(testAccount.getUsername_of_account_holder() == testUsername) {
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC1-getUsername_of_account_holder-Passed" + TestUtils.TEXT_COLOR_RESET);
        }
        else {
        	System.out.println(TestUtils.TEXT_COLOR_RED + "TC1-getUsername_of_account_holder-Failed: Username did not match" + TestUtils.TEXT_COLOR_RESET);
        }
		
		//TC3 getAccount_type test
		if(testAccount.getAccount_type() == testAccount_type) {
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC1-getAccount_type-Passed" + TestUtils.TEXT_COLOR_RESET);
        }
        else {
        	System.out.println(TestUtils.TEXT_COLOR_RED + "TC1-getAccount_type-Failed: Username did not match" + TestUtils.TEXT_COLOR_RESET);
        }
		
		//TC4 getAccount_opening_date test
		if(testAccount.getAccount_opening_date() == testAccount_opening_date) {
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC1-getAccount_opening_date-Passed" + TestUtils.TEXT_COLOR_RESET);
        }
        else {
        	System.out.println(TestUtils.TEXT_COLOR_RED + "TC1-getAccount_opening_date-Failed: Username did not match" + TestUtils.TEXT_COLOR_RESET);
        }
		
		System.out.println("If all above tests are green they have passed");
		System.out.println();
	}

	
	
	public static void testSetters() throws ParseException{
		
		//Create blank account
		Account testAccount = new Account("" , "" , "" , new Date());
		
		//setAccount_number test using assertion 
		testAccount.setAccount_number("5495-1234");
		assert "5495-1234" == testAccount.getAccount_number();
		
		//setUsername_of_account_holder test using assertion 
		testAccount.setUsername_of_account_holder("mike");
		assert "mike" == testAccount.getUsername_of_account_holder();
		
		//setAccount_type test using assertion 
		testAccount.setAccount_type("Standard");
		assert "Standard" == testAccount.getAccount_type();
		
		System.out.println("All assertion testing in the suite has passed");
		
		//setAccount_opening_date test using same method as above as get function testing 
		Date testDate = new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019");
		testAccount.setAccount_opening_date(testDate);
		
		if(testDate.compareTo(testAccount.getAccount_opening_date()) == 0) {
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "setAccount_opening_date-Passed" + TestUtils.TEXT_COLOR_RESET);
		}
		else {
			System.out.println(TestUtils.TEXT_COLOR_RED + "setAccount_opening_date-Failed: Date did not match" + TestUtils.TEXT_COLOR_RESET);
		}
	}
}
