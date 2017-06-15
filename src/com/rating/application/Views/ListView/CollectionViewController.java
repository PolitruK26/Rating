package com.rating.application.Views.ListView;

import com.rating.application.Entity.CollectionEntity;
import com.rating.application.DAO.CollectionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CollectionViewController implements Initializable {

    @FXML
    private Button buttonAdd;

    @FXML
    private ListView<CollectionEntity> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initData();

        listView.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.F5)
                initData();
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

                Button delete = new Button();
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
