package com.rating.application.DAO;

import com.rating.application.Entity.TypeCollectionEntity;

import java.util.ArrayList;
import java.util.List;

public class TypeCollectionDAO extends DAO {

    public List<TypeCollectionEntity> getTypes() {
        List<TypeCollectionEntity> typeCollectionEntities = new ArrayList<TypeCollectionEntity>();
        try {
            begin();
            typeCollectionEntities = getSession().createQuery("from TypeCollectionEntity order by name").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return typeCollectionEntities;
    }

}
