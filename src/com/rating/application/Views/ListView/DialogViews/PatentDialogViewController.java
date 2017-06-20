package com.rating.application.Views.ListView.DialogViews;

import com.rating.application.DAO.AuthorDAO;
import com.rating.application.DAO.PatentDAO;
import com.rating.application.DAO.RegistrationPlaceDAO;
import com.rating.application.DAO.TypePatentDAO;
import com.rating.application.Entity.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import org.controlsfx.control.CheckListView;
import org.controlsfx.control.ListSelectionView;

import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.ResourceBundle;

public class PatentDialogViewController implements Initializable {

    @FXML
    private Label title;

    @FXML
    private Button cancel;

    @FXML
    private Button accept;

    @FXML
    private TextField name;

    @FXML
    private ComboBox<TypePatentEntity> type;

    @FXML
    private TextField year;

    @FXML
    private TextField number;

    @FXML
    private ComboBox<RegistrationPlaceEntity> place;

    @FXML
    private ListSelectionView<AuthorEntity> authors;

    @FXML
    private CheckListView<AuthorEntity> holder;

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

        Collection<AuthorEntity> authorEntities = FXCollections.observableArrayList(new AuthorDAO().getAuthors());
        authors.getSourceItems().addAll(authorEntities);
        authors.setCellFactory(list -> new AuthorCell());
        authors.setSourceHeader(new Label("Имеющиеся авторы"));
        authors.setTargetHeader(new Label("Выбранные авторы"));

        holder.setItems(authors.getTargetItems());

        accept.setOnMouseClicked(event -> {

            if (
                    (name.getText() == null) ||
                            (type.getValue() == null) ||
                            (year.getText() == null) ||
                            (number.getText() == null) ||
                            (place.getValue() == null) ||
                            (authors.getTargetItems().size() == 0) ||
                            (holder.getItems().size() == 0)
                    ) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(accept.getScene().getWindow());
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initStyle(StageStyle.UNDECORATED);
                alert.setTitle(null);
                alert.setHeaderText("Внимание!");
                alert.setContentText("Все поля должны быть заполнены");
                alert.showAndWait();
            } else if (holder.getCheckModel().getCheckedItems().size() > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(accept.getScene().getWindow());
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initStyle(StageStyle.UNDECORATED);
                alert.setTitle(null);
                alert.setHeaderText("Внимание!");
                alert.setContentText("Должно быть не более одного правообладателя");
                alert.showAndWait();
            } else {
                PatentDAO delete = new PatentDAO();
                PatentDAO update = new PatentDAO();
                if (!isEdit) {
                    PatentEntity patentEntity = new PatentEntity();
                    patentEntity.setName(name.getText());
                    patentEntity.setNumber(number.getText());
                    patentEntity.setYear(Integer.valueOf(year.getText()));
                    patentEntity.setTypePatentEntity(type.getValue());
                    patentEntity.setRegistrationPlaceEntity(place.getValue());
                    Collection<AuthorPatentEntity> authorPatentEntities = FXCollections.observableArrayList();
                    for (AuthorEntity authorEntity : holder.getItems()) {
                        AuthorPatentEntity authorPatentEntity = new AuthorPatentEntity();
                        authorPatentEntity.setAuthorEntity(authorEntity);
                        authorPatentEntity.setPatentEntity(patentEntity);
                        if (holder.getCheckModel().getCheckedItems().get(0) == authorEntity)
                            authorPatentEntity.setHolder(new Integer(1).byteValue());
                        else
                            authorPatentEntity.setHolder(new Integer(0).byteValue());
                        authorPatentEntities.add(authorPatentEntity);
                    }
                    patentEntity.setAuthorPatentEntities(new HashSet<AuthorPatentEntity>(authorPatentEntities));
                    update.addPatent(patentEntity, authorPatentEntities);
                } else {
                    PatentEntity patentEntity = new PatentEntity();
                    patentEntity.setName(name.getText());
                    patentEntity.setNumber(number.getText());
                    patentEntity.setYear(Integer.valueOf(year.getText()));
                    patentEntity.setTypePatentEntity(type.getValue());
                    patentEntity.setRegistrationPlaceEntity(place.getValue());
                    Collection<AuthorPatentEntity> authorPatentEntities = FXCollections.observableArrayList();
                    for (AuthorEntity authorEntity : holder.getItems()) {
                        AuthorPatentEntity authorPatentEntity = new AuthorPatentEntity();
                        authorPatentEntity.setAuthorEntity(authorEntity);
                        authorPatentEntity.setPatentEntity(patentEntity);
                        if (holder.getCheckModel().getCheckedItems().get(0) == authorEntity)
                            authorPatentEntity.setHolder(new Integer(1).byteValue());
                        else
                            authorPatentEntity.setHolder(new Integer(0).byteValue());
                        authorPatentEntities.add(authorPatentEntity);
                    }
                    patentEntity.setAuthorPatentEntities(new HashSet<AuthorPatentEntity>(authorPatentEntities));
                    delete.deletePatentById(this.id);
                    update.updatePatent(patentEntity, authorPatentEntities);
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

    public void setEdit(PatentEntity patentEntity) {
        title.setText("Изменить патент");
        this.id = patentEntity.getId();
        name.setText(patentEntity.getName());
        type.getSelectionModel().select(patentEntity.getTypePatentEntity());
        number.setText(patentEntity.getNumber());
        year.setText(patentEntity.getYear().toString());
        place.getSelectionModel().select(patentEntity.getRegistrationPlaceEntity());
        for (AuthorPatentEntity authorPatentEntity : patentEntity.getAuthorPatentEntities()) {
            authors.getTargetItems().add(authorPatentEntity.getAuthorEntity());
            if (authorPatentEntity.getHolder() == new Integer(1).byteValue())
                holder.getCheckModel().check(authorPatentEntity.getAuthorEntity());
        }
        for (AuthorPatentEntity authorPatentEntity : patentEntity.getAuthorPatentEntities()) {
            if (authorPatentEntity.getHolder() == new Integer(1).byteValue())
                holder.getCheckModel().check(authorPatentEntity.getAuthorEntity());
        }
        isEdit = true;
    }

    private class AuthorCell extends ListCell<AuthorEntity> {

        @Override
        protected void updateItem(AuthorEntity item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null)
                setText(item.getSecondName() + ' ' + item.getFirstName() + '.' + item.getMiddleName() + '.');
        }
    }

}
