package com.neu.jobsearch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "organization")
public class Organization {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="orgID")
	private int orgID;
	
	@Column(name="orgName")
	private String orgName;
	
	@Column(name="orgAddress")
	private String orgAddress;
	
	@Column(name="orgDescription")
	private String orgDescription;

	
	public Organization() {
		
	}
	public Organization(int orgID){
		this.orgID = orgID;

	}
	public Organization( String orgName, String orgAddress, String orgDescription){
		this.orgName = orgName;
		this.orgAddress = orgAddress;
		this.orgDescription = orgDescription;
	}
	public int getOrgID() {
		return orgID;
	}
	public void setOrgID(int orgID) {
		this.orgID = orgID;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgAddress() {
		return orgAddress;
	}
	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}
	public String getOrgDescription() {
		return orgDescription;
	}
	public void setOrgDescription(String orgDescription) {
		this.orgDescription = orgDescription;
	}
	
}
