package com.rating.application.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "form_conference")
public class FormConferenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_form_conference")
    private Integer id;

    @Column(name = "form_conference_Name")
    private String name;

    @OneToMany(mappedBy = "formConferenceEntity", fetch = FetchType.EAGER)
    private Set<ConferenceEntity> conferenceEntities = new HashSet<ConferenceEntity>();

    public FormConferenceEntity() {
    }

    public FormConferenceEntity(String name) {
        this.name = name;
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
