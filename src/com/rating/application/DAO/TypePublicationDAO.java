package com.rating.application.DAO;

import com.rating.application.Entity.TypePublicationEntity;

import java.util.ArrayList;
import java.util.List;

public class TypePublicationDAO extends DAO {

    public List<TypePublicationEntity> getTypes() {
        List<TypePublicationEntity> typePublicationEntities = new ArrayList<TypePublicationEntity>();
        try {
            begin();
            typePublicationEntities = getSession().createQuery("from TypePublicationEntity").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return typePublicationEntities;
    }

}
