package com.rating.application.DAO;

import com.rating.application.Entity.RegistrationPlaceEntity;

import java.util.ArrayList;
import java.util.List;

public class RegistrationPlaceDAO extends DAO {

    public List<RegistrationPlaceEntity> getPlaces() {
        List<RegistrationPlaceEntity> registrationPlaceEntities = new ArrayList<RegistrationPlaceEntity>();
        try {
            begin();
            registrationPlaceEntities = getSession().createQuery("from RegistrationPlaceEntity").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return registrationPlaceEntities;
    }

}
