package com.rating.application.DAO;

import com.rating.application.Entity.TypeConferenceEntity;

import java.util.ArrayList;
import java.util.List;

public class TypeConferenceDAO extends DAO {

    public List<TypeConferenceEntity> getTypes() {
        List<TypeConferenceEntity> typeConferenceEntities = new ArrayList<TypeConferenceEntity>();
        try {
            begin();
            typeConferenceEntities = getSession().createQuery("from TypeConferenceEntity order by name").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return typeConferenceEntities;
    }

}
