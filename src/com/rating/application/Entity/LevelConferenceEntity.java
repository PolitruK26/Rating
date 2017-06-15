package com.rating.application.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "level_conference")
public class LevelConferenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_level_conference")
    private Integer id;

    @Column(name = "level_conference_Name")
    private String name;

    @OneToMany(mappedBy = "levelConferenceEntity", fetch = FetchType.EAGER)
    private Set<ConferenceEntity> conferenceEntities = new HashSet<ConferenceEntity>();

    public LevelConferenceEntity() {
    }

    public LevelConferenceEntity(String name, Set<ConferenceEntity> conferenceEntities) {
        this.name = name;
        this.conferenceEntities = conferenceEntities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ConferenceEntity> getConferenceEntities() {
        return conferenceEntities;
    }

    public void setConferenceEntities(Set<ConferenceEntity> conferenceEntities) {
        this.conferenceEntities = conferenceEntities;
    }

    @Override
    public String toString() {
        return name;
    }
}
