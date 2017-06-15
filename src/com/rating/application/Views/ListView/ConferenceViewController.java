package com.rating.application.Views.ListView;

import com.rating.application.DAO.ConferenceDAO;
import com.rating.application.DAO.FormConferenceDAO;
import com.rating.application.DAO.LevelConferenceDAO;
import com.rating.application.DAO.TypeConferenceDAO;
import com.rating.application.Entity.ConferenceEntity;
import com.rating.application.Entity.FormConferenceEntity;
import com.rating.application.Entity.LevelConferenceEntity;
import com.rating.application.Entity.TypeConferenceEntity;
import com.rating.application.Views.ListView.DialogViews.ConferenceDialogViewController;
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

public class ConferenceViewController implements Initializable {

    @FXML
    private Button add;

    @FXML
    private ListView<ConferenceEntity> list;

    @FXML
    private AnchorPane window;

    @FXML
    private ComboBox<FormConferenceEntity> form;

    @FXML
    private ComboBox<LevelConferenceEntity> level;

    @FXML
    private ComboBox<TypeConferenceEntity> type;

    @FXML
    private Button clear;

    private TextField search;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        clear.setOnAction(event -> {
            type.getSelectionModel().clearSelection();
            form.getSelectionModel().clearSelection();
            level.getSelectionModel().clearSelection();
        });

        Collection<TypeConferenceEntity> typeConferenceEntities = FXCollections.observableArrayList(new TypeConferenceDAO().getTypes());
        type.getItems().addAll(typeConferenceEntities);
        type.setConverter(new StringConverter<TypeConferenceEntity>() {
            @Override
            public String toString(TypeConferenceEntity object) {
                if (object != null)
                    return object.getName();
                else
                    return null;
            }

            @Override
            public TypeConferenceEntity fromString(String string) {
                TypeConferenceEntity entity = new TypeConferenceEntity();
                for (TypeConferenceEntity typeConferenceEntity : typeConferenceEntities) {
                    if (Objects.equals(typeConferenceEntity.getName(), type.getEditor().getText()))
                        entity = typeConferenceEntity;
                }
                return entity;
            }
        });
        type.valueProperty().addListener((observable, oldValue, newValue) -> {
            ConferenceDAO conferenceDAO = new ConferenceDAO();
            ObservableList<ConferenceEntity> observableList = FXCollections.observableArrayList();
            observableList.clear();
            observableList.addAll(conferenceDAO.getFiltredConferences(search.getText(), type.getValue(), form.getValue(), level.getValue()));
            list.getItems().clear();
            list.setItems(observableList);
            list.setCellFactory(list -> new ConferenceCell());
        });

        Collection<FormConferenceEntity> conferenceEntities = FXCollections.observableArrayList(new FormConferenceDAO().getForms());
        form.getItems().addAll(conferenceEntities);
        form.setConverter(new StringConverter<FormConferenceEntity>() {
            @Override
            public String toString(FormConferenceEntity object) {
                if (object != null)
                    return object.getName();
                else
                    return null;
            }

            @Override
            public FormConferenceEntity fromString(String string) {
                FormConferenceEntity entity = new FormConferenceEntity();
                for (FormConferenceEntity formConferenceEntity : conferenceEntities) {
                    if (Objects.equals(formConferenceEntity.getName(), form.getEditor().getText()))
                        entity = formConferenceEntity;
                }
                return entity;
            }
        });
        form.valueProperty().addListener((observable, oldValue, newValue) -> {
            ConferenceDAO conferenceDAO = new ConferenceDAO();
            ObservableList<ConferenceEntity> observableList = FXCollections.observableArrayList();
            observableList.clear();
            observableList.addAll(conferenceDAO.getFiltredConferences(search.getText(), type.getValue(), form.getValue(), level.getValue()));
            list.getItems().clear();
            list.setItems(observableList);
            list.setCellFactory(list -> new ConferenceCell());
        });

