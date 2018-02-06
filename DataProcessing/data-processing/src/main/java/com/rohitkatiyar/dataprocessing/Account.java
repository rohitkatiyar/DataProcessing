/**
 * @author Rohit Katiyar
 *
 */
package com.rohitkatiyar.dataprocessing;

// Account class to hold the main Account JSON object
public class Account {
	
	private int accountId;
	private String eventDate, accountStanding;
	private AccountInformation accountInfo;
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getAccountStanding() {
		return accountStanding;
	}
	public void setAccountStanding(String accountStanding) {
		this.accountStanding = accountStanding;
	}
	public AccountInformation getAccountInfo() {
		return accountInfo;
	}
	public void setAccountInfo(AccountInformation accountInfo) {
		this.accountInfo = accountInfo;
	}
	
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", eventDate=" + eventDate + ", accountStanding=" + accountStanding
				+ ", accountInfo=" + accountInfo.toString() + "]";
	}
	
}
