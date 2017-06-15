package com.rating.application.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "type_publication")
public class TypePublicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_type_publication")
    private Integer id;

    @Column(name = "type_publication_Name")
    private String name;

    @OneToMany(mappedBy = "typePublicationEntity", fetch = FetchType.EAGER)
    private Set<PublicationEntity> publicationEntities = new HashSet<PublicationEntity>();

    public TypePublicationEntity() {
    }

    public TypePublicationEntity(String name, Set<PublicationEntity> publicationEntities) {
        this.name = name;
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

    public Set<PublicationEntity> getPublicationEntities() {
        return publicationEntities;
    }

    public void setPublicationEntities(Set<PublicationEntity> publicationEntities) {
        this.publicationEntities = publicationEntities;
    }

}
