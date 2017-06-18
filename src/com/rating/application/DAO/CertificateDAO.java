package com.rating.application.DAO;

import com.rating.application.Entity.AuthorCertificateEntity;
import com.rating.application.Entity.AuthorPatentEntity;
import com.rating.application.Entity.CertificateEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CertificateDAO extends DAO {

    public void addCertificate(CertificateEntity certificateEntity, Collection<AuthorCertificateEntity> authorCertificateEntities) {

        try {
            begin();
            getSession().save(certificateEntity);
            for (AuthorCertificateEntity authorCertificateEntity : authorCertificateEntities)
                getSession().save(authorCertificateEntity);
            commit();
        } catch (RuntimeException e) {
            rollback();
            e.printStackTrace();
        } finally {
            close();
        }

    }

    public void updateCertificate(CertificateEntity certificateEntity, Collection<AuthorCertificateEntity> authorCertificateEntities) {

        try {
            begin();
            getSession().save(certificateEntity);
            for (AuthorCertificateEntity authorCertificateEntity : authorCertificateEntities)
                getSession().save(authorCertificateEntity);
            commit();
        } catch (RuntimeException e) {
            rollback();
            e.printStackTrace();
        } finally {
            close();
        }

    }

    public void deleteCertificate(CertificateEntity certificateEntity) {

        try {
            begin();
            getSession().delete(certificateEntity);
            commit();
        } catch (RuntimeException e) {
            rollback();
            e.printStackTrace();
        } finally {
            close();
        }

    }

    public void deleteCertificateById(Integer id) {

        try {
            begin();
            getSession().delete(getSession().get(CertificateEntity.class, id));
            commit();
        } catch (RuntimeException e) {
            rollback();
            e.printStackTrace();
        } finally {
            close();
        }

    }

    public List<CertificateEntity> getCertificates() {
        List<CertificateEntity> certificateEntities = new ArrayList<CertificateEntity>();
        try {
            begin();
            certificateEntities = getSession().createQuery("from CertificateEntity order by name").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return certificateEntities;
    }

}
