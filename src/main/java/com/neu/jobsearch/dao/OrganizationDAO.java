package com.neu.jobsearch.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.neu.jobsearch.exception.OrganizationException;
import com.neu.jobsearch.exception.UserException;
import com.neu.jobsearch.model.Organization;

public class OrganizationDAO extends MainDAO {

	public OrganizationDAO() {

	}

	public Organization register(Organization com) throws OrganizationException {
		try {
			begin();
			Organization organization = new Organization(com.getOrgName(), com.getOrgAddress(), com.getOrgDescription());
			getSession().saveOrUpdate(organization);
			commit();
			return organization;
		} catch (HibernateException e) {
			rollback();
			throw new OrganizationException("Exception while adding an org: " + e.getMessage());
		}
	}

	public Organization get(String name) throws UserException {
		try {
			begin();
			@SuppressWarnings("rawtypes")
			Query query = getSession().createQuery("from Organization where orgName= :orgName");
			query.setParameter("orgName", name);
			Organization organization = (Organization) query.uniqueResult();
			commit();
			return organization;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not fetch the org" + name, e);
		}
	}

	public List<Organization> list() throws OrganizationException {
		try {
			begin();
			@SuppressWarnings("unchecked")
			TypedQuery<Organization> query = getSession().createQuery("FROM Organization");
			List<Organization> orgList = query.getResultList();
			commit();
			return orgList;
		} catch (HibernateException e) {
			rollback();
			throw new OrganizationException("Could not list the organizations", e);
		}
	}

	public Organization getById(int id) throws UserException {
		try {
			begin();
			@SuppressWarnings("rawtypes")
			Query query = getSession().createQuery("from Organization where orgID= :orgID");
			query.setParameter("orgID", id);
			Organization organization = (Organization) query.uniqueResult();
			commit();
			return organization;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not fetch the org" + id, e);
		}
	}

	public Organization delete(Organization organization) throws OrganizationException {
		try {
			begin();
			getSession().delete(organization);
			commit();
			return organization;
		} catch (HibernateException e) {
			rollback();
			throw new OrganizationException("Could not delete the org" + organization.getOrgID(), e);
		}
	}
}
