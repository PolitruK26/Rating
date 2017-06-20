package com.rating.application.Views.ListView.DialogViews;

import com.rating.application.DAO.ConferenceDAO;
import com.rating.application.DAO.FormConferenceDAO;
import com.rating.application.DAO.LevelConferenceDAO;
import com.rating.application.DAO.TypeConferenceDAO;
import com.rating.application.Entity.ConferenceEntity;
import com.rating.application.Entity.FormConferenceEntity;
import com.rating.application.Entity.LevelConferenceEntity;
import com.rating.application.Entity.TypeConferenceEntity;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.Collection;
import java.util.Objects;
import java.util.ResourceBundle;

public class ConferenceDialogViewController implements Initializable {

    @FXML
    private Button accept;

    @FXML
    private Button cancel;

    @FXML
    private TextField name;

    @FXML
    private ComboBox<FormConferenceEntity> form;

    @FXML
    private ComboBox<LevelConferenceEntity> level;

    @FXML
    private ComboBox<TypeConferenceEntity> type;

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

        Collection<FormConferenceEntity> formConferenceEntities = FXCollections.observableArrayList(new FormConferenceDAO().getForms());
        form.getItems().addAll(formConferenceEntities);
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
                for (FormConferenceEntity formConferenceEntity : formConferenceEntities) {
                    if (Objects.equals(formConferenceEntity.getName(), form.getEditor().getText()))
                        entity = formConferenceEntity;
                }
                return entity;
            }
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

        accept.setOnMouseClicked(event -> {

            if (
                    (name.getText() == null) ||
                            (form.getValue() == null) ||
                            (level.getValue() == null) ||
                            (type.getValue() == null)
                    ) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(accept.getScene().getWindow());
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initStyle(StageStyle.UNDECORATED);
                alert.setTitle(null);
                alert.setHeaderText("Внимание!");
                alert.setContentText("Все поля должны быть заполнены");
                alert.showAndWait();
            } else {
                ConferenceDAO conferenceDAO = new ConferenceDAO();
                if (!isEdit)
                    conferenceDAO.addConference(new ConferenceEntity(
                            name.getText(),
                            form.getValue(),
                            level.getValue(),
                            type.getValue()
                    ));
                else {
                    ConferenceEntity conferenceEntity = new ConferenceEntity();
                    conferenceEntity.setId(this.id);
                    conferenceEntity.setName(name.getText());
                    conferenceEntity.setFormConferenceEntity(form.getValue());
                    conferenceEntity.setLevelConferenceEntity(level.getValue());
                    conferenceEntity.setTypeConferenceEntity(type.getValue());
                    conferenceDAO.updateConference(conferenceEntity);
                }

                Stage stage = (Stage) accept.getScene().getWindow();
                stage.close();
            }
        });

        cancel.setOnMouseClicked(event -> {
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        });

    }

    public void setEdit(ConferenceEntity conferenceEntity) {
        title.setText("Изменить конференцию");
        this.id = conferenceEntity.getId();
        name.setText(conferenceEntity.getName());
        form.getSelectionModel().select(conferenceEntity.getFormConferenceEntity());
        level.getSelectionModel().select(conferenceEntity.getLevelConferenceEntity());
        type.getSelectionModel().select(conferenceEntity.getTypeConferenceEntity());
        isEdit = true;
    }

}
