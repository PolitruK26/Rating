package com.rating.application.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "publication")
public class PublicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_publication")
    private Integer id;

    @Column(name = "publication_Name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_type_publication")
    private TypePublicationEntity typePublicationEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_collection")
    private CollectionEntity collectionEntity;

    @Column(name = "publication_Year")
    private Integer year;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Scientometric_Base")
    private ScientometricBaseEntity scientometricBaseEntity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "author_publication",
            joinColumns = {@JoinColumn(name = "ID_publication")},
            inverseJoinColumns = {@JoinColumn(name = "ID_author")})
    private Set<AuthorEntity> authorEntities = new HashSet<AuthorEntity>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "publication_keywords",
            joinColumns = {@JoinColumn(name = "ID_publication")},
            inverseJoinColumns = {@JoinColumn(name = "ID_keywords")})
    private Set<KeywordsEntity> keywordsEntities = new HashSet<KeywordsEntity>();

    public PublicationEntity() {
    }

    public PublicationEntity(String name, TypePublicationEntity typePublicationEntity, CollectionEntity collectionEntity, Integer year, ScientometricBaseEntity scientometricBaseEntity, Set<AuthorEntity> authorEntities, Set<KeywordsEntity> keywordsEntities) {
        this.name = name;
        this.typePublicationEntity = typePublicationEntity;
        this.collectionEntity = collectionEntity;
        this.year = year;
        this.scientometricBaseEntity = scientometricBaseEntity;
        this.authorEntities = authorEntities;
        this.keywordsEntities = keywordsEntities;
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

    public TypePublicationEntity getTypePublicationEntity() {
        return typePublicationEntity;
    }

    public void setTypePublicationEntity(TypePublicationEntity typePublicationEntity) {
        this.typePublicationEntity = typePublicationEntity;
    }

    public CollectionEntity getCollectionEntity() {
        return collectionEntity;
    }

    public void setCollectionEntity(CollectionEntity collectionEntity) {
        this.collectionEntity = collectionEntity;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public ScientometricBaseEntity getScientometricBaseEntity() {
        return scientometricBaseEntity;
    }

    public void setScientometricBaseEntity(ScientometricBaseEntity scientometricBaseEntity) {
        this.scientometricBaseEntity = scientometricBaseEntity;
    }

    public Set<AuthorEntity> getAuthorEntities() {
        return authorEntities;
    }

    public void setAuthorEntities(Set<AuthorEntity> authorEntities) {
        this.authorEntities = authorEntities;
    }

    public Set<KeywordsEntity> getKeywordsEntities() {
        return keywordsEntities;
    }

    public void setKeywordsEntities(Set<KeywordsEntity> keywordsEntities) {
        this.keywordsEntities = keywordsEntities;
    }

}
