package com.rating.application.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "edition")
public class EditionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_edition")
    private Integer id;

    @Column(name = "edition_Name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_city")
    private CityEntity cityEntity;

    @OneToMany(mappedBy = "editionEntity", fetch = FetchType.EAGER)
    private Set<CollectionEntity> collectionEntities = new HashSet<CollectionEntity>();

    public EditionEntity() {
    }

    public EditionEntity(String name, CityEntity cityEntity, Set<CollectionEntity> collectionEntities) {
        this.name = name;
        this.cityEntity = cityEntity;
        this.collectionEntities = collectionEntities;
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

    public CityEntity getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(CityEntity cityEntity) {
        this.cityEntity = cityEntity;
    }

    public Set<CollectionEntity> getCollectionEntities() {
        return collectionEntities;
    }

    public void setCollectionEntities(Set<CollectionEntity> collectionEntities) {
        this.collectionEntities = collectionEntities;
    }

}
