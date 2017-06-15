package com.rating.application.DAO;

import com.rating.application.Entity.CertificateEntity;

import java.util.ArrayList;
import java.util.List;

public class CertificateDAO extends DAO {

    public void addCertificate(CertificateEntity certificateEntity) {

        try {
            begin();
            getSession().save(certificateEntity);
            commit();
        } catch (RuntimeException e) {
            rollback();
            e.printStackTrace();
        } finally {
            close();
        }

    }

    public void updateCertificate(CertificateEntity certificateEntity) {

        try {
            begin();
            getSession().update(certificateEntity);
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
