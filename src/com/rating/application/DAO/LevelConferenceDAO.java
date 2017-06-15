package com.rating.application.DAO;

import com.rating.application.Entity.LevelConferenceEntity;

import java.util.ArrayList;
import java.util.List;

public class LevelConferenceDAO extends DAO {

    public List<LevelConferenceEntity> getLevels() {
        List<LevelConferenceEntity> levelConferenceEntities = new ArrayList<LevelConferenceEntity>();
        try {
            begin();
            levelConferenceEntities = getSession().createQuery("from LevelConferenceEntity").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return levelConferenceEntities;
    }

}
