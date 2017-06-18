package com.rating.application.DAO;

import com.rating.application.Entity.ThemeCollectionEntity;

import java.util.ArrayList;
import java.util.List;

public class ThemeCollectionDAO extends DAO {

    public List<ThemeCollectionEntity> getThemes() {
        List<ThemeCollectionEntity> themeCollectionEntities = new ArrayList<ThemeCollectionEntity>();
        try {
            begin();
            themeCollectionEntities = getSession().createQuery("from ThemeCollectionEntity order by name").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return themeCollectionEntities;
    }

}
