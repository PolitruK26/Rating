package com.rating.application.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "city")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_city")
    private Integer id;

    @Column(name = "city_Name")
    private String name;

    @OneToMany(mappedBy = "cityEntity", fetch = FetchType.EAGER)
    private Set<EditionEntity> editionEntities = new HashSet<EditionEntity>();

    @OneToMany(mappedBy = "cityEntity", fetch = FetchType.EAGER)
    private Set<RegistrationPlaceEntity> registrationPlaceEntities = new HashSet<RegistrationPlaceEntity>();

    public CityEntity() {
    }

    public CityEntity(String name, Set<EditionEntity> editionEntities, Set<RegistrationPlaceEntity> registrationPlaceEntities) {
        this.name = name;
        this.editionEntities = editionEntities;
        this.registrationPlaceEntities = registrationPlaceEntities;
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

    public Set<EditionEntity> getEditionEntities() {
        return editionEntities;
    }

    public void setEditionEntities(Set<EditionEntity> editionEntities) {
        this.editionEntities = editionEntities;
    }

    public Set<RegistrationPlaceEntity> getRegistrationPlaceEntities() {
        return registrationPlaceEntities;
    }

    public void setRegistrationPlaceEntities(Set<RegistrationPlaceEntity> registrationPlaceEntities) {
        this.registrationPlaceEntities = registrationPlaceEntities;
    }

}
