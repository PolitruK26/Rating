package com.rating.application.Views.ListView;

import com.rating.application.DAO.AuthorDAO;
import com.rating.application.DAO.PositionAuthorDAO;
import com.rating.application.DAO.PowerAuthorDAO;
import com.rating.application.DAO.RankAuthorDAO;
import com.rating.application.Entity.AuthorEntity;
import com.rating.application.Entity.PositionAuthorEntity;
import com.rating.application.Entity.PowerAuthorEntity;
import com.rating.application.Entity.RankAuthorEntity;
import com.rating.application.Views.ListView.DialogViews.AuthorDialogViewController;
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

public class AuthorViewController implements Initializable {

    @FXML
    private AnchorPane window;

    @FXML
    private Button buttonAdd;

    @FXML
    private ListView<AuthorEntity> listView;

    @FXML
    private ComboBox<PositionAuthorEntity> position;

    @FXML
    private ComboBox<PowerAuthorEntity> power;

    @FXML
    private ComboBox<RankAuthorEntity> rank;

    @FXML
    private Button clear;

    private TextField search;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        clear.setOnAction(event -> {
            position.getSelectionModel().clearSelection();
            power.getSelectionModel().clearSelection();
            rank.getSelectionModel().clearSelection();
        });

        Collection<PositionAuthorEntity> positionAuthorEntities = FXCollections.observableArrayList(new PositionAuthorDAO().getPosotions());
        position.getItems().addAll(positionAuthorEntities);
        position.setConverter(new StringConverter<PositionAuthorEntity>() {
            @Override
            public String toString(PositionAuthorEntity object) {
                if (object != null)
                    return object.getName();
                else
                    return null;
            }

            @Override
            public PositionAuthorEntity fromString(String string) {
                PositionAuthorEntity entity = new PositionAuthorEntity();
                for (PositionAuthorEntity positionAuthorEntity : positionAuthorEntities) {
                    if (Objects.equals(positionAuthorEntity.getName(), position.getEditor().getText()))
                        entity = positionAuthorEntity;
                }
                return entity;
            }
        });
        position.valueProperty().addListener((observable, oldValue, newValue) -> {
            AuthorDAO authorDAO = new AuthorDAO();
            ObservableList<AuthorEntity> observableList = FXCollections.observableArrayList();
            observableList.clear();
            observableList.addAll(authorDAO.getFiltredAuthors(search.getText(), position.getValue(), power.getValue(), rank.getValue()));
            listView.getItems().clear();
            listView.setItems(observableList);
            listView.setCellFactory(list -> new AuthorCell());
        });

        Collection<PowerAuthorEntity> powerAuthorEntities = FXCollections.observableArrayList(new PowerAuthorDAO().getPowers());
        power.getItems().addAll(powerAuthorEntities);
        power.setConverter(new StringConverter<PowerAuthorEntity>() {
            @Override
            public String toString(PowerAuthorEntity object) {
                if (object != null)
                    return object.getName();
                else
                    return null;
            }

            @Override
            public PowerAuthorEntity fromString(String string) {
                PowerAuthorEntity entity = new PowerAuthorEntity();
                for (PowerAuthorEntity powerAuthorEntity : powerAuthorEntities) {
                    if (Objects.equals(powerAuthorEntity.getName(), power.getEditor().getText()))
                        entity = powerAuthorEntity;
                }
                return entity;
            }
        });
        power.valueProperty().addListener((observable, oldValue, newValue) -> {
            AuthorDAO authorDAO = new AuthorDAO();
            ObservableList<AuthorEntity> observableList = FXCollections.observableArrayList();
            observableList.clear();
            observableList.addAll(authorDAO.getFiltredAuthors(search.getText(), position.getValue(), power.getValue(), rank.getValue()));
            listView.getItems().clear();
            listView.setItems(observableList);
            listView.setCellFactory(list -> new AuthorCell());
        });

