package com.rating.application.Views;

import com.jfoenix.controls.JFXMasonryPane;
import com.rating.application.DAO.AuthorDAO;
import com.rating.application.Entity.AuthorCertificateEntity;
import com.rating.application.Entity.AuthorEntity;
import com.rating.application.Entity.AuthorPatentEntity;
import com.rating.application.Entity.PublicationEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.text.TextAlignment;
import org.controlsfx.control.PopOver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class RatingViewController implements Initializable {

    @FXML
    private JFXMasonryPane content;

    private Properties properties;
    private File config;

    private Integer levelInternational;
    private Integer levelAllRussian;
    private Integer levelRegionalBig;
    private Integer levelRegionalLittle;
    private Integer levelMunicipal;
    private Integer sbRINC;
    private Integer sbVAK;
    private Integer sbScopus;
    private Integer sbWeb;
    private Integer tpAbstracts;
    private Integer tpArticle;
    private Integer tpMonograph;
    private Integer tpDevelopments;
    private Integer tpTutorial;
    private Integer tpDepositing;
    private Integer tpInvention;
    private Integer tpSample;
    private Integer tcModel;
    private Integer tcDB;
    private Integer tcComputer;

    private Integer rating;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        config = new File("config.properties");
        properties = new Properties();
        try {
            properties.load(new FileInputStream(config));
        } catch (IOException e) {
            e.printStackTrace();
        }

        rating = new Integer(0);

        levelInternational = Integer.valueOf(properties.getProperty("rating.level.international"));
        levelAllRussian = Integer.valueOf(properties.getProperty("rating.level.allrussian"));
        levelRegionalBig = Integer.valueOf(properties.getProperty("rating.level.regionalbig"));
        levelRegionalLittle = Integer.valueOf(properties.getProperty("rating.level.regionallittle"));
        levelMunicipal = Integer.valueOf(properties.getProperty("rating.level.municipal"));
        sbRINC = Integer.valueOf(properties.getProperty("rating.sb.rinc"));
        sbVAK = Integer.valueOf(properties.getProperty("rating.sb.vak"));
        sbScopus = Integer.valueOf(properties.getProperty("rating.sb.scopus"));
        sbWeb = Integer.valueOf(properties.getProperty("rating.sb.web"));
        tpAbstracts = Integer.valueOf(properties.getProperty("rating.tp.abstracts"));
        tpArticle = Integer.valueOf(properties.getProperty("rating.tp.article"));
        tpMonograph = Integer.valueOf(properties.getProperty("rating.tp.monograph"));
        tpDevelopments = Integer.valueOf(properties.getProperty("rating.tp.developments"));
        tpTutorial = Integer.valueOf(properties.getProperty("rating.tp.tutorial"));
        tpDepositing = Integer.valueOf(properties.getProperty("rating.tp.depositing"));
        tpInvention = Integer.valueOf(properties.getProperty("rating.tp.invention"));
        tpSample = Integer.valueOf(properties.getProperty("rating.tp.sample"));
        tcModel = Integer.valueOf(properties.getProperty("rating.tc.model"));
        tcDB = Integer.valueOf(properties.getProperty("rating.tc.db"));
        tcComputer = Integer.valueOf(properties.getProperty("rating.tc.computer"));

        AuthorDAO authorDAO = new AuthorDAO();
        ObservableList<AuthorEntity> authorEntities = FXCollections.observableArrayList(authorDAO.getAuthors());

        for (AuthorEntity authorEntity : authorEntities) {

            rating = 0;

            Tooltip tooltip = new Tooltip(
                    authorEntity.getPositionAuthorEntity().getName()
            );

            for (PublicationEntity publicationEntity : authorEntity.getPublicationEntities()) {

                switch (publicationEntity.getCollectionEntity().getConferenceEntity().getLevelConferenceEntity().getName()) {
                    case "Всероссийская":
                        rating += levelAllRussian;
                        break;
                    case "Международная":
                        rating += levelInternational;
                        break;
                    case "Региональная":
                        rating += levelRegionalBig;
                        break;
                    case "Муниципальная":
                        rating += levelMunicipal;
                        break;
                    case "Областная":
                        rating += levelRegionalLittle;
                        break;
                }

                switch (publicationEntity.getScientometricBaseEntity().getName()) {
                    case "РИНЦ":
                        rating += sbRINC;
                        break;
                    case "Scopus":
                        rating += sbScopus;
                        break;
                    case "Web of Sience":
                        rating += sbWeb;
                        break;
                    case "ВАК":
                        rating += sbVAK;
                        break;
                }

                switch (publicationEntity.getTypePublicationEntity().getName()) {
                    case "Тезисы докладов и выступлений":
                        rating += tpAbstracts;
                        break;
                    case "Научная статья":
                        rating += tpArticle;
                        break;
                    case "Монография":
                        rating += tpMonograph;
                        break;
                    case "Методические разработки":
                        rating += tpDevelopments;
                        break;
                    case "Учебное пособие":
                        rating += tpTutorial;
                        break;
                    case "Депонирование":
                        rating += tpDepositing;
                        break;
                }

            }

            for (AuthorPatentEntity authorPatentEntity : authorEntity.getAuthorPatentEntities()) {
                switch (authorPatentEntity.getPatentEntity().getTypePatentEntity().getName()) {
                    case "На изобретение":
                        rating += tpInvention;
                        break;
                    case "На промышленный образец":
                        rating += tpSample;
                        break;
                }
            }

            for (AuthorCertificateEntity authorCertificateEntity : authorEntity.getAuthorCertificateEntities()) {
                switch (authorCertificateEntity.getCertificateEntity().getTypeCertificateEntity().getName()) {
                    case "На полезную модель":
                        rating += tcModel;
                        break;
                    case "О регистрации базы данных":
                        rating += tcDB;
                        break;
                    case "О регистрации программы для ЭВМ":
                        rating += tcComputer;
                        break;
                }
            }

            PopOver popOver = new PopOver(
                    new Label(
                            authorEntity.getPositionAuthorEntity().getName() + '\n'
                                    + authorEntity.getEmail() + '\n'
                                    + authorEntity.getPhone() + '\n'
                                    + authorEntity.getPowerAuthorEntity().getName() + '\n'
                                    + authorEntity.getRankAuthorEntity().getName()
                    )
            );
            popOver.setArrowLocation(PopOver.ArrowLocation.LEFT_CENTER);

            Label label = new Label(
                    authorEntity.getSecondName() + ' '
                            + authorEntity.getFirstName() + '.'
                            + authorEntity.getMiddleName() + ".\n"
                            + rating + " баллов");
            label.getStyleClass().add("label-main");
            label.setPrefSize(75, 75);
            label.setAlignment(Pos.CENTER);
            label.setTextAlignment(TextAlignment.CENTER);
            label.setOnMouseEntered(event -> popOver.show(label));
            label.setOnMouseMoved(event -> {
                if (!popOver.isShowing()) popOver.show(label);
            });
            label.setOnMouseExited(event -> popOver.hide());

            content.getChildren().add(label);
        }

    }
}
