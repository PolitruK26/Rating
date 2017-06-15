package com.rating.application.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "conference")
public class ConferenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_conference")
    private Integer id;

    @Column(name = "conference_Name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_form_conference")
    private FormConferenceEntity formConferenceEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_level_conference")
    private LevelConferenceEntity levelConferenceEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_type_conference")
    private TypeConferenceEntity typeConferenceEntity;

    @OneToMany(mappedBy = "conferenceEntity", fetch = FetchType.EAGER)
    private Set<CollectionEntity> collectionEntities = new HashSet<CollectionEntity>();

    public ConferenceEntity() {
    }

    public ConferenceEntity(String name, FormConferenceEntity formConferenceEntity, LevelConferenceEntity levelConferenceEntity, TypeConferenceEntity typeConferenceEntity) {
        this.name = name;
        this.formConferenceEntity = formConferenceEntity;
        this.levelConferenceEntity = levelConferenceEntity;
        this.typeConferenceEntity = typeConferenceEntity;
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

    public FormConferenceEntity getFormConferenceEntity() {
        return formConferenceEntity;
    }

    public void setFormConferenceEntity(FormConferenceEntity formConferenceEntity) {
        this.formConferenceEntity = formConferenceEntity;
    }

    public LevelConferenceEntity getLevelConferenceEntity() {
        return levelConferenceEntity;
    }

    public void setLevelConferenceEntity(LevelConferenceEntity levelConferenceEntity) {
        this.levelConferenceEntity = levelConferenceEntity;
    }

    public TypeConferenceEntity getTypeConferenceEntity() {
        return typeConferenceEntity;
    }

    public void setTypeConferenceEntity(TypeConferenceEntity typeConferenceEntity) {
        this.typeConferenceEntity = typeConferenceEntity;
    }

    public Set<CollectionEntity> getCollectionEntities() {
        return collectionEntities;
    }

    public void setCollectionEntities(Set<CollectionEntity> collectionEntities) {
        this.collectionEntities = collectionEntities;
    }

}