        Collection<RankAuthorEntity> rankAuthorEntities = FXCollections.observableArrayList(new RankAuthorDAO().getRanks());
        rank.getItems().addAll(rankAuthorEntities);
        rank.setConverter(new StringConverter<RankAuthorEntity>() {
            @Override
            public String toString(RankAuthorEntity object) {
                if (object != null)
                    return object.getName();
                else
                    return null;
            }

            @Override
            public RankAuthorEntity fromString(String string) {
                RankAuthorEntity entity = new RankAuthorEntity();
                for (RankAuthorEntity rankAuthorEntity : rankAuthorEntities) {
                    if (Objects.equals(rankAuthorEntity.getName(), rank.getEditor().getText()))
                        entity = rankAuthorEntity;
                }
                return entity;
            }
        });
        rank.valueProperty().addListener((observable, oldValue, newValue) -> {
            AuthorDAO authorDAO = new AuthorDAO();
            ObservableList<AuthorEntity> observableList = FXCollections.observableArrayList();
            observableList.clear();
            observableList.addAll(authorDAO.getFiltredAuthors(search.getText(), position.getValue(), power.getValue(), rank.getValue()));
            listView.getItems().clear();
            listView.setItems(observableList);
            listView.setCellFactory(list -> new AuthorCell());
        });

        search = TextFields.createClearableTextField();
        window.getChildren().add(search);
        search.setPrefHeight(30);
        search.setFocusTraversable(false);
        AnchorPane.setTopAnchor(search, 5.0);
        AnchorPane.setRightAnchor(search, 10.0);
        AnchorPane.setLeftAnchor(search, 10.0);
        search.setPromptText("Поиск по фамилии");
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            AuthorDAO authorDAO = new AuthorDAO();
            ObservableList<AuthorEntity> observableList = FXCollections.observableArrayList();
            observableList.clear();
            observableList.addAll(authorDAO.getFiltredAuthors(search.getText(), position.getValue(), power.getValue(), rank.getValue()));
            listView.getItems().clear();
            listView.setItems(observableList);
            listView.setCellFactory(list -> new AuthorCell());
        });

        initData();

        listView.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.F5)
                initData();
        });

        buttonAdd.setOnMouseClicked(event -> {
            try {
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("DialogViews/authorDialogView.fxml"));
                primaryStage.initOwner(buttonAdd.getScene().getWindow());
                primaryStage.initModality(Modality.APPLICATION_MODAL);
                primaryStage.initStyle(StageStyle.UNDECORATED);
                Rectangle rectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getBounds();
                primaryStage.setX(rectangle.getWidth() / 2 - 150);
                primaryStage.setY(rectangle.getHeight() / 2 - 215);
                primaryStage.setTitle("Добавить автора");
                primaryStage.setScene(new Scene(root));
                primaryStage.showAndWait();
                initData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private void initData() {
        AuthorDAO authorDAO = new AuthorDAO();
        ObservableList<AuthorEntity> observableList = FXCollections.observableArrayList();
        observableList.clear();
        observableList.addAll(authorDAO.getAuthors());
        listView.setItems(observableList);
        listView.setCellFactory(list -> new AuthorCell());
    }

    private class AuthorCell extends ListCell<AuthorEntity> {

        @Override
        public void updateItem(AuthorEntity item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {

                HBox top = new HBox();
                top.setSpacing(5);

                Label department = new Label(item.getPositionAuthorEntity().getDepartmentEntity().getName());
                department.getStyleClass().add("label-tag");

                top.getChildren().add(department);

                Label name = new Label(item.getPositionAuthorEntity().getName() + " :: "
                        + item.getSecondName() + ' '
                        + item.getFirstName() + '.'
                        + item.getMiddleName() + '.');
                name.getStyleClass().add("label-big");

                VBox center = new VBox(
                        top,
                        name,
                        new Label(item.getPowerAuthorEntity().getName()),
                        new Label(item.getRankAuthorEntity().getName()),
                        new Label("Телефон: " + item.getPhone()),
                        new Label("Email: " + item.getEmail())
                );

                name.prefWidthProperty().bind(center.widthProperty());

                Button edit = new Button();
                edit.getStyleClass().add("button-edit");
                edit.setOnMouseClicked(event -> {
                    try {
                        Stage primaryStage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogViews/authorDialogView.fxml"));
                        Parent root = fxmlLoader.load();
                        fxmlLoader.<AuthorDialogViewController>getController().setEdit(item);
                        primaryStage.initOwner(getScene().getWindow());
                        primaryStage.initModality(Modality.APPLICATION_MODAL);
                        primaryStage.initStyle(StageStyle.UNDECORATED);
                        Rectangle rectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getBounds();
                        primaryStage.setX(rectangle.getWidth() / 2 - 150);
                        primaryStage.setY(rectangle.getHeight() / 2 - 215);
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
                    AuthorDAO authorDAO = new AuthorDAO();
                    authorDAO.deleteAuthor(item);
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
