package com.rating.application.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "department")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_department")
    private Integer id;

    @Column(name = "department_Name")
    private String name;

    @OneToMany(mappedBy = "departmentEntity", fetch = FetchType.EAGER)
    private Set<PositionAuthorEntity> positionAuthorEntities = new HashSet<PositionAuthorEntity>();

    public DepartmentEntity() {
    }

    public DepartmentEntity(String name, Set<PositionAuthorEntity> positionAuthorEntities) {
        this.name = name;
        this.positionAuthorEntities = positionAuthorEntities;
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

    public Set<PositionAuthorEntity> getPositionAuthorEntities() {
        return positionAuthorEntities;
    }

    public void setPositionAuthorEntities(Set<PositionAuthorEntity> positionAuthorEntities) {
        this.positionAuthorEntities = positionAuthorEntities;
    }

}
