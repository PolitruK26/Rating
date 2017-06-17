package com.rating.application.DAO;

import com.rating.application.Entity.RankAuthorEntity;

import java.util.ArrayList;
import java.util.List;

public class RankAuthorDAO extends DAO {

    public List<RankAuthorEntity> getRanks() {
        List<RankAuthorEntity> rankAuthorEntities = new ArrayList<RankAuthorEntity>();
        try {
            begin();
            rankAuthorEntities = getSession().createQuery("from RankAuthorEntity").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return rankAuthorEntities;
    }

}
