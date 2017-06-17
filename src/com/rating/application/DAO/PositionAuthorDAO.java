package com.rating.application.DAO;

import com.rating.application.Entity.PositionAuthorEntity;

import java.util.ArrayList;
import java.util.List;

public class PositionAuthorDAO extends DAO {

    public List<PositionAuthorEntity> getPosotions() {
        List<PositionAuthorEntity> positionAuthorEntities = new ArrayList<PositionAuthorEntity>();
        try {
            begin();
            positionAuthorEntities = getSession().createQuery("from PositionAuthorEntity").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return positionAuthorEntities;
    }

}
