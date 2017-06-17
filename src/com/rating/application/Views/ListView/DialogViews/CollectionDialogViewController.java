package com.rating.application.Views.ListView.DialogViews;

import com.rating.application.DAO.*;
import com.rating.application.Entity.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.Collection;
import java.util.Objects;
import java.util.ResourceBundle;

public class CollectionDialogViewController implements Initializable {

    @FXML
    private Button accept;

    @FXML
    private Button cancel;

    @FXML
    private TextField name;

    @FXML
    private ComboBox<TypeCollectionEntity> type;

    @FXML
    private ComboBox<ThemeCollectionEntity> theme;

    @FXML
    private ComboBox<EditionEntity> edition;

    @FXML
    private ComboBox<ConferenceEntity> conference;

    @FXML
    private Label title;

    private Boolean isEdit = false;

    private Integer id;

    private double initialX;
    private double initialY;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        title.setOnMousePressed(
                event -> {

                    if (event.getButton() != MouseButton.MIDDLE) {

                        initialX = event.getSceneX();
                        initialY = event.getSceneY();

                    }

                });

        title.setOnMouseDragged(
                event -> {

                    if (event.getButton() != MouseButton.MIDDLE) {

                        title.getScene().getWindow().setX(event.getScreenX() - initialX);
                        title.getScene().getWindow().setY(event.getScreenY() - initialY);

                    }

                });

        Collection<TypeCollectionEntity> typeCollectionEntities = FXCollections.observableArrayList(new TypeCollectionDAO().getTypes());
        type.getItems().addAll(typeCollectionEntities);
        type.setConverter(new StringConverter<TypeCollectionEntity>() {
            @Override
            public String toString(TypeCollectionEntity object) {
                if (object != null)
                    return object.getName();
                else
                    return null;
            }

            @Override
            public TypeCollectionEntity fromString(String string) {
                TypeCollectionEntity entity = new TypeCollectionEntity();
                for (TypeCollectionEntity typeCollectionEntity : typeCollectionEntities) {
                    if (Objects.equals(typeCollectionEntity.getName(), type.getEditor().getText()))
                        entity = typeCollectionEntity;
                }
                return entity;
            }
        });

        Collection<ThemeCollectionEntity> themeCollectionEntities = FXCollections.observableArrayList(new ThemeCollectionDAO().getThemes());
        theme.getItems().addAll(themeCollectionEntities);
        theme.setConverter(new StringConverter<ThemeCollectionEntity>() {
            @Override
            public String toString(ThemeCollectionEntity object) {
                if (object != null)
                    return object.getName();
                else
                    return null;
            }

            @Override
            public ThemeCollectionEntity fromString(String string) {
                ThemeCollectionEntity entity = new ThemeCollectionEntity();
                for (ThemeCollectionEntity themeCollectionEntity : themeCollectionEntities) {
                    if (Objects.equals(themeCollectionEntity.getName(), theme.getEditor().getText()))
                        entity = themeCollectionEntity;
                }
                return entity;
            }
        });

        Collection<EditionEntity> editionEntities = FXCollections.observableArrayList(new EditionDAO().getEditions());
        edition.getItems().addAll(editionEntities);
        edition.setConverter(new StringConverter<EditionEntity>() {
            @Override
            public String toString(EditionEntity object) {
                if (object != null)
                    return object.getName();
                else
                    return null;
            }

            @Override
            public EditionEntity fromString(String string) {
                EditionEntity entity = new EditionEntity();
                for (EditionEntity editionEntity : editionEntities) {
                    if (Objects.equals(editionEntity.getName(), edition.getEditor().getText()))
                        entity = editionEntity;
                }
                return entity;
            }
        });

        Collection<ConferenceEntity> conferenceEntities = FXCollections.observableArrayList(new ConferenceDAO().getConferences());
        conference.getItems().addAll(conferenceEntities);
        conference.setConverter(new StringConverter<ConferenceEntity>() {
            @Override
            public String toString(ConferenceEntity object) {
                if (object != null)
                    return object.getName();
                else
                    return null;
            }

            @Override
            public ConferenceEntity fromString(String string) {
                ConferenceEntity entity = new ConferenceEntity();
                for (ConferenceEntity conferenceEntity : conferenceEntities) {
                    if (Objects.equals(conferenceEntity.getName(), conference.getEditor().getText()))
                        entity = conferenceEntity;
                }
                return entity;
            }
        });

        accept.setOnMouseClicked(event -> {

            CollectionDAO collectionDAO = new CollectionDAO();
            if (!isEdit)
                collectionDAO.addCollection(new CollectionEntity(
                        name.getText(),
                        type.getValue(),
                        theme.getValue(),
                        conference.getValue(),
                        edition.getValue()
                ));
            else {
                CollectionEntity collectionEntity = new CollectionEntity();
                collectionEntity.setId(this.id);
                collectionEntity.setName(name.getText());
                collectionEntity.setTypeCollectionEntity(type.getValue());
                collectionEntity.setThemeCollectionEntity(theme.getValue());
                collectionEntity.setConferenceEntity(conference.getValue());
                collectionEntity.setEditionEntity(edition.getValue());
                collectionDAO.updateCollection(collectionEntity);
            }

            Stage stage = (Stage) accept.getScene().getWindow();
            stage.close();
        });

        cancel.setOnMouseClicked(event -> {
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        });

    }

    public void setEdit(CollectionEntity collectionEntity) {
        title.setText("Изменить сборник");
        this.id = collectionEntity.getId();
        name.setText(collectionEntity.getName());
        type.getSelectionModel().select(collectionEntity.getTypeCollectionEntity());
        theme.getSelectionModel().select(collectionEntity.getThemeCollectionEntity());
        edition.getSelectionModel().select(collectionEntity.getEditionEntity());
        conference.getSelectionModel().select(collectionEntity.getConferenceEntity());
        isEdit = true;
    }

}
