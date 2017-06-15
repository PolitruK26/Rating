package com.rating.application.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "type_certificate")
public class TypeCertificateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_type_certificate")
    private Integer id;

    @Column(name = "type_certificate_Name")
    private String name;

    @OneToMany(mappedBy = "typeCertificateEntity", fetch = FetchType.EAGER)
    private Set<CertificateEntity> certificateEntities = new HashSet<CertificateEntity>();

    public TypeCertificateEntity() {
    }

    public TypeCertificateEntity(String name, Set<CertificateEntity> certificateEntities) {
        this.name = name;
        this.certificateEntities = certificateEntities;
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

    public Set<CertificateEntity> getCertificateEntities() {
        return certificateEntities;
    }

    public void setCertificateEntities(Set<CertificateEntity> certificateEntities) {
        this.certificateEntities = certificateEntities;
    }

}
