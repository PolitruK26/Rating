package com.rating.application.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

class DAO {

    private SessionFactory sessionFactory = null;
    private Session session = null;
    private Transaction transaction = null;

    DAO() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        session = sessionFactory.openSession();
    }

    Session getSession() {
        return session;
    }

    void begin() {
        transaction = session.beginTransaction();
    }

    void commit() {
        transaction.commit();
    }

    void rollback() {
        transaction.rollback();
    }

    void close() {
        session.close();
        sessionFactory.close();
    }

}
