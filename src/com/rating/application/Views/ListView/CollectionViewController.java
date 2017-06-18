package com.rating.application.Views.ListView;

import com.rating.application.DAO.*;
import com.rating.application.Entity.*;
import com.rating.application.Views.ListView.DialogViews.CollectionDialogViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
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

public class CollectionViewController implements Initializable {

    @FXML
    private AnchorPane window;

    @FXML
    private Button buttonAdd;

    @FXML
    private ListView<CollectionEntity> listView;

    @FXML
    private ComboBox<TypeCollectionEntity> type;

    @FXML
    private ComboBox<ThemeCollectionEntity> theme;

    @FXML
    private ComboBox<ConferenceEntity> conference;

    @FXML
    private ComboBox<EditionEntity> edition;

    @FXML
    private Button clear;

    private TextField search;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        clear.setOnAction(event -> {
            type.getSelectionModel().clearSelection();
            theme.getSelectionModel().clearSelection();
            conference.getSelectionModel().clearSelection();
            edition.getSelectionModel().clearSelection();
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
        type.valueProperty().addListener((observable, oldValue, newValue) -> {
            CollectionDAO collectionDAO = new CollectionDAO();
            ObservableList<CollectionEntity> observableList = FXCollections.observableArrayList();
            observableList.clear();
            observableList.addAll(collectionDAO.getFiltredCollections(search.getText(), type.getValue(), theme.getValue(), conference.getValue(), edition.getValue()));
            listView.getItems().clear();
            listView.setItems(observableList);
            listView.setCellFactory(list -> new CollectionCell());
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
        theme.valueProperty().addListener((observable, oldValue, newValue) -> {
            CollectionDAO collectionDAO = new CollectionDAO();
            ObservableList<CollectionEntity> observableList = FXCollections.observableArrayList();
            observableList.clear();
            observableList.addAll(collectionDAO.getFiltredCollections(search.getText(), type.getValue(), theme.getValue(), conference.getValue(), edition.getValue()));
            listView.getItems().clear();
            listView.setItems(observableList);
            listView.setCellFactory(list -> new CollectionCell());
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
        edition.valueProperty().addListener((observable, oldValue, newValue) -> {
            CollectionDAO collectionDAO = new CollectionDAO();
            ObservableList<CollectionEntity> observableList = FXCollections.observableArrayList();
            observableList.clear();
            observableList.addAll(collectionDAO.getFiltredCollections(search.getText(), type.getValue(), theme.getValue(), conference.getValue(), edition.getValue()));
            listView.getItems().clear();
            listView.setItems(observableList);
            listView.setCellFactory(list -> new CollectionCell());
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
        conference.valueProperty().addListener((observable, oldValue, newValue) -> {
            CollectionDAO collectionDAO = new CollectionDAO();
            ObservableList<CollectionEntity> observableList = FXCollections.observableArrayList();
            observableList.clear();
            observableList.addAll(collectionDAO.getFiltredCollections(search.getText(), type.getValue(), theme.getValue(), conference.getValue(), edition.getValue()));
            listView.getItems().clear();
            listView.setItems(observableList);
            listView.setCellFactory(list -> new CollectionCell());
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
            CollectionDAO collectionDAO = new CollectionDAO();
            ObservableList<CollectionEntity> observableList = FXCollections.observableArrayList();
            observableList.clear();
            observableList.addAll(collectionDAO.getFiltredCollections(search.getText(), type.getValue(), theme.getValue(), conference.getValue(), edition.getValue()));
            listView.getItems().clear();
            listView.setItems(observableList);
            listView.setCellFactory(list -> new CollectionCell());
        });

        initData();

        listView.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.F5)
                initData();
        });

        buttonAdd.setOnMouseClicked(event -> {
            try {
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("DialogViews/collectionDialogView.fxml"));
                primaryStage.initOwner(buttonAdd.getScene().getWindow());
                primaryStage.initModality(Modality.APPLICATION_MODAL);
                primaryStage.initStyle(StageStyle.UNDECORATED);
                Rectangle rectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getBounds();
                primaryStage.setX(rectangle.getWidth() / 2 - 150);
                primaryStage.setY(rectangle.getHeight() / 2 - 150);
                primaryStage.setTitle("Добавить сборник");
                primaryStage.setScene(new Scene(root));
                primaryStage.showAndWait();
                initData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private void initData() {

        CollectionDAO collectionDAO = new CollectionDAO();
        ObservableList<CollectionEntity> observableList = FXCollections.observableArrayList();
        observableList.clear();
        observableList.addAll(collectionDAO.getCollections());
        listView.setItems(observableList);
        listView.setCellFactory(list -> new CollectionCell());

    }

    private class CollectionCell extends ListCell<CollectionEntity> {

        @Override
        public void updateItem(CollectionEntity item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {

                HBox top = new HBox();
                top.setSpacing(5);

                Label theme = new Label(item.getThemeCollectionEntity().getName());
                theme.getStyleClass().add("label-tag");

                top.getChildren().add(theme);

                Label name = new Label(item.getTypeCollectionEntity().getName()
                        + " \"" + item.getName() + "\"");
                name.getStyleClass().add("label-big");

                VBox center = new VBox(
                        top,
                        name,
                        new Label(item.getConferenceEntity().getLevelConferenceEntity().getName() + ' '
                                + item.getConferenceEntity().getTypeConferenceEntity().getName().toLowerCase() + " конференция "
                                + '\"' + item.getConferenceEntity().getName() + '\"'),
                        new Label("Издательство " + item.getEditionEntity().getName() + ", г."
                                + item.getEditionEntity().getCityEntity().getName())
                );

                name.prefWidthProperty().bind(center.widthProperty());

                Button edit = new Button();
                edit.getStyleClass().add("button-edit");
                edit.setOnMouseClicked(event -> {
                    try {
                        Stage primaryStage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogViews/collectionDialogView.fxml"));
                        Parent root = fxmlLoader.load();
                        fxmlLoader.<CollectionDialogViewController>getController().setEdit(item);
                        primaryStage.initOwner(getScene().getWindow());
                        primaryStage.initModality(Modality.APPLICATION_MODAL);
                        primaryStage.initStyle(StageStyle.UNDECORATED);
                        Rectangle rectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getBounds();
                        primaryStage.setX(rectangle.getWidth() / 2 - 150);
                        primaryStage.setY(rectangle.getHeight() / 2 - 150);
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
                    CollectionDAO collectionDAO = new CollectionDAO();
                    collectionDAO.deleteCollection(item);
                    initData();
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
