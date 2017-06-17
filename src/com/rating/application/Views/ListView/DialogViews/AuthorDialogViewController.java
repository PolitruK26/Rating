package com.rating.application.Views.ListView.DialogViews;

import com.rating.application.DAO.AuthorDAO;
import com.rating.application.DAO.PositionAuthorDAO;
import com.rating.application.DAO.PowerAuthorDAO;
import com.rating.application.DAO.RankAuthorDAO;
import com.rating.application.Entity.AuthorEntity;
import com.rating.application.Entity.PositionAuthorEntity;
import com.rating.application.Entity.PowerAuthorEntity;
import com.rating.application.Entity.RankAuthorEntity;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.Collection;
import java.util.Objects;
import java.util.ResourceBundle;

public class AuthorDialogViewController implements Initializable {

    @FXML
    private Label title;

    @FXML
    private TextField first;

    @FXML
    private TextField second;

    @FXML
    private TextField middle;

    @FXML
    private TextField phome;

    @FXML
    private TextField email;

    @FXML
    private ComboBox<PositionAuthorEntity> position;

    @FXML
    private ComboBox<PowerAuthorEntity> power;

    @FXML
    private ComboBox<RankAuthorEntity> rank;

    @FXML
    private Button cancel;

    @FXML
    private Button accept;

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

        accept.setOnMouseClicked(event -> {

            AuthorDAO authorDAO = new AuthorDAO();
            if (!isEdit)
                authorDAO.addAuthor(new AuthorEntity(
                        second.getText(),
                        first.getText(),
                        middle.getText(),
                        phome.getText(),
                        email.getText(),
                        position.getValue(),
                        power.getValue(),
                        rank.getValue()
                ));
            else {
                AuthorEntity authorEntity = new AuthorEntity();
                authorEntity.setId(this.id);
                authorEntity.setSecondName(second.getText());
                authorEntity.setFirstName(first.getText());
                authorEntity.setMiddleName(middle.getText());
                authorEntity.setPhone(phome.getText());
                authorEntity.setEmail(email.getText());
                authorEntity.setPositionAuthorEntity(position.getValue());
                authorEntity.setPowerAuthorEntity(power.getValue());
                authorEntity.setRankAuthorEntity(rank.getValue());
                authorDAO.updateAuthor(authorEntity);
            }

            Stage stage = (Stage) accept.getScene().getWindow();
            stage.close();
        });

        cancel.setOnMouseClicked(event -> {
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        });

    }

    public void setEdit(AuthorEntity authorEntity) {
        title.setText("Изменить автора");
        this.id = authorEntity.getId();
        first.setText(authorEntity.getFirstName());
        second.setText(authorEntity.getSecondName());
        middle.setText(authorEntity.getMiddleName());
        phome.setText(authorEntity.getPhone());
        email.setText(authorEntity.getEmail());
        position.getSelectionModel().select(authorEntity.getPositionAuthorEntity());
        power.getSelectionModel().select(authorEntity.getPowerAuthorEntity());
        rank.getSelectionModel().select(authorEntity.getRankAuthorEntity());
        isEdit = true;
    }
}
