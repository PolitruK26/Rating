package com.rating.application.DAO;

import com.rating.application.Entity.AuthorPatentEntity;
import com.rating.application.Entity.PatentEntity;
import com.rating.application.Entity.RegistrationPlaceEntity;
import com.rating.application.Entity.TypePatentEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PatentDAO extends DAO {

    public void addPatent(PatentEntity patentEntity, Collection<AuthorPatentEntity> authorPatentEntities) {

        try {
            begin();
            getSession().save(patentEntity);
            for (AuthorPatentEntity authorPatentEntity : authorPatentEntities)
                getSession().save(authorPatentEntity);
            commit();
        } catch (RuntimeException e) {
            rollback();
            e.printStackTrace();
        } finally {
            close();
        }

    }

    public void updatePatent(PatentEntity patentEntity, Collection<AuthorPatentEntity> authorPatentEntities) {

        try {
            begin();
            getSession().save(patentEntity);
            for (AuthorPatentEntity authorPatentEntity : authorPatentEntities) {
                getSession().save(authorPatentEntity);
            }
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

    public void deletePatentById(Integer id) {

        try {
            begin();
            getSession().delete(getSession().get(PatentEntity.class, id));
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

    public List<PatentEntity> getFiltredPatents(String name, TypePatentEntity type, Integer year, RegistrationPlaceEntity place) {
        List<PatentEntity> patentEntities = new ArrayList<PatentEntity>();
        try {
            begin();
            String string = "from PatentEntity where (lower(name) like :name) and (typePatentEntity ";

            if (type == null) string += "is not null)";
            else string += "like :typeP)";

            string += " and (year ";
            if (year == null) string += "is not null)";
            else string += "like :year)";

            string += " and (registrationPlaceEntity ";
            if (place == null) string += "is not null)";
            else string += "like :place)";

            string += " order by name";

            Query query = getSession().createQuery(string);

            query.setParameter("name", '%' + name.toLowerCase() + '%');

            if (type != null) query.setParameter("typeP", type);
            if (year != null) query.setParameter("year", year);
            if (place != null) query.setParameter("place", place);

            patentEntities = query.list();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return patentEntities;
    }

}
