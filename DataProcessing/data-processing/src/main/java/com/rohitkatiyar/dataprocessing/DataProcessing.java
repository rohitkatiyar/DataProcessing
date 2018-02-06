package com.rohitkatiyar.dataprocessing;
import java.io.*;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * @author Rohit_Katiyar
 *
 */
public class DataProcessing {

	// Assuming the data coming from client with JSON structure in string form
	public Account parseJsonData(String jsonData)
	{
		/*
		 * {"account_id" : 1121345, "event_date": "2017-08-24", "account_standing": "G", 
		 * "account_information": {"first_name": "John", "last_name": "Doe", 
		 * "date_of_birth": "1986-08-18", "address": {"street_number": "123", 
		 * "street_name": "Main Street", "city": "Centerville", "state": "CA", "zip_code": "91111"}, "email_address": "john_doe@gmail.com"} }
		 */
		if(!validateIncomingJsonString(jsonData))
		{
			return null;
		}
		
		Account accountOb = null;
		try {
			
			// populate the account object
			accountOb = new Account();
			AccountInformation accountInf = new AccountInformation();
			Address addressOb = new Address();
			
			JSONObject accountJson = new JSONObject(jsonData);
			accountOb.setAccountId(Integer.parseInt(accountJson.getString("account_id")));
			accountOb.setEventDate(accountJson.getString("event_date"));
			accountOb.setAccountStanding(accountJson.getString("account_standing"));
			
			JSONObject accountInfJson = accountJson.getJSONObject("account_information");
			accountInf.setFirstName(accountInfJson.getString("first_name"));
			accountInf.setLastName(accountInfJson.getString("last_name"));
			accountInf.setEmailAddress(accountInfJson.getString("email_address"));
			accountInf.setDateOfBirth(accountInfJson.getString("date_of_birth"));
			
			JSONObject addressJson = accountInfJson.getJSONObject("address");
			addressOb.setStreetNumber(addressJson.getString("street_number"));
			addressOb.setStreetName(addressJson.getString("street_name"));
			if(jsonData.indexOf("unit_number") != -1)
			{
				addressOb.setUnitNumber(addressJson.getString("unit_number"));
			}
			addressOb.setCity(addressJson.getString("city"));
			addressOb.setState(addressJson.getString("state"));
			addressOb.setZipCode(addressJson.getString("zip_code"));
			
			accountInf.setAddress(addressOb);
			accountOb.setAccountInfo(accountInf);
			
			//System.out.println(accountOb.toString());
				
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return accountOb;
	}
	
	// function to create the view file with JSON structure
	public void createUpdatedViewJsonFile(Account account)
	{
		// creating file name
		
		if(account == null)
		{
			return;
		}
		
		String fileName = "JSON" + account.getAccountId() + ".json";
		
		PrintWriter pw = null;
		
		try {
			// create the file
			pw = new PrintWriter(fileName);
			
			JSONObject accountJson = new JSONObject();
			
			// creating JSON object Account
			accountJson.put("account_id", account.getAccountId());
			accountJson.put("event_date", account.getEventDate());
			accountJson.put("account_standing", account.getAccountStanding());
			
			//Map addressMap = null;
			
			JSONObject addressMap = new JSONObject();
			
			// checking if the Account object has unit number set in it
			if(account.getAccountInfo().getAddress().getUnitNumber() != null)
			{
				addressMap.put("unit_number", account.getAccountInfo().getAddress().getUnitNumber());
			}
			
			addressMap.put("street_number", account.getAccountInfo().getAddress().getStreetNumber());
			addressMap.put("street_name", account.getAccountInfo().getAddress().getStreetName());
			addressMap.put("city", account.getAccountInfo().getAddress().getCity());
			addressMap.put("state", account.getAccountInfo().getAddress().getState());
			addressMap.put("zip_code", account.getAccountInfo().getAddress().getZipCode());
			
			JSONObject accountInfoMap = new JSONObject();
			
			accountInfoMap.put("first_name", account.getAccountInfo().getFirstName());
			accountInfoMap.put("last_name", account.getAccountInfo().getLastName());
			accountInfoMap.put("date_of_birth", account.getAccountInfo().getDateOfBirth());
			accountInfoMap.put("address", addressMap);
			accountInfoMap.put("email_address", account.getAccountInfo().getEmailAddress());
			
			accountJson.put("account_information", accountInfoMap);
			
			// writing the JSON file for view
	        pw.write(accountJson.toString());
	        
	        // flush and close the file
	        pw.flush();
	        pw.close();
	        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
			
	}
	
	// function to validate the incoming JSON string
	public boolean validateIncomingJsonString(String jsonStr)
	{
		boolean isJasonValid = true;
		
		// checking for most important parameters which should be present in the JSON
		if(jsonStr.indexOf("account_id") == -1
				|| jsonStr.indexOf("event_date") == -1
				|| jsonStr.indexOf("account_standing") == -1
				|| jsonStr.indexOf("account_information") == -1
				|| jsonStr.indexOf("first_name") == -1
				|| jsonStr.indexOf("last_name") == -1
				|| jsonStr.indexOf("email_address") == -1
				|| jsonStr.indexOf("date_of_birth") == -1
				|| jsonStr.indexOf("address") == -1
				|| jsonStr.indexOf("street_number") == -1
				|| jsonStr.indexOf("street_name") == -1
				|| jsonStr.indexOf("city") == -1
				|| jsonStr.indexOf("state") == -1
				|| jsonStr.indexOf("zip_code") == -1)
		{
			isJasonValid = false;
		}
		
		return isJasonValid;
	}

}
