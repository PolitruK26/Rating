package com.rating.application.DAO;


import com.rating.application.Entity.CollectionEntity;

import java.util.ArrayList;
import java.util.List;

public class CollectionDAO extends DAO {

    public void addCollection(CollectionEntity collectionEntity) {

        try {
            begin();
            getSession().save(collectionEntity);
            commit();
        } catch (RuntimeException e) {
            rollback();
            e.printStackTrace();
        } finally {
            close();
        }

    }

    public void updateCollection(CollectionEntity collectionEntity) {

        try {
            begin();
            getSession().update(collectionEntity);
            commit();
        } catch (RuntimeException e) {
            rollback();
            e.printStackTrace();
        } finally {
            close();
        }

    }

    public void deleteCollection(CollectionEntity collectionEntity) {

        try {
            begin();
            getSession().delete(collectionEntity);
            commit();
        } catch (RuntimeException e) {
            rollback();
            e.printStackTrace();
        } finally {
            close();
        }

    }

    public List<CollectionEntity> getCollections() {
        List<CollectionEntity> collectionEntities = new ArrayList<CollectionEntity>();
        try {
            begin();
            collectionEntities = getSession().createQuery("from CollectionEntity order by name").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return collectionEntities;
    }

}
