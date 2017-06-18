package com.rating.application.DAO;


import com.rating.application.Entity.KeywordsEntity;

import java.util.ArrayList;
import java.util.List;

public class KeyWordsDAO extends DAO {

    public List<KeywordsEntity> getKeyWords() {
        List<KeywordsEntity> keywordsEntities = new ArrayList<KeywordsEntity>();
        try {
            begin();
            keywordsEntities = getSession().createQuery("from KeywordsEntity order by name").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return keywordsEntities;
    }

}
