/**
 * 
 */
package com.rohitkatiyar.dataprocessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author Rohit_Katiyar
 *
 */
public class DataProcessingUnitTest1 extends TestCase{

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DataProcessingUnitTest1( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( DataProcessingUnitTest1.class );
    }

    // Test JSON string without unit_number in it
    // changing email_address and testing it
    public void testJsonStringCreatingUpdatedView_ViewCheck() throws FileNotFoundException, IOException, ParseException, JSONException
    {
    
    	// delete file if already present
    	File file = new File("JSON1121345.json");
    	
    	if(file.exists() && !file.isDirectory()) 
    	{ 
    	    if(!file.delete())
    	    {
    	    	assertTrue( false );
    	    }
    	}
    	// first passing a JSON string to create a file
		String jsonData = "{\"account_id\" : 1121345, \"event_date\": \"2017-08-24\", "
				+ "\"account_standing\": \"G\", \"account_information\": {\"first_name\": \"John\", "
				+ "\"last_name\": \"Doe\", \"date_of_birth\": \"1986-08-18\", "
				+ "\"address\": {\"street_number\": \"123\", \"street_name\": \"Main Street\", "
				+ "\"city\": \"Centerville\", \"state\": \"CA\", \"zip_code\": \"91111\"}, "
				+ "\"email_address\": \"john_doe@gmail.com\"} }";
		
		DataProcessing dp = new DataProcessing();
		Account ob = dp.parseJsonData(jsonData);
		dp.createUpdatedViewJsonFile(ob);
		
		// now will again pass the JSON string with different email address
		// and will try to confirm if the email address is updated or not
		jsonData = "{\"account_id\" : 1121345, \"event_date\": \"2017-08-24\", "
				+ "\"account_standing\": \"G\", \"account_information\": {\"first_name\": "
				+ "\"John\", \"last_name\": \"Doe\", \"date_of_birth\": \"1986-08-18\", "
				+ "\"address\": {\"street_number\": \"123\", \"street_name\": \"Main Street\", "
				+ "\"city\": \"Centerville\", \"state\": \"CA\", \"zip_code\": \"91111\"}, "
				+ "\"email_address\": \"sep_rohit@yahoo.com\"} }";
		
		dp.createUpdatedViewJsonFile(dp.parseJsonData(jsonData));
		
		// Assuming account id was there in the JSON string with which the file was created 
		JSONObject jsonOb = (JSONObject)new JSONParser().parse(new FileReader("JSON1121345.json"));
		
		JSONObject accInfo = (JSONObject) jsonOb.get("account_information");
		String email = (String) accInfo.get("email_address");
		
		if(email.compareTo("sep_rohit@yahoo.com") == 0)
		{
			assertTrue( true );
		}
		else
		{
			assertTrue( false );
		}
    }
    
    // Test JSON string without unit_number in it
    // Changing email_address and testing it
    // Changing first_name, last_name and testing it
    // Changing event_date and testing it
    // Changing unit_number and testing it
    public void testJsonStringCreatingUpdatedView_ViewCheck_JsonWithoutUnitNumber() throws FileNotFoundException, IOException, ParseException, JSONException
    {
        //assertTrue( true );
    	
    	// first passing a JSON string to create a file
		String jsonData = "{\"account_id\" : 1454581, \"event_date\": \"2018-01-09\", "
				+ "\"account_standing\": \"B\", \"account_information\": {\"first_name\": \"Jane\", "
				+ "\"last_name\": \"Smith\", \"date_of_birth\": \"1975-09-09\", "
				+ "\"address\": {\"street_number\": \"345\", \"street_name\": \"Oak Drive\", "
				+ "\"unit_number\": \"12A\", \"city\": \"Mount Pleasant\", \"state\": \"CA\", "
				+ "\"zip_code\": \"90010\"}, \"email_address\": \"jane_smith@yahoo.com\"} }";
		
		
		DataProcessing dp = new DataProcessing();
		Account ob = dp.parseJsonData(jsonData);
		dp.createUpdatedViewJsonFile(ob);
		
		// now will again pass the JSON string with different email address
		// and will try to confirm if the email address is updated or not
		jsonData = "{\"account_id\" : 1454581, \"event_date\": \"2018-02-05\", "
				+ "\"account_standing\": \"B\", \"account_information\": {\"first_name\": \"Rohit\", "
				+ "\"last_name\": \"Katiyar\", \"date_of_birth\": \"1975-09-09\", "
				+ "\"address\": {\"street_number\": \"345\", \"street_name\": \"Oak Drive\", "
				+ "\"unit_number\": \"13B\", \"city\": \"Mount Pleasant\", \"state\": \"CA\", "
				+ "\"zip_code\": \"90010\"}, \"email_address\": \"sep_rohit@yahoo.com\"} }";
		
		dp.createUpdatedViewJsonFile(dp.parseJsonData(jsonData));
		
		// Assuming account id was there in the JSON string with which the file was created 
		JSONObject jsonOb = (JSONObject)new JSONParser().parse(new FileReader("JSON1454581.json"));
		
		String eventDate = (String) jsonOb.get("event_date");
		
		JSONObject accInfo = (JSONObject) jsonOb.get("account_information");
		
		String firstName = (String) accInfo.get("first_name");
		String lastName = (String) accInfo.get("last_name");
		String email = (String) accInfo.get("email_address");
		
		String unitNum = (String) ((JSONObject) accInfo.get("address")).get("unit_number");
		
		if(email.compareTo("sep_rohit@yahoo.com") == 0
				&& firstName.compareTo("Rohit") == 0
				&& lastName.compareTo("Katiyar") == 0
				&& eventDate.compareTo("2018-02-05") == 0
				&& unitNum.compareTo("13B") == 0)
		{
			assertTrue( true );
		}
		else
		{
			assertTrue( false );
		}
    }

}
