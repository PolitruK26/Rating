package com.rating.application.DAO;


import com.rating.application.Entity.*;
import org.hibernate.query.Query;

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

    public List<CollectionEntity> getFiltredCollections(String name, TypeCollectionEntity type, ThemeCollectionEntity theme, ConferenceEntity conference, EditionEntity edition) {
        List<CollectionEntity> collectionEntities = new ArrayList<CollectionEntity>();
        try {
            begin();
            String string = "from CollectionEntity where (lower(name) like :name) and (typeCollectionEntity ";

            if (type == null) string += "is not null)";
            else string += "like :typeCE)";

            string += " and (themeCollectionEntity ";
            if (theme == null) string += "is not null)";
            else string += "like :themeCE)";

            string += " and (conferenceEntity ";
            if (conference == null) string += "is not null)";
            else string += "like :conferenceCE)";

            string += " and (editionEntity ";
            if (edition == null) string += "is not null)";
            else string += "like :editionCE)";

            string += " order by name";

            Query query = getSession().createQuery(string);

            query.setParameter("name", '%' + name.toLowerCase() + '%');

            if (type != null) query.setParameter("typeCE", type);
            if (theme != null) query.setParameter("themeCE", theme);
            if (conference != null) query.setParameter("conferenceCE", conference);
            if (edition != null) query.setParameter("editionCE", edition);

            collectionEntities = query.list();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return collectionEntities;
    }

}
