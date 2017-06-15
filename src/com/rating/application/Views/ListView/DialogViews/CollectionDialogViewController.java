package com.rating.application.Views.ListView.DialogViews;

import com.rating.application.Entity.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;

import java.net.URL;
import java.util.ResourceBundle;

public class CollectionDialogViewController implements Initializable {

    @FXML
    private Button accept;

    @FXML
    private Button cancel;

    @FXML
    private TextField name;

    @FXML
    private ComboBox<TypeCollectionEntity> type;

    @FXML
    private ComboBox<ThemeCollectionEntity> theme;

    @FXML
    private ComboBox<EditionEntity> edition;

    @FXML
    private ComboBox<ConferenceEntity> conference;

    @FXML
    private Label title;

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

    }

    public void setEdit(CollectionEntity collectionEntity) {
        title.setText("Изменить сборник");
        this.id = collectionEntity.getId();
        name.setText(collectionEntity.getName());
        type.getSelectionModel().select(collectionEntity.getTypeCollectionEntity());
        theme.getSelectionModel().select(collectionEntity.getThemeCollectionEntity());
        edition.getSelectionModel().select(collectionEntity.getEditionEntity());
        conference.getSelectionModel().select(collectionEntity.getConferenceEntity());
        isEdit = true;
    }

}
