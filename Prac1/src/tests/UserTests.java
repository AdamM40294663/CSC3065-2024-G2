package tests;

import model.User;
import utils.TestUtils;

public class UserTests {

	public static void main(String[] args) {
		testUserConstructor();
		
	}
	
	public static void testUserConstructor() {
		 //automated testing
        //1- setup object and test data

        String test_username = "mike";
        String test_password = "my_passwrd";
        String test_first_name = "Mike";
        String test_last_name = "Smith";
        String test_mobile_number = "07771234567";

        //create user
        User testUser = new User(test_username, test_password, test_first_name, test_last_name, test_mobile_number);

        
        //TC1 - test Username
        if (testUser.getUsername() == test_username) {
        	System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC1-getUsername-Passed" + TestUtils.TEXT_COLOR_RESET);
        }
        else {
        	System.out.println(TestUtils.TEXT_COLOR_RED + "TC1-getUsername-Failed: Username did not match" + TestUtils.TEXT_COLOR_RESET);
        }
        
        //TC2 - test password
        if (testUser.getPassword() == test_password) {
        	System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC2-getPassword-Passed" + TestUtils.TEXT_COLOR_RESET);
        }
        else {
        	System.out.println(TestUtils.TEXT_COLOR_RED + "TC2-getPassword-Failed: Password did not match" + TestUtils.TEXT_COLOR_RESET);
        }
        
        //TC3 - test first name
        if (testUser.getFirst_name() == test_first_name) {
        	System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC3-getFirst_name-Passed" + TestUtils.TEXT_COLOR_RESET);
        }
        else {
        	System.out.println(TestUtils.TEXT_COLOR_RED + "TC3-getFirst_name-Failed: First name did not match" + TestUtils.TEXT_COLOR_RESET);
        }
        
        //TC4 - test last name 
        if (testUser.getLast_name() == test_last_name) {
        	System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC4-getLast_name-Passed" + TestUtils.TEXT_COLOR_RESET);
        }
        else {
        	System.out.println(TestUtils.TEXT_COLOR_RED + "TC4-getLast_name-Failed: Last name did not match" + TestUtils.TEXT_COLOR_RESET);
        }
        
        //TC5 - test mobile number 
        if (testUser.getMobile_number() == test_mobile_number) {
        	System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC5-getMoblie_number-Passed" + TestUtils.TEXT_COLOR_RESET);
        }
        else {
        	System.out.println(TestUtils.TEXT_COLOR_RED + "TC5-getMobile_number-Failed: Mobile number did not match" + TestUtils.TEXT_COLOR_RESET);
        }
        
        System.out.println("If all above tests are green they have passed.");
        System.out.println();
        
       
        
        //--Using Asserts-- 
        
        //Username assertion
        assert testUser.getUsername() == test_username;
     
        //Password assertion
        assert testUser.getPassword() == test_password;
        
        //First name assertion
        assert testUser.getFirst_name() == test_first_name;
        
        //Last name assertion 
        assert testUser.getLast_name() == test_last_name;
        
        //Mobile number assertion
        assert testUser.getMobile_number() == test_mobile_number;
          
        System.out.println("All Java assertions in the test suite passed.");
        
        

	}

}
