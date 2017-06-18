package com.rating.application.DAO;

import com.rating.application.Entity.AuthorEntity;
import com.rating.application.Entity.PositionAuthorEntity;
import com.rating.application.Entity.PowerAuthorEntity;
import com.rating.application.Entity.RankAuthorEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class AuthorDAO extends DAO {

    public void addAuthor(AuthorEntity authorEntity) {

        try {
            begin();
            getSession().save(authorEntity);
            commit();
        } catch (RuntimeException e) {
            rollback();
            e.printStackTrace();
        } finally {
            close();
        }

    }

    public void updateAuthor(AuthorEntity authorEntity) {

        try {
            begin();
            getSession().update(authorEntity);
            commit();
        } catch (RuntimeException e) {
            rollback();
            e.printStackTrace();
        } finally {
            close();
        }

    }

    public void deleteAuthor(AuthorEntity authorEntity) {

        try {
            begin();
            getSession().delete(authorEntity);
            commit();
        } catch (RuntimeException e) {
            rollback();
            e.printStackTrace();
        } finally {
            close();
        }

    }

    public List<AuthorEntity> getAuthors() {
        List<AuthorEntity> authorEntities = new ArrayList<AuthorEntity>();
        try {
            begin();
            authorEntities = getSession().createQuery("from AuthorEntity order by secondName,firstName,middleName").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return authorEntities;
    }

    public List<AuthorEntity> getFiltredAuthors(String secondName, PositionAuthorEntity position, PowerAuthorEntity power, RankAuthorEntity rank) {
        List<AuthorEntity> authorEntities = new ArrayList<AuthorEntity>();
        try {
            begin();
            String string = "from AuthorEntity where (lower(secondName) like :secondName) and (positionAuthorEntity ";

            if (position == null) string += "is not null)";
            else string += "like :position)";

            string += " and (powerAuthorEntity ";
            if (power == null) string += "is not null)";
            else string += "like :power)";

            string += " and (rankAuthorEntity ";
            if (rank == null) string += "is not null)";
            else string += "like :rank)";

            string += " order by secondName,firstName,middleName";

            Query query = getSession().createQuery(string);

            query.setParameter("secondName", '%' + secondName.toLowerCase() + '%');

            if (position != null) query.setParameter("position", position);
            if (power != null) query.setParameter("power", power);
            if (rank != null) query.setParameter("rank", rank);

            authorEntities = query.list();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return authorEntities;
    }

}
