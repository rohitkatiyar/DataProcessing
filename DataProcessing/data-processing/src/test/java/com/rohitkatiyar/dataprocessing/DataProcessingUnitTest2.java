package com.rohitkatiyar.dataprocessing;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author Rohit_Katiyar
 *
 */

public class DataProcessingUnitTest2 extends TestCase{
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DataProcessingUnitTest2( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( DataProcessingUnitTest2.class );
    }
    
    public void testFunctionParseJasonData()
    {
    	// first passing a JSON string to create a file
		String jsonData = "{\"account_id\" : 1121345, \"event_date\": \"2017-08-24\", "
				+ "\"account_standing\": \"G\", \"account_information\": {\"first_name\": \"John\", "
				+ "\"last_name\": \"Doe\", \"date_of_birth\": \"1986-08-18\", "
				+ "\"address\": {\"street_number\": \"123\", \"street_name\": \"Main Street\", "
				+ "\"city\": \"Centerville\", \"state\": \"CA\", \"zip_code\": \"91111\"}, "
				+ "\"email_address\": \"john_doe@gmail.com\"} }";
		
		DataProcessing dp = new DataProcessing();
		Account ob = dp.parseJsonData(jsonData);
		
		// check if Account object is populated properly
		if(ob.getAccountId() == 1121345 
				&& ob.getEventDate().compareTo("2017-08-24") == 0
				&& ob.getAccountStanding().compareTo("G") == 0
				&& ob.getAccountInfo().getFirstName().compareTo("John") == 0
				&& ob.getAccountInfo().getEmailAddress().compareTo("john_doe@gmail.com") == 0
				&& ob.getAccountInfo().getAddress().getZipCode().compareTo("91111") == 0)
		{
			assertTrue( true );
		}
		else
		{
			assertTrue( false );
		}
		
    }
    
    // testing for invalid JSON
    public void testFunctionParseJasonData_InvalidJson()
    {
    	// first passing a JSON string to create a file
		String invalidJsonData = "{\"account_id\" : 1121345, \"event_date\": \"2017-08-24\", "
				+ "\"account_standing\": \"G\", \"account_information\": {\"first_name\": \"John\", "
				+ "\"last_name\": \"Doe\", \"date_of_birth\": \"1986-08-18\", "
				+ "\"address\": {\"street_number\": \"123\", \"street_name\": \"Main Street\", "
				+ "\"city\": \"Centerville\", \"state\": \"CA\", \"zip_code\": \"91111\"}, "
				+ "} }";
		
		DataProcessing dp = new DataProcessing();
		Account ob = dp.parseJsonData(invalidJsonData);
		
		if(ob == null)
		{
			assertTrue( true );
		}
		else
		{
			assertTrue( false );
		}
    }

}
