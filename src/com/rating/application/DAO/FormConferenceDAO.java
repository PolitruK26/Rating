package com.rating.application.DAO;

import com.rating.application.Entity.FormConferenceEntity;

import java.util.ArrayList;
import java.util.List;

public class FormConferenceDAO extends DAO {

    public List<FormConferenceEntity> getForms() {
        List<FormConferenceEntity> formConferenceEntities = new ArrayList<FormConferenceEntity>();
        try {
            begin();
            formConferenceEntities = getSession().createQuery("from FormConferenceEntity order by name").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return formConferenceEntities;
    }

}
