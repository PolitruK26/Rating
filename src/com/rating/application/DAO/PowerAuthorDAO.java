package com.rating.application.DAO;

import com.rating.application.Entity.PowerAuthorEntity;

import java.util.ArrayList;
import java.util.List;

public class PowerAuthorDAO extends DAO {

    public List<PowerAuthorEntity> getPowers() {
        List<PowerAuthorEntity> powerAuthorEntities = new ArrayList<PowerAuthorEntity>();
        try {
            begin();
            powerAuthorEntities = getSession().createQuery("from PowerAuthorEntity").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return powerAuthorEntities;
    }

}
