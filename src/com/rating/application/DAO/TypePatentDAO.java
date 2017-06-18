package com.rating.application.DAO;

import com.rating.application.Entity.TypePatentEntity;

import java.util.ArrayList;
import java.util.List;

public class TypePatentDAO extends DAO {

    public List<TypePatentEntity> getTypes() {
        List<TypePatentEntity> typePatentEntities = new ArrayList<TypePatentEntity>();
        try {
            begin();
            typePatentEntities = getSession().createQuery("from TypePatentEntity order by name").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return typePatentEntities;
    }

}
