package com.rating.application.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "collection")
public class CollectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_collection")
    private Integer id;

    @Column(name = "collection_Name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_type_collection")
    private TypeCollectionEntity typeCollectionEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_theme_collection")
    private ThemeCollectionEntity themeCollectionEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_conference")
    private ConferenceEntity conferenceEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_edition")
    private EditionEntity editionEntity;

    @OneToMany(mappedBy = "collectionEntity", fetch = FetchType.EAGER)
    private Set<PublicationEntity> publicationEntities = new HashSet<PublicationEntity>();

    public CollectionEntity() {
    }

    public CollectionEntity(String name, TypeCollectionEntity typeCollectionEntity, ThemeCollectionEntity themeCollectionEntity, ConferenceEntity conferenceEntity, EditionEntity editionEntity, Set<PublicationEntity> publicationEntities) {
        this.name = name;
        this.typeCollectionEntity = typeCollectionEntity;
        this.themeCollectionEntity = themeCollectionEntity;
        this.conferenceEntity = conferenceEntity;
        this.editionEntity = editionEntity;
        this.publicationEntities = publicationEntities;
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

    public TypeCollectionEntity getTypeCollectionEntity() {
        return typeCollectionEntity;
    }

    public void setTypeCollectionEntity(TypeCollectionEntity typeCollectionEntity) {
        this.typeCollectionEntity = typeCollectionEntity;
    }

    public ThemeCollectionEntity getThemeCollectionEntity() {
        return themeCollectionEntity;
    }

    public void setThemeCollectionEntity(ThemeCollectionEntity themeCollectionEntity) {
        this.themeCollectionEntity = themeCollectionEntity;
    }

    public ConferenceEntity getConferenceEntity() {
        return conferenceEntity;
    }

    public void setConferenceEntity(ConferenceEntity conferenceEntity) {
        this.conferenceEntity = conferenceEntity;
    }

    public EditionEntity getEditionEntity() {
        return editionEntity;
    }

    public void setEditionEntity(EditionEntity editionEntity) {
        this.editionEntity = editionEntity;
    }

    public Set<PublicationEntity> getPublicationEntities() {
        return publicationEntities;
    }

    public void setPublicationEntities(Set<PublicationEntity> publicationEntities) {
        this.publicationEntities = publicationEntities;
    }

}
