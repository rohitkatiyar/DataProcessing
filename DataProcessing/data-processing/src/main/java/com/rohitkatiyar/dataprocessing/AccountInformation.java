/**
 * 
 */
package com.rohitkatiyar.dataprocessing;

/**
 * @author Rohit Katiyar
 *
 */

// Class to hold the JSON object account_information
public class AccountInformation {
	
	private String firstName, lastName, dateOfBirth, emailAddress;
	private Address address;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "AccountInformation [firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
				+ ", emailAddress=" + emailAddress + ", address=" + address.toString() + "]";
	}

	
}
