package com.rating.application.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "type_patent")
public class TypePatentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_type_patent")
    private Integer id;

    @Column(name = "type_patent_Name")
    private String name;

    @OneToMany(mappedBy = "typePatentEntity", fetch = FetchType.EAGER)
    private Set<PatentEntity> patentEntities = new HashSet<PatentEntity>();

    public TypePatentEntity() {
    }

    public TypePatentEntity(String name, Set<PatentEntity> patentEntities) {
        this.name = name;
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

    public Set<PatentEntity> getPatentEntities() {
        return patentEntities;
    }

    public void setPatentEntities(Set<PatentEntity> patentEntities) {
        this.patentEntities = patentEntities;
    }

}
