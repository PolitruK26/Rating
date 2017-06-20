package com.rating.application.Views.ListView;

import com.rating.application.DAO.PatentDAO;
import com.rating.application.DAO.RegistrationPlaceDAO;
import com.rating.application.DAO.TypePatentDAO;
import com.rating.application.Entity.AuthorPatentEntity;
import com.rating.application.Entity.PatentEntity;
import com.rating.application.Entity.RegistrationPlaceEntity;
import com.rating.application.Entity.TypePatentEntity;
import com.rating.application.Views.ListView.DialogViews.PatentDialogViewController;
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

public class PatentViewController implements Initializable {

    @FXML
    private AnchorPane window;

    @FXML
    private Button buttonAdd;

    @FXML
    private ListView<PatentEntity> listView;

    @FXML
    private ComboBox<TypePatentEntity> type;

    @FXML
    private ComboBox<Integer> year;

    @FXML
    private ComboBox<RegistrationPlaceEntity> place;

    @FXML
    private Button clear;

    private TextField search;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        clear.setOnAction(event -> {
            type.getSelectionModel().clearSelection();
            year.getSelectionModel().clearSelection();
            place.getSelectionModel().clearSelection();
        });

        Collection<TypePatentEntity> typePatentEntities = FXCollections.observableArrayList(new TypePatentDAO().getTypes());
        type.getItems().addAll(typePatentEntities);
        type.setConverter(new StringConverter<TypePatentEntity>() {
            @Override
            public String toString(TypePatentEntity object) {
                if (object != null)
                    return object.getName();
                else
                    return null;
            }

            @Override
            public TypePatentEntity fromString(String string) {
                TypePatentEntity entity = new TypePatentEntity();
                for (TypePatentEntity typePatentEntity : typePatentEntities) {
                    if (Objects.equals(typePatentEntity.getName(), type.getEditor().getText()))
                        entity = typePatentEntity;
                }
                return entity;
            }
        });
        type.valueProperty().addListener((observable, oldValue, newValue) -> {
            PatentDAO patentDAO = new PatentDAO();
            ObservableList<PatentEntity> observableList = FXCollections.observableArrayList();
            observableList.clear();
            observableList.addAll(patentDAO.getFiltredPatents(search.getText(), type.getValue(), year.getValue(), place.getValue()));
            listView.getItems().clear();
            listView.setItems(observableList);
            listView.setCellFactory(list -> new PatentCell());
        });

        for (int i = 1980; i <= 2070; i++)
            year.getItems().add(i);
        year.valueProperty().addListener((observable, oldValue, newValue) -> {
            PatentDAO patentDAO = new PatentDAO();
            ObservableList<PatentEntity> observableList = FXCollections.observableArrayList();
            observableList.clear();
            observableList.addAll(patentDAO.getFiltredPatents(search.getText(), type.getValue(), year.getValue(), place.getValue()));
            listView.getItems().clear();
            listView.setItems(observableList);
            listView.setCellFactory(list -> new PatentCell());
        });

        Collection<RegistrationPlaceEntity> registrationPlaceEntities = FXCollections.observableArrayList(new RegistrationPlaceDAO().getPlaces());
        place.getItems().addAll(registrationPlaceEntities);
        place.setConverter(new StringConverter<RegistrationPlaceEntity>() {
            @Override
            public String toString(RegistrationPlaceEntity object) {
                if (object != null)
                    return object.getName();
                else
                    return null;
            }

            @Override
            public RegistrationPlaceEntity fromString(String string) {
                RegistrationPlaceEntity entity = new RegistrationPlaceEntity();
                for (RegistrationPlaceEntity registrationPlaceEntity : registrationPlaceEntities) {
                    if (Objects.equals(registrationPlaceEntity.getName(), place.getEditor().getText()))
                        entity = registrationPlaceEntity;
                }
                return entity;
            }
        });
        place.valueProperty().addListener((observable, oldValue, newValue) -> {
            PatentDAO patentDAO = new PatentDAO();
            ObservableList<PatentEntity> observableList = FXCollections.observableArrayList();
            observableList.clear();
            observableList.addAll(patentDAO.getFiltredPatents(search.getText(), type.getValue(), year.getValue(), place.getValue()));
            listView.getItems().clear();
            listView.setItems(observableList);
            listView.setCellFactory(list -> new PatentCell());
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
            PatentDAO patentDAO = new PatentDAO();
            ObservableList<PatentEntity> observableList = FXCollections.observableArrayList();
            observableList.clear();
            observableList.addAll(patentDAO.getFiltredPatents(search.getText(), type.getValue(), year.getValue(), place.getValue()));
            listView.getItems().clear();
            listView.setItems(observableList);
            listView.setCellFactory(list -> new PatentCell());
        });

