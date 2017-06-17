package com.rating.application.Views.ListView;

import com.rating.application.DAO.AuthorDAO;
import com.rating.application.Entity.AuthorEntity;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthorViewController implements Initializable {

    @FXML
    private Button buttonAdd;

    @FXML
    private ListView<AuthorEntity> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
