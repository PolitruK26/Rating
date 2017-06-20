package com.rating.application.Views.ListView;

import com.rating.application.DAO.CollectionDAO;
import com.rating.application.DAO.PublicationDAO;
import com.rating.application.DAO.ScientometricBaseDAO;
import com.rating.application.DAO.TypePublicationDAO;
import com.rating.application.Entity.*;
import com.rating.application.Views.ListView.DialogViews.PublicationDialogViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import org.controlsfx.control.textfield.TextFields;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Objects;
import java.util.ResourceBundle;

public class PublicationViewController implements Initializable {

    @FXML
    private AnchorPane window;

    @FXML
    private Button buttonAdd;

    @FXML
    private ListView<PublicationEntity> listView;

    @FXML
    private ComboBox<TypePublicationEntity> type;

    @FXML
    private ComboBox<CollectionEntity> collection;

    @FXML
    private ComboBox<Integer> year;

    @FXML
    private ComboBox<ScientometricBaseEntity> base;

    @FXML
    private Button clear;

    private TextField search;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        clear.setOnAction(event -> {
            type.getSelectionModel().clearSelection();
            collection.getSelectionModel().clearSelection();
            year.getSelectionModel().clearSelection();
            base.getSelectionModel().clearSelection();
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
        type.valueProperty().addListener((observable, oldValue, newValue) -> {
            PublicationDAO publicationDAO = new PublicationDAO();
            ObservableList<PublicationEntity> observableList = FXCollections.observableArrayList();
            observableList.clear();
            observableList.addAll(publicationDAO.getFiltredPublications(search.getText(), type.getValue(), collection.getValue(), year.getValue(), base.getValue()));
            listView.getItems().clear();
            listView.setItems(observableList);
            listView.setCellFactory(list -> new PublicationCell());
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
        collection.valueProperty().addListener((observable, oldValue, newValue) -> {
            PublicationDAO publicationDAO = new PublicationDAO();
            ObservableList<PublicationEntity> observableList = FXCollections.observableArrayList();
            observableList.clear();
            observableList.addAll(publicationDAO.getFiltredPublications(search.getText(), type.getValue(), collection.getValue(), year.getValue(), base.getValue()));
            listView.getItems().clear();
            listView.setItems(observableList);
            listView.setCellFactory(list -> new PublicationCell());
        });

        for (int i = 1980; i <= 2070; i++)
            year.getItems().add(i);
        year.valueProperty().addListener((observable, oldValue, newValue) -> {
            PublicationDAO publicationDAO = new PublicationDAO();
            ObservableList<PublicationEntity> observableList = FXCollections.observableArrayList();
            observableList.clear();
            observableList.addAll(publicationDAO.getFiltredPublications(search.getText(), type.getValue(), collection.getValue(), year.getValue(), base.getValue()));
            listView.getItems().clear();
            listView.setItems(observableList);
            listView.setCellFactory(list -> new PublicationCell());
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
        base.valueProperty().addListener((observable, oldValue, newValue) -> {
            PublicationDAO publicationDAO = new PublicationDAO();
            ObservableList<PublicationEntity> observableList = FXCollections.observableArrayList();
            observableList.clear();
            observableList.addAll(publicationDAO.getFiltredPublications(search.getText(), type.getValue(), collection.getValue(), year.getValue(), base.getValue()));
            listView.getItems().clear();
            listView.setItems(observableList);
            listView.setCellFactory(list -> new PublicationCell());
        });

        search = TextFields.createClearableTextField();
        window.getChildren().add(search);
        search.setPrefHeight(30);
        search.setFocusTraversable(false);
        AnchorPane.setTopAnchor(search, 5.0);
        AnchorPane.setRightAnchor(search, 10.0);
        AnchorPane.setLeftAnchor(search, 10.0);
        search.setPromptText("Поиск по названию");
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            PublicationDAO publicationDAO = new PublicationDAO();
            ObservableList<PublicationEntity> observableList = FXCollections.observableArrayList();
            observableList.clear();
            observableList.addAll(publicationDAO.getFiltredPublications(search.getText(), type.getValue(), collection.getValue(), year.getValue(), base.getValue()));
            listView.getItems().clear();
            listView.setItems(observableList);
            listView.setCellFactory(list -> new PublicationCell());
        });

        initData();

        listView.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.F5)
                initData();
        });

        buttonAdd.setOnMouseClicked(event -> {
            try {
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("DialogViews/publicationDialogView.fxml"));
                primaryStage.initOwner(buttonAdd.getScene().getWindow());
                primaryStage.initModality(Modality.APPLICATION_MODAL);
                primaryStage.initStyle(StageStyle.UNDECORATED);
                Rectangle rectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getBounds();
                primaryStage.setX(rectangle.getWidth() / 2 - 350);
                primaryStage.setY(rectangle.getHeight() / 2 - 200);
                primaryStage.setTitle("Добавить публикацию");
                primaryStage.setScene(new Scene(root));
                primaryStage.showAndWait();
                initData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private void initData() {
        PublicationDAO publicationDAO = new PublicationDAO();
        ObservableList<PublicationEntity> observableList = FXCollections.observableArrayList();
        observableList.clear();
        observableList.addAll(publicationDAO.getPublications());
        listView.setItems(observableList);
        listView.setCellFactory(list -> new PublicationCell());
    }


    private class PublicationCell extends ListCell<PublicationEntity> {

        @Override
        public void updateItem(PublicationEntity item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {

                HBox top = new HBox();
                top.setSpacing(5);

                Label year = new Label(item.getYear().toString());
                year.getStyleClass().add("label-tag");

                top.getChildren().add(year);

                for (KeywordsEntity keywordsEntity : item.getKeywordsEntities()) {
                    Label label = new Label(keywordsEntity.getName());
                    label.getStyleClass().add("label-tags");
                    top.getChildren().add(label);
                }

                Label name = new Label(item.getTypePublicationEntity().getName() + " :: " + item.getName());
                name.getStyleClass().add("label-big");

                HBox authors = new HBox();
                authors.setSpacing(5);

                for (AuthorEntity authorEntity : item.getAuthorEntities()) {
                    Label label = new Label(authorEntity.getSecondName() + ' '
                            + authorEntity.getFirstName() + '.'
                            + authorEntity.getMiddleName() + '.');
                    label.getStyleClass().add("label-italic");
                    authors.getChildren().add(label);
                }

                VBox center = new VBox(
                        top,
                        name,
                        authors,
                        new Label("Опубликованно в сборнике \"" + item.getCollectionEntity().getName() + "\""),
                        new Label("Публикация размещена в " + item.getScientometricBaseEntity().getName())
                );
                name.prefWidthProperty().bind(center.widthProperty());

                Button edit = new Button();
                edit.getStyleClass().add("button-edit");
                edit.setOnMouseClicked(event -> {
                    try {
                        Stage primaryStage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogViews/publicationDialogView.fxml"));
                        Parent root = fxmlLoader.load();
                        fxmlLoader.<PublicationDialogViewController>getController().setEdit(item);
                        primaryStage.initOwner(getScene().getWindow());
                        primaryStage.initModality(Modality.APPLICATION_MODAL);
                        primaryStage.initStyle(StageStyle.UNDECORATED);
                        Rectangle rectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getBounds();
                        primaryStage.setX(rectangle.getWidth() / 2 - 350);
                        primaryStage.setY(rectangle.getHeight() / 2 - 200);
                        primaryStage.setScene(new Scene(root));
                        primaryStage.showAndWait();
                        initData();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                Button delete = new Button();
                delete.getStyleClass().add("button-delete");
                delete.setOnMouseClicked(event -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(delete.getScene().getWindow());
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.initStyle(StageStyle.UNDECORATED);
                    alert.setTitle(null);
                    alert.setHeaderText("Требуется подтверждение операции");
                    alert.setContentText("Вы действительно хотите удалить запись?");
                    ButtonType yes = new ButtonType("Да");
                    ButtonType no = new ButtonType("Нет");
                    alert.getButtonTypes().setAll(yes, no);
                    alert.showAndWait();
                    if (alert.getResult() == yes) {
                        PublicationDAO publicationDAO = new PublicationDAO();
                        publicationDAO.deletePublication(item);
                        initData();
                    }
                });

                HBox buttons = new HBox(edit, delete);
                buttons.setAlignment(Pos.CENTER);
                buttons.setPrefWidth(100);
                buttons.setSpacing(5);

                BorderPane borderPane = new BorderPane();
                borderPane.setCenter(center);
                borderPane.setRight(buttons);
                BorderPane.setMargin(center, new Insets(5));
                borderPane.getStyleClass().add("background-list");
                setGraphic(borderPane);
            }
        }

    }
}
