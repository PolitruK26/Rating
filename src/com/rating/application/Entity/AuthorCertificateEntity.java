package com.rating.application.Entity;

import javax.persistence.*;

@Entity
@Table(name = "author_certificate")
public class AuthorCertificateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_author_certificate")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_author")
    private AuthorEntity authorEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_certificate")
    private CertificateEntity certificateEntity;

    @Column(name = "author_certificate_Rightholder")
    private Byte holder;

    public AuthorCertificateEntity() {
    }

    public AuthorCertificateEntity(AuthorEntity authorEntity, CertificateEntity certificateEntity, Byte holder) {
        this.authorEntity = authorEntity;
        this.certificateEntity = certificateEntity;
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

    public CertificateEntity getCertificateEntity() {
        return certificateEntity;
    }

    public void setCertificateEntity(CertificateEntity certificateEntity) {
        this.certificateEntity = certificateEntity;
    }

    public Byte getHolder() {
        return holder;
    }

    public void setHolder(Byte holder) {
        this.holder = holder;
    }

}
