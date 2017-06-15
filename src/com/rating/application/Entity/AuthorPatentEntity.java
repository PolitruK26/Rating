package com.rating.application.Entity;

import javax.persistence.*;

@Entity
@Table(name = "author_patent")
public class AuthorPatentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_author_patent")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_author")
    private AuthorEntity authorEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_patent")
    private PatentEntity patentEntity;

    @Column(name = "author_patent_Rightholder")
    private Byte holder;

    public AuthorPatentEntity() {
    }

    public AuthorPatentEntity(AuthorEntity authorEntity, PatentEntity patentEntity, Byte holder) {
        this.authorEntity = authorEntity;
        this.patentEntity = patentEntity;
        this.holder = holder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuthorEntity getAuthorEntity() {
        return authorEntity;
    }

    public void setAuthorEntity(AuthorEntity authorEntity) {
        this.authorEntity = authorEntity;
    }

    public PatentEntity getPatentEntity() {
        return patentEntity;
    }

    public void setPatentEntity(PatentEntity patentEntity) {
        this.patentEntity = patentEntity;
    }

    public Byte getHolder() {
        return holder;
    }

    public void setHolder(Byte holder) {
        this.holder = holder;
    }

}
