package com.rating.application.Views.ListView.DialogViews;

import com.rating.application.DAO.AuthorDAO;
import com.rating.application.DAO.CertificateDAO;
import com.rating.application.DAO.TypeCertificateDAO;
import com.rating.application.Entity.AuthorCertificateEntity;
import com.rating.application.Entity.AuthorEntity;
import com.rating.application.Entity.CertificateEntity;
import com.rating.application.Entity.TypeCertificateEntity;
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

public class CertificateDialogViewController implements Initializable {

    @FXML
    private Label title;

    @FXML
    private Button cancel;

    @FXML
    private Button accept;

    @FXML
    private TextField name;

    @FXML
    private ComboBox<TypeCertificateEntity> type;

    @FXML
    private TextField year;

    @FXML
    private TextField number;

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

        Collection<TypeCertificateEntity> typeCertificateEntities = FXCollections.observableArrayList(new TypeCertificateDAO().getTypes());
        type.getItems().addAll(typeCertificateEntities);
        type.setConverter(new StringConverter<TypeCertificateEntity>() {
            @Override
            public String toString(TypeCertificateEntity object) {
                if (object != null)
                    return object.getName();
                else
                    return null;
            }

            @Override
            public TypeCertificateEntity fromString(String string) {
                TypeCertificateEntity entity = new TypeCertificateEntity();
                for (TypeCertificateEntity typeCertificateEntity : typeCertificateEntities) {
                    if (Objects.equals(typeCertificateEntity.getName(), type.getEditor().getText()))
                        entity = typeCertificateEntity;
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
                CertificateDAO update = new CertificateDAO();
                CertificateDAO delete = new CertificateDAO();
                if (!isEdit) {
                    CertificateEntity certificateEntity = new CertificateEntity();
                    certificateEntity.setName(name.getText());
                    certificateEntity.setNumber(number.getText());
                    certificateEntity.setYear(Integer.valueOf(year.getText()));
                    certificateEntity.setTypeCertificateEntity(type.getValue());
                    Collection<AuthorCertificateEntity> authorCertificateEntities = FXCollections.observableArrayList();
                    for (AuthorEntity authorEntity : holder.getItems()) {
                        AuthorCertificateEntity authorCertificateEntity = new AuthorCertificateEntity();
                        authorCertificateEntity.setAuthorEntity(authorEntity);
                        authorCertificateEntity.setCertificateEntity(certificateEntity);
                        if (holder.getCheckModel().getCheckedItems().get(0) == authorEntity)
                            authorCertificateEntity.setHolder(new Integer(1).byteValue());
                        else
                            authorCertificateEntity.setHolder(new Integer(0).byteValue());
                        authorCertificateEntities.add(authorCertificateEntity);
                    }
                    certificateEntity.setAuthorCertificateEntities(new HashSet<AuthorCertificateEntity>(authorCertificateEntities));
                    update.addCertificate(certificateEntity, authorCertificateEntities);
                } else {
                    CertificateEntity certificateEntity = new CertificateEntity();
                    certificateEntity.setName(name.getText());
                    certificateEntity.setNumber(number.getText());
                    certificateEntity.setYear(Integer.valueOf(year.getText()));
                    certificateEntity.setTypeCertificateEntity(type.getValue());
                    Collection<AuthorCertificateEntity> authorCertificateEntities = FXCollections.observableArrayList();
                    for (AuthorEntity authorEntity : holder.getItems()) {
                        AuthorCertificateEntity authorCertificateEntity = new AuthorCertificateEntity();
                        authorCertificateEntity.setAuthorEntity(authorEntity);
                        authorCertificateEntity.setCertificateEntity(certificateEntity);
                        if (holder.getCheckModel().getCheckedItems().get(0) == authorEntity)
                            authorCertificateEntity.setHolder(new Integer(1).byteValue());
                        else
                            authorCertificateEntity.setHolder(new Integer(0).byteValue());
                        authorCertificateEntities.add(authorCertificateEntity);
                    }
                    certificateEntity.setAuthorCertificateEntities(new HashSet<AuthorCertificateEntity>(authorCertificateEntities));
                    delete.deleteCertificateById(this.id);
                    update.addCertificate(certificateEntity, authorCertificateEntities);
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

    public void setEdit(CertificateEntity certificateEntity) {
        title.setText("Изменить свидетельство");
        this.id = certificateEntity.getId();
        name.setText(certificateEntity.getName());
        type.getSelectionModel().select(certificateEntity.getTypeCertificateEntity());
        number.setText(certificateEntity.getNumber());
        year.setText(certificateEntity.getYear().toString());
        for (AuthorCertificateEntity authorCertificateEntity : certificateEntity.getAuthorCertificateEntities()) {
            authors.getTargetItems().add(authorCertificateEntity.getAuthorEntity());
            if (authorCertificateEntity.getHolder() == new Integer(1).byteValue())
                holder.getCheckModel().check(authorCertificateEntity.getAuthorEntity());
        }
        for (AuthorCertificateEntity authorCertificateEntity : certificateEntity.getAuthorCertificateEntities()) {
            if (authorCertificateEntity.getHolder() == new Integer(1).byteValue())
                holder.getCheckModel().check(authorCertificateEntity.getAuthorEntity());
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
