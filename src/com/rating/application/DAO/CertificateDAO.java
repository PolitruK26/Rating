package com.rating.application.DAO;

import com.rating.application.Entity.AuthorCertificateEntity;
import com.rating.application.Entity.CertificateEntity;
import com.rating.application.Entity.TypeCertificateEntity;
import org.hibernate.query.Query;

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

    public List<CertificateEntity> getFiltredCertificates(String name, TypeCertificateEntity type, Integer year) {
        List<CertificateEntity> certificateEntities = new ArrayList<CertificateEntity>();
        try {
            begin();
            String string = "from CertificateEntity where (lower(name) like :name) and (typeCertificateEntity ";

            if (type == null) string += "is not null)";
            else string += "like :typeC)";

            string += " and (year ";
            if (year == null) string += "is not null)";
            else string += "like :year)";

            string += " order by name";

            Query query = getSession().createQuery(string);

            query.setParameter("name", '%' + name.toLowerCase() + '%');

            if (type != null) query.setParameter("typeC", type);
            if (year != null) query.setParameter("year", year);

            certificateEntities = query.list();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return certificateEntities;
    }

}
