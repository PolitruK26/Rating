package com.rating.application.Views.ListView.DialogViews;

import com.rating.application.DAO.*;
import com.rating.application.Entity.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.controlsfx.control.ListSelectionView;

import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.ResourceBundle;

public class PublicationDialogViewController implements Initializable {

    @FXML
    private Label title;

    @FXML
    private Button cancel;

    @FXML
    private Button accept;

    @FXML
    private TextField name;

    @FXML
    private ComboBox<TypePublicationEntity> type;

    @FXML
    private ComboBox<CollectionEntity> collection;

    @FXML
    private TextField year;

    @FXML
    private ComboBox<ScientometricBaseEntity> base;

    @FXML
    private ListSelectionView<AuthorEntity> authors;

    @FXML
    private ListSelectionView<KeywordsEntity> keywords;

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

        Collection<TypePublicationEntity> typePublicationEntities = FXCollections.observableArrayList(new TypePublicationDAO().getTypes());
        type.getItems().addAll(typePublicationEntities);
        type.setConverter(new StringConverter<TypePublicationEntity>() {
            @Override
            public String toString(TypePublicationEntity object) {
                if (object != null)
                    return object.getName();
                else
                    return null;
            }

            @Override
            public TypePublicationEntity fromString(String string) {
                TypePublicationEntity entity = new TypePublicationEntity();
                for (TypePublicationEntity typePublicationEntity : typePublicationEntities) {
                    if (Objects.equals(typePublicationEntity.getName(), type.getEditor().getText()))
                        entity = typePublicationEntity;
                }
                return entity;
            }
        });

        Collection<CollectionEntity> collectionEntities = FXCollections.observableArrayList(new CollectionDAO().getCollections());
        collection.getItems().addAll(collectionEntities);
        collection.setConverter(new StringConverter<CollectionEntity>() {
            @Override
            public String toString(CollectionEntity object) {
                if (object != null)
                    return object.getName();
                else
                    return null;
            }

            @Override
            public CollectionEntity fromString(String string) {
                CollectionEntity entity = new CollectionEntity();
                for (CollectionEntity collectionEntity : collectionEntities) {
                    if (Objects.equals(collectionEntity.getName(), collection.getEditor().getText()))
                        entity = collectionEntity;
                }
                return entity;
            }
        });

        Collection<ScientometricBaseEntity> scientometricBaseEntities = FXCollections.observableArrayList(new ScientometricBaseDAO().getBases());
        base.getItems().addAll(scientometricBaseEntities);
        base.setConverter(new StringConverter<ScientometricBaseEntity>() {
            @Override
            public String toString(ScientometricBaseEntity object) {
                if (object != null)
                    return object.getName();
                else
                    return null;
            }

            @Override
            public ScientometricBaseEntity fromString(String string) {
                ScientometricBaseEntity entity = new ScientometricBaseEntity();
                for (ScientometricBaseEntity scientometricBaseEntity : scientometricBaseEntities) {
                    if (Objects.equals(scientometricBaseEntity.getName(), base.getEditor().getText()))
                        entity = scientometricBaseEntity;
                }
                return entity;
            }
        });

        Collection<AuthorEntity> authorEntities = FXCollections.observableArrayList(new AuthorDAO().getAuthors());
        authors.getSourceItems().addAll(authorEntities);
        authors.setCellFactory(list -> new AuthorCell());
        authors.setSourceHeader(new Label("Имеющиеся авторы"));
        authors.setTargetHeader(new Label("Выбранные авторы"));

        Collection<KeywordsEntity> keywordsEntities = FXCollections.observableArrayList(new KeyWordsDAO().getKeyWords());
        keywords.getSourceItems().addAll(keywordsEntities);
        keywords.setCellFactory(list -> new KeyWordsCell());
        keywords.setSourceHeader(new Label("Имеющиеся ключевые слова"));
        keywords.setTargetHeader(new Label("Выбранные ключевые слова"));

        accept.setOnMouseClicked(event -> {

            PublicationDAO publicationDAO = new PublicationDAO();
            if (!isEdit)
                publicationDAO.addPublication(new PublicationEntity(
                        name.getText(),
                        type.getValue(),
                        collection.getValue(),
                        Integer.valueOf(year.getText()),
                        base.getValue(),
                        new HashSet<AuthorEntity>(authors.getTargetItems()),
                        new HashSet<KeywordsEntity>(keywords.getTargetItems())
                ));
            else {
                PublicationEntity publicationEntity = new PublicationEntity();
                publicationEntity.setId(this.id);
                publicationEntity.setName(name.getText());
                publicationEntity.setTypePublicationEntity(type.getValue());
                publicationEntity.setCollectionEntity(collection.getValue());
                publicationEntity.setYear(Integer.valueOf(year.getText()));
                publicationEntity.setScientometricBaseEntity(base.getValue());
                publicationEntity.setAuthorEntities(new HashSet<AuthorEntity>(authors.getTargetItems()));
                publicationEntity.setKeywordsEntities(new HashSet<KeywordsEntity>(keywords.getTargetItems()));
                publicationDAO.updatePublication(publicationEntity);
            }

            Stage stage = (Stage) accept.getScene().getWindow();
            stage.close();
        });

        cancel.setOnMouseClicked(event -> {
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        });

    }

    public void setEdit(PublicationEntity publicationEntity) {
        title.setText("Изменить публикацию");
        this.id = publicationEntity.getId();
        name.setText(publicationEntity.getName());
        type.getSelectionModel().select(publicationEntity.getTypePublicationEntity());
        collection.getSelectionModel().select(publicationEntity.getCollectionEntity());
        year.setText(publicationEntity.getYear().toString());
        base.getSelectionModel().select(publicationEntity.getScientometricBaseEntity());
        authors.getTargetItems().addAll(publicationEntity.getAuthorEntities());
        keywords.getTargetItems().addAll(publicationEntity.getKeywordsEntities());
        isEdit = true;
    }

    private class AuthorCell extends ListCell<AuthorEntity> {

        @Override
        protected void updateItem(AuthorEntity item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null)
                setText(item.getSecondName() + ' ' + item.getFirstName() + '.' + item.getMiddleName() + '.');
        }
    }

    private class KeyWordsCell extends ListCell<KeywordsEntity> {

        @Override
        protected void updateItem(KeywordsEntity item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null)
                setText(item.getName());
        }
    }

}
