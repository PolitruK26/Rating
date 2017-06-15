package com.rating.application.DAO;

import com.rating.application.Entity.PatentEntity;

import java.util.ArrayList;
import java.util.List;

public class PatentDAO extends DAO {

    public void addPatent(PatentEntity patentEntity) {

        try {
            begin();
            getSession().save(patentEntity);
            commit();
        } catch (RuntimeException e) {
            rollback();
            e.printStackTrace();
        } finally {
            close();
        }

    }

    public void updatePatent(PatentEntity patentEntity) {

        try {
            begin();
            getSession().update(patentEntity);
            commit();
        } catch (RuntimeException e) {
            rollback();
            e.printStackTrace();
        } finally {
            close();
        }

    }

    public void deletePatent(PatentEntity patentEntity) {

        try {
            begin();
            getSession().delete(patentEntity);
            commit();
        } catch (RuntimeException e) {
            rollback();
            e.printStackTrace();
        } finally {
            close();
        }

    }

    public List<PatentEntity> getPanents() {
        List<PatentEntity> patentEntities = new ArrayList<PatentEntity>();
        try {
            begin();
            patentEntities = getSession().createQuery("from PatentEntity order by name").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return patentEntities;
    }

}
