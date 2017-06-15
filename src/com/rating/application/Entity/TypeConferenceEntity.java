package com.rating.application.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "type_conference")
public class TypeConferenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_type_conference")
    private Integer id;

    @Column(name = "type_conference_Name")
    private String name;

    @OneToMany(mappedBy = "typeConferenceEntity", fetch = FetchType.EAGER)
    private Set<ConferenceEntity> conferenceEntities = new HashSet<ConferenceEntity>();

    public TypeConferenceEntity() {
    }

    public TypeConferenceEntity(String name, Set<ConferenceEntity> conferenceEntities) {
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
