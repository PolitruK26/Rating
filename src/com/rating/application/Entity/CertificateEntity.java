package com.rating.application.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "certificate")
public class CertificateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_certificate")
    private Integer id;

    @Column(name = "certificate_Name")
    private String name;

    @Column(name = "certificate_Number")
    private String number;

    @Column(name = "certificate_Year")
    private Integer year;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_type_certificate")
    private TypeCertificateEntity typeCertificateEntity;

    @OneToMany(mappedBy = "certificateEntity", fetch = FetchType.EAGER)
    private Set<AuthorCertificateEntity> authorCertificateEntities = new HashSet<AuthorCertificateEntity>();

    public CertificateEntity() {
    }

    public CertificateEntity(String name, String number, Integer year, TypeCertificateEntity typeCertificateEntity, Set<AuthorCertificateEntity> authorCertificateEntities) {
        this.name = name;
        this.number = number;
        this.year = year;
        this.typeCertificateEntity = typeCertificateEntity;
        this.authorCertificateEntities = authorCertificateEntities;
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

    public TypeCertificateEntity getTypeCertificateEntity() {
        return typeCertificateEntity;
    }

    public void setTypeCertificateEntity(TypeCertificateEntity typeCertificateEntity) {
        this.typeCertificateEntity = typeCertificateEntity;
    }

    public Set<AuthorCertificateEntity> getAuthorCertificateEntities() {
        return authorCertificateEntities;
    }

    public void setAuthorCertificateEntities(Set<AuthorCertificateEntity> authorCertificateEntities) {
        this.authorCertificateEntities = authorCertificateEntities;
    }

}
