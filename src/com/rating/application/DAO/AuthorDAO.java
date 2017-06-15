package com.rating.application.DAO;

import com.rating.application.Entity.AuthorEntity;

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

}