        initData();

        listView.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.F5)
                initData();
        });

        buttonAdd.setOnMouseClicked(event -> {
            try {
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("DialogViews/patentDialogView.fxml"));
                primaryStage.initOwner(buttonAdd.getScene().getWindow());
                primaryStage.initModality(Modality.APPLICATION_MODAL);
                primaryStage.initStyle(StageStyle.UNDECORATED);
                Rectangle rectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getBounds();
                primaryStage.setX(rectangle.getWidth() / 2 - 350);
                primaryStage.setY(rectangle.getHeight() / 2 - 210);
                primaryStage.setTitle("Добавить патент");
                primaryStage.setScene(new Scene(root));
                primaryStage.showAndWait();
                initData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private void initData() {
        PatentDAO patentDAO = new PatentDAO();
        ObservableList<PatentEntity> observableList = FXCollections.observableArrayList();
        observableList.clear();
        observableList.addAll(patentDAO.getPanents());
        listView.setItems(observableList);
        listView.setCellFactory(list -> new PatentCell());
    }

    private class PatentCell extends ListCell<PatentEntity> {

        @Override
        public void updateItem(PatentEntity item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {

                HBox top = new HBox();
                top.setSpacing(5);

                Label year = new Label(item.getYear().toString());
                year.getStyleClass().add("label-tag");

                top.getChildren().add(year);

                Label name = new Label("Патент №" + item.getNumber() + ' '
                        + item.getTypePatentEntity().getName().toLowerCase()
                        + " \"" + item.getName() + "\"");
                name.getStyleClass().add("label-big");

                Boolean isHolderAuthor = new Boolean(false);
                Label holder = null;

                HBox authors = new HBox();
                authors.setSpacing(5);

                for (AuthorPatentEntity authorPatentEntity : item.getAuthorPatentEntities()) {
                    Label label = new Label(authorPatentEntity.getAuthorEntity().getSecondName() + ' '
                            + authorPatentEntity.getAuthorEntity().getFirstName() + '.'
                            + authorPatentEntity.getAuthorEntity().getMiddleName() + '.');
                    label.getStyleClass().add("label-italic");
                    authors.getChildren().add(label);

                    if (authorPatentEntity.getHolder() == 1) {
                        holder = new Label("Правообладатель: " + authorPatentEntity.getAuthorEntity().getSecondName() + ' '
                                + authorPatentEntity.getAuthorEntity().getFirstName() + '.'
                                + authorPatentEntity.getAuthorEntity().getMiddleName() + '.');
                        isHolderAuthor = true;
                    }
                }

                if (!isHolderAuthor) {
                    holder = new Label("Правообладание закрепленно за ОРГАНИЗАЦИЕЙ");
                }

                VBox center = new VBox(
                        top,
                        name,
                        authors,
                        holder,
                        new Label("Зарегестрирован в: " + item.getRegistrationPlaceEntity().getName() + ", г."
                                + item.getRegistrationPlaceEntity().getCityEntity().getName())
                );

                name.prefWidthProperty().bind(center.widthProperty());

                Button edit = new Button();
                edit.getStyleClass().add("button-edit");
                edit.setOnMouseClicked(event -> {
                    try {
                        Stage primaryStage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogViews/patentDialogView.fxml"));
                        Parent root = fxmlLoader.load();
                        fxmlLoader.<PatentDialogViewController>getController().setEdit(item);
                        primaryStage.initOwner(getScene().getWindow());
                        primaryStage.initModality(Modality.APPLICATION_MODAL);
                        primaryStage.initStyle(StageStyle.UNDECORATED);
                        Rectangle rectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getBounds();
                        primaryStage.setX(rectangle.getWidth() / 2 - 350);
                        primaryStage.setY(rectangle.getHeight() / 2 - 210);
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
                        PatentDAO patentDAO = new PatentDAO();
                        patentDAO.deletePatent(item);
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
