package com.rating.application.DAO;

import com.rating.application.Entity.ScientometricBaseEntity;

import java.util.ArrayList;
import java.util.List;

public class ScientometricBaseDAO extends DAO {

    public List<ScientometricBaseEntity> getBases() {
        List<ScientometricBaseEntity> scientometricBaseEntities = new ArrayList<ScientometricBaseEntity>();
        try {
            begin();
            scientometricBaseEntities = getSession().createQuery("from ScientometricBaseEntity order by name").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return scientometricBaseEntities;
    }

}