        Collection<LevelConferenceEntity> levelConferenceEntities = FXCollections.observableArrayList(new LevelConferenceDAO().getLevels());
        level.getItems().addAll(levelConferenceEntities);
        level.setConverter(new StringConverter<LevelConferenceEntity>() {
            @Override
            public String toString(LevelConferenceEntity object) {
                if (object != null)
                    return object.getName();
                else
                    return null;
            }

            @Override
            public LevelConferenceEntity fromString(String string) {
                LevelConferenceEntity entity = new LevelConferenceEntity();
                for (LevelConferenceEntity levelConferenceEntity : levelConferenceEntities) {
                    if (Objects.equals(levelConferenceEntity.getName(), level.getEditor().getText()))
                        entity = levelConferenceEntity;
                }
                return entity;
            }
        });
        level.valueProperty().addListener((observable, oldValue, newValue) -> {
            ConferenceDAO conferenceDAO = new ConferenceDAO();
            ObservableList<ConferenceEntity> observableList = FXCollections.observableArrayList();
            observableList.clear();
            observableList.addAll(conferenceDAO.getFiltredConferences(search.getText(), type.getValue(), form.getValue(), level.getValue()));
            list.getItems().clear();
            list.setItems(observableList);
            list.setCellFactory(list -> new ConferenceCell());
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
            ConferenceDAO conferenceDAO = new ConferenceDAO();
            ObservableList<ConferenceEntity> observableList = FXCollections.observableArrayList();
            observableList.clear();
            observableList.addAll(conferenceDAO.getFiltredConferences(search.getText(), type.getValue(), form.getValue(), level.getValue()));
            list.getItems().clear();
            list.setItems(observableList);
            list.setCellFactory(list -> new ConferenceCell());
        });

        initData();

        list.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.F5)
                initData();
        });

        add.setOnMouseClicked(event -> {
            try {
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("DialogViews/conferenceDialogView.fxml"));
                primaryStage.initOwner(add.getScene().getWindow());
                primaryStage.initModality(Modality.APPLICATION_MODAL);
                primaryStage.initStyle(StageStyle.UNDECORATED);
                Rectangle rectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getBounds();
                primaryStage.setX(rectangle.getWidth() / 2 - 150);
                primaryStage.setY(rectangle.getHeight() / 2 - 150);
                primaryStage.setTitle("Добавить конференцию");
                primaryStage.setScene(new Scene(root));
                primaryStage.showAndWait();
                initData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private void initData() {

        ConferenceDAO conferenceDAO = new ConferenceDAO();
        ObservableList<ConferenceEntity> observableList = FXCollections.observableArrayList();
        observableList.clear();
        observableList.addAll(conferenceDAO.getConferences());
        list.getItems().clear();
        list.setItems(observableList);
        list.setCellFactory(list -> new ConferenceCell());

    }

    private class ConferenceCell extends ListCell<ConferenceEntity> {

        @Override
        public void updateItem(ConferenceEntity item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {

                HBox top = new HBox();
                top.setSpacing(5);

                Label theme = new Label(item.getFormConferenceEntity().getName());
                theme.getStyleClass().add("label-tag");

                top.getChildren().add(theme);

                Label name = new Label(item.getLevelConferenceEntity().getName() + ' '
                        + item.getTypeConferenceEntity().getName().toLowerCase() + " конференция "
                        + '\"' + item.getName() + '\"');
                name.getStyleClass().add("label-big");

                VBox center = new VBox(
                        top,
                        name
                );

                name.prefWidthProperty().bind(center.widthProperty());

                Button edit = new Button();
                edit.getStyleClass().add("button-edit");
                edit.setOnMouseClicked(event -> {
                    try {
                        Stage primaryStage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogViews/conferenceDialogView.fxml"));
                        Parent root = fxmlLoader.load();
                        fxmlLoader.<ConferenceDialogViewController>getController().setEdit(item);
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
                delete.setOnMouseClicked(event -> {
                    ConferenceDAO conferenceDAO = new ConferenceDAO();
                    conferenceDAO.deleteConference(item);
                    initData();
                });
                delete.getStyleClass().add("button-delete");

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
