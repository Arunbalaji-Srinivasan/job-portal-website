package com.neu.jobsearch.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "user")
@PrimaryKeyJoinColumn(name = "personID")
public class User extends Person {

	@Column(name = "userName")
	private String userName;

	@Column(name = "password")
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	private Email email;
	
	@Column(name = "role")
	private String role;
	
	@ManyToOne
	private Organization organization;

	@ManyToMany
    @JoinTable (
       name="job_info",
       joinColumns = {@JoinColumn(name="personID", nullable = false, updatable = false)},
       inverseJoinColumns = {@JoinColumn(name="jobID" )}
       
    )
	private Set<JobList> joblists = new HashSet<JobList>();
	
	public User(String userName, String password, String role) {
		this.userName = userName;
		this.password = password;
		this.role = role;
		
	}

	public User() {
	
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Set<JobList> getJoblists() {
		return joblists;
	}

	public void setJoblists(Set<JobList> joblists) {
		this.joblists = joblists;
	}

	
}