package com.rating.application.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "position_author")
public class PositionAuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_position")
    private Integer id;

    @Column(name = "position_Name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_department")
    private DepartmentEntity departmentEntity;

    @OneToMany(mappedBy = "positionAuthorEntity", fetch = FetchType.EAGER)
    private Set<AuthorEntity> authorEntities = new HashSet<AuthorEntity>();

    public PositionAuthorEntity() {
    }

    public PositionAuthorEntity(String name, DepartmentEntity departmentEntity, Set<AuthorEntity> authorEntities) {
        this.name = name;
        this.departmentEntity = departmentEntity;
        this.authorEntities = authorEntities;
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

    public DepartmentEntity getDepartmentEntity() {
        return departmentEntity;
    }

    public void setDepartmentEntity(DepartmentEntity departmentEntity) {
        this.departmentEntity = departmentEntity;
    }

    public Set<AuthorEntity> getAuthorEntities() {
        return authorEntities;
    }

    public void setAuthorEntities(Set<AuthorEntity> authorEntities) {
        this.authorEntities = authorEntities;
    }

}
