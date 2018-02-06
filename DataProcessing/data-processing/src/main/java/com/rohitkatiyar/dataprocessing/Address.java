/**
 * 
 */
package com.rohitkatiyar.dataprocessing;

/**
 * @author Rohit Katiyar
 *
 */

// Class to hold the JSON object address inside account_information
public class Address {
	
	private String streetNumber, streetName, unitNumber, city, state, zipCode;

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "Address [streetNumber=" + streetNumber + ", streetName=" + streetName + ", unitNumber=" + unitNumber
				+ ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + "]";
	}
	

}
