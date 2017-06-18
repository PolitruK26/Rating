package com.rating.application.Views.ListView;

import com.rating.application.DAO.PatentDAO;
import com.rating.application.Entity.AuthorPatentEntity;
import com.rating.application.Entity.PatentEntity;
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

public class PatentViewController implements Initializable {

    @FXML
    private Button buttonAdd;

    @FXML
    private ListView<PatentEntity> listView;

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
                Parent root = FXMLLoader.load(getClass().getResource("DialogViews/patentDialogView.fxml"));
                primaryStage.initOwner(buttonAdd.getScene().getWindow());
                primaryStage.initModality(Modality.APPLICATION_MODAL);
                primaryStage.initStyle(StageStyle.UNDECORATED);
                Rectangle rectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getBounds();
                primaryStage.setX(rectangle.getWidth() / 2 - 350);
                primaryStage.setY(rectangle.getHeight() / 2 - 210);
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
                    PatentDAO patentDAO = new PatentDAO();
                    patentDAO.deletePatent(item);
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
