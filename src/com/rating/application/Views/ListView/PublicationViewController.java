package com.rating.application.Views.ListView;

import com.rating.application.DAO.PublicationDAO;
import com.rating.application.Entity.AuthorEntity;
import com.rating.application.Entity.KeywordsEntity;
import com.rating.application.Entity.PublicationEntity;
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

public class PublicationViewController implements Initializable {

    @FXML
    private Button buttonAdd;

    @FXML
    private ListView<PublicationEntity> listView;

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
                Parent root = FXMLLoader.load(getClass().getResource("DialogViews/publicationDialogView.fxml"));
                primaryStage.initOwner(buttonAdd.getScene().getWindow());
                primaryStage.initModality(Modality.APPLICATION_MODAL);
                primaryStage.initStyle(StageStyle.UNDECORATED);
                Rectangle rectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getBounds();
                primaryStage.setX(rectangle.getWidth() / 2 - 150);
                primaryStage.setY(rectangle.getHeight() / 2 - 215);
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
                        new Label("Опубликованно в \"" + item.getCollectionEntity().getName() + "\"")
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
                        primaryStage.setX(rectangle.getWidth() / 2 - 200);
                        primaryStage.setY(rectangle.getHeight() / 2 - 300);
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
                    PublicationDAO publicationDAO = new PublicationDAO();
                    publicationDAO.deletePublication(item);
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
