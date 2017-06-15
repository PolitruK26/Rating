package com.rating.application.DAO;


import com.rating.application.Entity.PublicationEntity;
import org.hibernate.criterion.Projections;

import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class PublicationDAO extends DAO {

    public void addPublication(PublicationEntity publicationEntity) {

        try {
            begin();
            getSession().save(publicationEntity);
            commit();
        } catch (RuntimeException e) {
            rollback();
            e.printStackTrace();
        } finally {
            close();
        }

    }

    public void updatePublication(PublicationEntity publicationEntity) {

        try {
            begin();
            getSession().update(publicationEntity);
            commit();
        } catch (RuntimeException e) {
            rollback();
            e.printStackTrace();
        } finally {
            close();
        }

    }

    public void deletePublication(PublicationEntity publicationEntity) {

        try {
            begin();
            getSession().delete(publicationEntity);
            commit();
        } catch (RuntimeException e) {
            rollback();
            e.printStackTrace();
        } finally {
            close();
        }

    }

    public List<PublicationEntity> getPublications() {
        List<PublicationEntity> publicationEntities = new ArrayList<PublicationEntity>();
        try {
            begin();
            publicationEntities = getSession().createQuery("from PublicationEntity order by name").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return publicationEntities;
    }

}
