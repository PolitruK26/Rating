package com.rating.application.DAO;

import com.rating.application.Entity.ConferenceEntity;
import com.rating.application.Entity.FormConferenceEntity;
import com.rating.application.Entity.LevelConferenceEntity;
import com.rating.application.Entity.TypeConferenceEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ConferenceDAO extends DAO {

    public void addConference(ConferenceEntity conferenceEntity) {

        try {
            begin();
            getSession().save(conferenceEntity);
            commit();
        } catch (RuntimeException e) {
            rollback();
            e.printStackTrace();
        } finally {
            close();
        }

    }

    public void updateConference(ConferenceEntity conferenceEntity) {

        try {
            begin();
            getSession().update(conferenceEntity);
            commit();
        } catch (RuntimeException e) {
            rollback();
            e.printStackTrace();
        } finally {
            close();
        }

    }

    public void deleteConference(ConferenceEntity conferenceEntity) {

        try {
            begin();
            getSession().delete(conferenceEntity);
            commit();
        } catch (RuntimeException e) {
            rollback();
            e.printStackTrace();
        } finally {
            close();
        }

    }

    public List<ConferenceEntity> getConferences() {
        List<ConferenceEntity> conferenceEntities = new ArrayList<ConferenceEntity>();
        try {
            begin();
            conferenceEntities = getSession().createQuery("from ConferenceEntity order by name").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return conferenceEntities;
    }

    public List<ConferenceEntity> getFiltredConferences(String name, TypeConferenceEntity type, FormConferenceEntity form, LevelConferenceEntity level) {
        List<ConferenceEntity> conferenceEntities = new ArrayList<ConferenceEntity>();
        try {
            begin();
            String string = "from ConferenceEntity where (lower(name) like :name) and (typeConferenceEntity ";

            if (type == null) string += "is not null)";
            else string += "like :typeCE)";

            string += " and (formConferenceEntity ";
            if (form == null) string += "is not null)";
            else string += "like :formCE)";

            string += " and (levelConferenceEntity ";
            if (level == null) string += "is not null)";
            else string += "like :levelCE)";

            string+= " order by name";

            Query query = getSession().createQuery(string);

            query.setParameter("name", '%' + name.toLowerCase() + '%');

            if (type != null) query.setParameter("typeCE", type);
            if (form != null) query.setParameter("formCE", form);
            if (level != null) query.setParameter("levelCE", level);

            conferenceEntities = query.list();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return conferenceEntities;
    }

}
