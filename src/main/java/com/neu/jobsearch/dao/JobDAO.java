package com.neu.jobsearch.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.neu.jobsearch.exception.JobException;
import com.neu.jobsearch.model.Organization;
import com.neu.jobsearch.model.JobList;

public class JobDAO extends MainDAO {

	public JobDAO() {

	}

	public JobList register(JobList job) throws JobException {

		try {
			begin();
			JobList jobList = new JobList(job.getJobTitle(), job.getJobDescription());
			jobList.setOrganization(job.getOrganization());
			jobList.setPostedName(job.getPostedName());
			jobList.setPostedID(job.getPostedID());
			getSession().save(jobList);
			commit();
			return jobList;
		} catch (HibernateException e) {
			rollback();
			throw new JobException("Exception while adding an org" + e.getMessage());
		}
	}

	public List<JobList> list() throws JobException {
		try {
			begin();
			@SuppressWarnings("unchecked")
			TypedQuery<JobList> query = getSession().createQuery("from JobList");
			List<JobList> list = query.getResultList();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new JobException("Jobs could not be listed", e);
		}
	}

	@SuppressWarnings("deprecation")
	public List<JobList> listCompanies(Organization organization) throws JobException {
		try {
			begin();
			@SuppressWarnings("rawtypes")
			Query query = getSession().createQuery("from JobList where organization= :org");
			query.setInteger("org", organization.getOrgID()); // Integer
			@SuppressWarnings("unchecked")
			List<JobList> list = query.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new JobException("Could not list the Jobs", e);
		}
	}

	public List<JobList> listJobsByName(String name) throws JobException {
		try {
			begin();
			@SuppressWarnings("unchecked")
			TypedQuery<JobList> query = getSession()
					.createQuery("from JobList where lower(jobTitle) LIKE lower(:jobTitle)");
			query.setParameter("jobTitle", "%" + name + "%");
			List<JobList> jobList = query.getResultList();
			commit();
			return jobList;
		} catch (HibernateException e) {
			rollback();
			throw new JobException("Jobs could not be listed", e);
		}
	}

	public JobList fetchJobs(int id) throws JobException {
		try {
			begin();
			@SuppressWarnings("rawtypes")
			Query query = getSession().createQuery("from JobList where jobID= :id");
			query.setParameter("id", id);
			JobList job = (JobList) query.uniqueResult();
			commit();
			return job;
		} catch (HibernateException e) {
			rollback();
			throw new JobException("Jobs could not be found", e);
		}
	}

	public void update(JobList job) throws JobException {
		try {
			begin();
			getSession().update(job);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new JobException("Jobs could not be updated", e);
		}
	}

}
