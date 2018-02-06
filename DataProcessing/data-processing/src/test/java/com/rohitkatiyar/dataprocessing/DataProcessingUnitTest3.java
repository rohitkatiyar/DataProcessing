package com.rohitkatiyar.dataprocessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * @author Rohit_Katiyar
 *
 */

public class DataProcessingUnitTest3 extends TestCase{
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DataProcessingUnitTest3( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( DataProcessingUnitTest3.class );
    }
    
    // Test case to check the latency
    // Send information of 1000 accounts
    public void testCheckQueryLatencyAndThroughput() throws FileNotFoundException, IOException, ParseException
    {
    	// generating 1000 account id's using random number generator
    	Random r = new Random();
    	ArrayList<Integer> accIdList = new ArrayList<Integer>();
    	
    	int accountId;
    	
    	for(int i=1; i<=1000; i++)
    	{
    		accountId = 100000 + r.nextInt(900000);
    		accIdList.add(accountId);
    	}
    	
    	DataProcessing dp = new DataProcessing();
    	//sending the requests
    	for(int accId : accIdList)
    	{
        	// first passing a JSON string to create a file
    		String jsonData = "{\"account_id\" : "+ accId +", \"event_date\": \"2017-08-24\", "
    				+ "\"account_standing\": \"G\", \"account_information\": {\"first_name\": \"John\", "
    				+ "\"last_name\": \"Doe\", \"date_of_birth\": \"1986-08-18\", "
    				+ "\"address\": {\"street_number\": \"123\", \"street_name\": \"Main Street\", "
    				+ "\"city\": \"Centerville\", \"state\": \"CA\", \"zip_code\": \"91111\"}, "
    				+ "\"email_address\": \"john_doe@gmail.com\"} }";
    		
    		Account ob = dp.parseJsonData(jsonData);
    		dp.createUpdatedViewJsonFile(ob);
    		
    		//check if the files are created with corresponding AccountId
    		// delete file if already present
        	File file = new File("JSON"+accId+".json");
        	
        	if(file.exists() && !file.isDirectory()) 
        	{ 
        		// now will again pass the JSON string with different email address
        		// and will try to confirm if the email address is updated or not
        		// for that account id
        		jsonData = "{\"account_id\" : "+ accId +", \"event_date\": \"2017-08-24\", "
        				+ "\"account_standing\": \"G\", \"account_information\": {\"first_name\": "
        				+ "\"John\", \"last_name\": \"Doe\", \"date_of_birth\": \"1986-08-18\", "
        				+ "\"address\": {\"street_number\": \"123\", \"street_name\": \"Main Street\", "
        				+ "\"city\": \"Centerville\", \"state\": \"CA\", \"zip_code\": \"91111\"}, "
        				+ "\"email_address\": \"sep_rohit@yahoo.com\"} }";
        		
        		dp.createUpdatedViewJsonFile(dp.parseJsonData(jsonData));
        		 
        		JSONObject jsonOb = (JSONObject)new JSONParser().parse(new FileReader("JSON"+accId+".json"));
        		long id = (Long) jsonOb.get("account_id");
        		
        		JSONObject accInfo = (JSONObject) jsonOb.get("account_information");
        		String email = (String) accInfo.get("email_address");
        		
        		if(id == accId && email.compareTo("sep_rohit@yahoo.com") == 0)
        		{
        			assertTrue( true );
        		}
        		else
        		{
        			assertTrue( false );
        		}
        	}
        	else
        	{
        		assertTrue( false );
        	}	
    	}
    }
}
