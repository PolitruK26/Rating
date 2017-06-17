package com.rating.application.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "author")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_author")
    private Integer id;

    @Column(name = "author_Second_Name")
    private String secondName;

    @Column(name = "author_First_Name")
    private String firstName;

    @Column(name = "author_Middle_Name")
    private String middleName;

    @Column(name = "author_Phone")
    private String phone;

    @Column(name = "author_Email")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_position")
    private PositionAuthorEntity positionAuthorEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_power")
    private PowerAuthorEntity powerAuthorEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_rank")
    private RankAuthorEntity rankAuthorEntity;

    @OneToMany(mappedBy = "authorEntity", fetch = FetchType.EAGER)
    private Set<AuthorCertificateEntity> authorCertificateEntities = new HashSet<AuthorCertificateEntity>();

    @OneToMany(mappedBy = "authorEntity", fetch = FetchType.EAGER)
    private Set<AuthorPatentEntity> authorPatentEntities = new HashSet<AuthorPatentEntity>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "author_publication",
            joinColumns = {@JoinColumn(name = "ID_author")},
            inverseJoinColumns = {@JoinColumn(name = "ID_publication")})
    private Set<PublicationEntity> publicationEntities = new HashSet<PublicationEntity>();

    public AuthorEntity() {
    }

    public AuthorEntity(String secondName, String firstName, String middleName, String phone, String email, PositionAuthorEntity positionAuthorEntity, PowerAuthorEntity powerAuthorEntity, RankAuthorEntity rankAuthorEntity) {
        this.secondName = secondName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.phone = phone;
        this.email = email;
        this.positionAuthorEntity = positionAuthorEntity;
        this.powerAuthorEntity = powerAuthorEntity;
        this.rankAuthorEntity = rankAuthorEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PositionAuthorEntity getPositionAuthorEntity() {
        return positionAuthorEntity;
    }

    public void setPositionAuthorEntity(PositionAuthorEntity positionAuthorEntity) {
        this.positionAuthorEntity = positionAuthorEntity;
    }

    public PowerAuthorEntity getPowerAuthorEntity() {
        return powerAuthorEntity;
    }

    public void setPowerAuthorEntity(PowerAuthorEntity powerAuthorEntity) {
        this.powerAuthorEntity = powerAuthorEntity;
    }

    public RankAuthorEntity getRankAuthorEntity() {
        return rankAuthorEntity;
    }

    public void setRankAuthorEntity(RankAuthorEntity rankAuthorEntity) {
        this.rankAuthorEntity = rankAuthorEntity;
    }

    public Set<AuthorCertificateEntity> getAuthorCertificateEntities() {
        return authorCertificateEntities;
    }

    public void setAuthorCertificateEntities(Set<AuthorCertificateEntity> authorCertificateEntities) {
        this.authorCertificateEntities = authorCertificateEntities;
    }

    public Set<AuthorPatentEntity> getAuthorPatentEntities() {
        return authorPatentEntities;
    }

    public void setAuthorPatentEntities(Set<AuthorPatentEntity> authorPatentEntities) {
        this.authorPatentEntities = authorPatentEntities;
    }

    public Set<PublicationEntity> getPublicationEntities() {
        return publicationEntities;
    }

    public void setPublicationEntities(Set<PublicationEntity> publicationEntities) {
        this.publicationEntities = publicationEntities;
    }

}
