package com.rating.application.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "keywords")
public class KeywordsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_keywords")
    private Integer id;

    @Column(name = "keywords_Name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "publication_keywords",
            joinColumns = {@JoinColumn(name = "ID_keywords")},
            inverseJoinColumns = {@JoinColumn(name = "ID_publication")})
    private Set<PublicationEntity> publicationEntities = new HashSet<PublicationEntity>();

    public KeywordsEntity() {
    }

    public KeywordsEntity(String name, Set<PublicationEntity> publicationEntities) {
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
