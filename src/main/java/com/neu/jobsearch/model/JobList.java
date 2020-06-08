package com.neu.jobsearch.model;

import java.util.Set;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "job")
public class JobList {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "jobID", unique=true, nullable = false)
	private int jobID;
	
	@Column(name = "jobTitle")
	private String jobTitle;
	
	
	@Column(name = "jobDescription")
	private String jobDescription;
	
	
	@Column(name = "userName_job")
	private String postedName;
	
	
	@Column(name = "userID")
	private String postedID;
	
	@Column(name = "postedOn")
	private Date postedDate;
	
	@ManyToOne
	@PrimaryKeyJoinColumn
	private Organization organization;
	
	@ManyToMany(mappedBy="joblists") 
	private Set<User> users;

	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getPostedName() {
		return postedName;
	}

	public void setPostedName(String postedName) {
		this.postedName = postedName;
	}

	public String getPostedID() {
		return postedID;
	}

	public void setPostedID(String postedID) {
		this.postedID = postedID;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public JobList(){
		
	}
	
	public JobList(String jobTitle, String jobDescription){
		this.jobTitle = jobTitle;
		this.jobDescription = jobDescription;
		this.postedDate = new Date();
		
	}

}
