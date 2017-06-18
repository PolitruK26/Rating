package com.rating.application.DAO;

import com.rating.application.Entity.EditionEntity;

import java.util.ArrayList;
import java.util.List;

public class EditionDAO extends DAO {

    public List<EditionEntity> getEditions() {
        List<EditionEntity> editionEntities = new ArrayList<EditionEntity>();
        try {
            begin();
            editionEntities = getSession().createQuery("from EditionEntity order by name").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return editionEntities;
    }

}
