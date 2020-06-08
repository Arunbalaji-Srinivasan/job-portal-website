package com.neu.jobsearch.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainDAO {
	
	private static final Logger log = Logger.getAnonymousLogger();
    
	@SuppressWarnings("rawtypes")
	private static final ThreadLocal sessionThread = new ThreadLocal();
    protected static final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    protected MainDAO() {
    }

    @SuppressWarnings("unchecked")
	public static Session getSession()
    {        Session session = (Session) MainDAO.sessionThread.get();
        
        if (session == null)
        {
            session = sessionFactory.openSession();
            MainDAO.sessionThread.set(session);
        }
        return session;
    }

    protected void begin() {
        getSession().beginTransaction();
    }

    protected void commit() {
        getSession().getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
	protected void rollback() {
        try {
            getSession().getTransaction().rollback();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Could not rollback", e);
        }
        try {
            getSession().close();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Could not close connection", e);
        }
        MainDAO.sessionThread.set(null);
    }

    @SuppressWarnings("unchecked")
	public static void close() {
        getSession().close();
        MainDAO.sessionThread.set(null);
    }
}