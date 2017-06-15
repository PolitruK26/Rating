package com.rating.application.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patent")
public class PatentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_patent")
    private Integer id;

    @Column(name = "patent_Name")
    private String name;

    @Column(name = "patent_Number")
    private String number;

    @Column(name = "patent_Year")
    private Integer year;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_type_patent")
    private TypePatentEntity typePatentEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_registration_place")
    private RegistrationPlaceEntity registrationPlaceEntity;

    @OneToMany(mappedBy = "patentEntity", fetch = FetchType.EAGER)
    private Set<AuthorPatentEntity> authorPatentEntities = new HashSet<AuthorPatentEntity>();

    public PatentEntity() {
    }

    public PatentEntity(String name, String number, Integer year, TypePatentEntity typePatentEntity, RegistrationPlaceEntity registrationPlaceEntity, Set<AuthorPatentEntity> authorPatentEntities) {
        this.name = name;
        this.number = number;
        this.year = year;
        this.typePatentEntity = typePatentEntity;
        this.registrationPlaceEntity = registrationPlaceEntity;
        this.authorPatentEntities = authorPatentEntities;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public TypePatentEntity getTypePatentEntity() {
        return typePatentEntity;
    }

    public void setTypePatentEntity(TypePatentEntity typePatentEntity) {
        this.typePatentEntity = typePatentEntity;
    }

    public RegistrationPlaceEntity getRegistrationPlaceEntity() {
        return registrationPlaceEntity;
    }

    public void setRegistrationPlaceEntity(RegistrationPlaceEntity registrationPlaceEntity) {
        this.registrationPlaceEntity = registrationPlaceEntity;
    }

    public Set<AuthorPatentEntity> getAuthorPatentEntities() {
        return authorPatentEntities;
    }

    public void setAuthorPatentEntities(Set<AuthorPatentEntity> authorPatentEntities) {
        this.authorPatentEntities = authorPatentEntities;
    }

}
