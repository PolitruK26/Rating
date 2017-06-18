package com.rating.application.DAO;


import com.rating.application.Entity.CollectionEntity;
import com.rating.application.Entity.PublicationEntity;
import com.rating.application.Entity.ScientometricBaseEntity;
import com.rating.application.Entity.TypePublicationEntity;
import org.hibernate.query.Query;

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

    public List<PublicationEntity> getFiltredPublications(String name, TypePublicationEntity type, CollectionEntity collection, Integer year, ScientometricBaseEntity base) {
        List<PublicationEntity> publicationEntities = new ArrayList<PublicationEntity>();
        try {
            begin();
            String string = "from PublicationEntity where (lower(name) like :name) and (typePublicationEntity ";

            if (type == null) string += "is not null)";
            else string += "like :typeP)";

            string += " and (collectionEntity ";
            if (collection == null) string += "is not null)";
            else string += "like :collection)";

            string += " and (year ";
            if (year == null) string += "is not null)";
            else string += "like :year)";

            string += " and (scientometricBaseEntity ";
            if (base == null) string += "is not null)";
            else string += "like :base)";

            string += " order by name";

            Query query = getSession().createQuery(string);

            query.setParameter("name", '%' + name.toLowerCase() + '%');

            if (type != null) query.setParameter("typeP", type);
            if (collection != null) query.setParameter("collection", collection);
            if (year != null) query.setParameter("year", year);
            if (base != null) query.setParameter("base", base);

            publicationEntities = query.list();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return publicationEntities;
    }

}
