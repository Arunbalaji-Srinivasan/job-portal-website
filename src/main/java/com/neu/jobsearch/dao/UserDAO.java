package com.neu.jobsearch.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.neu.jobsearch.exception.UserException;
import com.neu.jobsearch.model.Email;
import com.neu.jobsearch.model.JobList;
import com.neu.jobsearch.model.User;

public class UserDAO extends MainDAO {

	public UserDAO() {
	}
	
	public User register(User u) throws UserException {
		try {
			begin();
			Email email = new Email(u.getEmail().getEmailAddress());
			User user = new User(u.getUsername(), u.getPassword(), u.getRole());

			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setEmail(email);
			user.setRole(u.getRole());
			user.setOrganization(u.getOrganization());
			email.setUser(user);
			getSession().save(user);
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("User could not be created " + e.getMessage());
		}
	}

	public User fetchByUsername(String username) throws UserException {
		try {
			begin();
			@SuppressWarnings("rawtypes")
			Query query = getSession().createQuery("from User where username = :username");
			query.setParameter("username", username);
			User user = (User) query.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("User could not be fetched" + username, e);
		}
	}

	public Email fetchByEmail(String email) throws UserException {
		try {
			begin();
			@SuppressWarnings("rawtypes")
			Query query = getSession().createQuery("from Email where emailAddress = :email");
			query.setParameter("email", email);
			Email mail = (Email) query.uniqueResult();
			commit();
			return mail;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("e-mail could not be fetched " + email, e);
		}
	}

	public User get(String username, String password) throws UserException {
		try {
			begin();
			@SuppressWarnings("rawtypes")
			Query query = getSession().createQuery("from User where username = :username and password = :password");
			query.setParameter("username", username);
			query.setParameter("password", password);
			User userResult = (User) query.uniqueResult();
			commit();
			return userResult;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("User could not be fetched" + username, e);
		}
	}
	
	@SuppressWarnings("deprecation")
	public User get(int userId) throws UserException {
		try {
			begin();
			@SuppressWarnings("rawtypes")
			Query query = getSession().createQuery("from User where personID= :personID");
			query.setInteger("personID", userId);
			User user = (User) query.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("User could not be fetched" + userId, e);
		}
	}

	public void delete(User user) throws UserException {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new UserException("User could not be fetched" + user.getUsername(), e);
		}
	}

	public void update(User user, JobList job) throws UserException {
		try {
			begin();
			user.getJoblists().add(job);
			getSession().merge(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new UserException("User could not be updated", e);
		}
	}
}