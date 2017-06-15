package com.rating.application.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rank_author")
public class RankAuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_rank")
    private Integer id;

    @Column(name = "rank_Name")
    private String name;

    @OneToMany(mappedBy = "rankAuthorEntity", fetch = FetchType.EAGER)
    private Set<AuthorEntity> authorEntities = new HashSet<AuthorEntity>();

    public RankAuthorEntity() {
    }

    public RankAuthorEntity(String name, Set<AuthorEntity> authorEntities) {
        this.name = name;
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

    public Set<AuthorEntity> getAuthorEntities() {
        return authorEntities;
    }

    public void setAuthorEntities(Set<AuthorEntity> authorEntities) {
        this.authorEntities = authorEntities;
    }

}
