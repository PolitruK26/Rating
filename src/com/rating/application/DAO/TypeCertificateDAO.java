package com.rating.application.DAO;

import com.rating.application.Entity.TypeCertificateEntity;

import java.util.ArrayList;
import java.util.List;

public class TypeCertificateDAO extends DAO {

    public List<TypeCertificateEntity> getTypes() {
        List<TypeCertificateEntity> typeCertificateEntities = new ArrayList<TypeCertificateEntity>();
        try {
            begin();
            typeCertificateEntities = getSession().createQuery("from TypeCertificateEntity").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return typeCertificateEntities;
    }

}
