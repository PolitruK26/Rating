package com.rating.application.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "registration_place")
public class RegistrationPlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_registration_place")
    private Integer id;

    @Column(name = "registration_place_Name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_city")
    private CityEntity cityEntity;

    @OneToMany(mappedBy = "registrationPlaceEntity", fetch = FetchType.EAGER)
    private Set<PatentEntity> patentEntities = new HashSet<PatentEntity>();

    public RegistrationPlaceEntity() {
    }

    public RegistrationPlaceEntity(String name, CityEntity cityEntity, Set<PatentEntity> patentEntities) {
        this.name = name;
        this.cityEntity = cityEntity;
        this.patentEntities = patentEntities;
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

    public Set<PatentEntity> getPatentEntities() {
        return patentEntities;
    }

    public void setPatentEntities(Set<PatentEntity> patentEntities) {
        this.patentEntities = patentEntities;
    }

}
