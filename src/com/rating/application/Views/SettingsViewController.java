package com.rating.application.Views;

import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class SettingsViewController implements Initializable {

    @FXML
    private Button defaultButton, saveButton, cancelButton;

    @FXML
    private JFXMasonryPane content;

    private Properties properties;
    private File config;

    private JFXToggleButton systemWindow;

    private JFXTextField levelInternational = new JFXTextField();
    private JFXTextField levelAllRussian = new JFXTextField();
    private JFXTextField levelRegionalBig = new JFXTextField();
    private JFXTextField levelRegionalLittle = new JFXTextField();
    private JFXTextField levelMunicipal = new JFXTextField();

    private JFXTextField sbRINC = new JFXTextField();
    private JFXTextField sbVAK = new JFXTextField();
    private JFXTextField sbScopus = new JFXTextField();
    private JFXTextField sbWeb = new JFXTextField();

    private JFXTextField tpAbstracts = new JFXTextField();
    private JFXTextField tpArticle = new JFXTextField();
    private JFXTextField tpMonograph = new JFXTextField();
    private JFXTextField tpDevelopments = new JFXTextField();
    private JFXTextField tpTutorial = new JFXTextField();
    private JFXTextField tpDepositing = new JFXTextField();

    private JFXTextField tpInvention = new JFXTextField();
    private JFXTextField tpSample = new JFXTextField();

    private JFXTextField tcModel = new JFXTextField();
    private JFXTextField tcDB = new JFXTextField();
    private JFXTextField tcComputer = new JFXTextField();

    private JFXTextField year = new JFXTextField();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        config = new File("config.properties");
        properties = new Properties();
        try {
            properties.load(new FileInputStream(config));
        } catch (IOException e) {
            e.printStackTrace();
        }

        systemWindow = new JFXToggleButton();
        systemWindow.setFocusTraversable(false);
        systemWindow.setText("Системный вид окон");
        systemWindow.setToggleColor(Color.web("#f6f6f7"));
        systemWindow.setToggleLineColor(Color.web("#7289da"));
        systemWindow.setUnToggleColor(Color.web("#f6f6f7"));
        systemWindow.setUnToggleLineColor(Color.web("#72767d"));
        systemWindow.setSelected(Boolean.valueOf(properties.getProperty("system.window")));
        systemWindow.setOnAction(event -> {
            defaultButton.setDisable(false);
            saveButton.setDisable(false);
            cancelButton.setDisable(false);
        });
        Label generalLabel = new Label("Настройки приложения:");
        generalLabel.getStyleClass().add("label-big");
        Button plus = new Button("+");
        plus.setPrefHeight(25);
        plus.setPrefWidth(25);
        plus.setOnAction(event -> {
            Integer integer = new Integer(0);
            switch (year.getText().length()) {
                case 2:
                    integer = Integer.valueOf(year.getText().substring(0, 1));
                    break;
                case 3:
                    integer = Integer.valueOf(year.getText().substring(0, 2));
                    break;
                case 4:
                    integer = Integer.valueOf(year.getText().substring(0, 3));
                    break;
            }
            if (integer < 100)
                year.setText(integer + 1 + "%");
            else year.setText("100%");
        });
        Button minus = new Button("-");
        minus.setPrefHeight(25);
        minus.setPrefWidth(25);
        minus.setOnAction(event -> {
            Integer integer = new Integer(0);
            switch (year.getText().length()) {
                case 2:
                    integer = Integer.valueOf(year.getText().substring(0, 1));
                    break;
                case 3:
                    integer = Integer.valueOf(year.getText().substring(0, 2));
                    break;
                case 4:
                    integer = Integer.valueOf(year.getText().substring(0, 3));
                    break;
            }
            if (integer > 0)
                year.setText(integer - 1 + "%");
            else year.setText("0%");
        });
        setTextField(year, null, "rating.down");
        year.setAlignment(Pos.CENTER);
        year.setDisable(true);
        HBox hBox = new HBox(minus, year, plus);

        Label yearDown = new Label("Годовой коэффициент понижения:");
        yearDown.getStyleClass().add("label-big");
        VBox general = new VBox(generalLabel, systemWindow, yearDown, hBox);

        setTextField(levelInternational, "Международная", "rating.level.international");
        setTextField(levelAllRussian, "Всероссийская", "rating.level.allrussian");
        setTextField(levelRegionalBig, "Региональная", "rating.level.regionalbig");
        setTextField(levelRegionalLittle, "Областная", "rating.level.regionallittle");
        setTextField(levelMunicipal, "Муниципальная", "rating.level.municipal");

        Label levelConference = new Label("Уровни конференции:");
        levelConference.getStyleClass().add("label-big");
        VBox level = new VBox(levelConference, levelInternational, levelAllRussian, levelRegionalBig, levelRegionalLittle, levelMunicipal);
        level.setSpacing(20);
        level.setPrefWidth(200);

        setTextField(sbRINC, "РИНЦ", "rating.sb.rinc");
        setTextField(sbVAK, "ВАК", "rating.sb.vak");
        setTextField(sbScopus, "Scopus", "rating.sb.scopus");
        setTextField(sbWeb, "Web of Science", "rating.sb.web");

        Label sbName = new Label("Наукометрические базы:");
        sbName.getStyleClass().add("label-big");
        VBox sb = new VBox(sbName, sbRINC, sbVAK, sbScopus, sbWeb);
        sb.setSpacing(20);
        sb.setPrefWidth(200);

        setTextField(tpAbstracts, "Тезисы докладов и выступлений", "rating.tp.abstracts");
        setTextField(tpArticle, "Научная статья", "rating.tp.article");
        setTextField(tpMonograph, "Монография", "rating.tp.monograph");
        setTextField(tpDevelopments, "Методические разработки", "rating.tp.developments");
        setTextField(tpTutorial, "Учебное пособие", "rating.tp.tutorial");
        setTextField(tpDepositing, "Депонирование", "rating.tp.depositing");

        Label tpName = new Label("Виды публикаций:");
        tpName.getStyleClass().add("label-big");
        VBox tp = new VBox(tpName, tpAbstracts, tpArticle, tpMonograph, tpDevelopments, tpTutorial, tpDepositing);
        tp.setSpacing(20);
        tp.setPrefWidth(200);

        setTextField(tpInvention, "На изобретение", "rating.tp.invention");
        setTextField(tpSample, "На промышленный образец", "rating.tp.sample");

        Label tpaName = new Label("Виды патентов:");
        tpaName.getStyleClass().add("label-big");
        VBox tpa = new VBox(tpaName, tpInvention, tpSample);
        tpa.setSpacing(20);
        tpa.setPrefWidth(200);

        setTextField(tcModel, "На полезную модель", "rating.tc.model");
        setTextField(tcDB, "О регистрации базы данных", "rating.tc.db");
        setTextField(tcComputer, "О регистрации программы для ЭВМ", "rating.tc.computer");

        Label tcName = new Label("Виды свидетельств:");
        tcName.getStyleClass().add("label-big");
        VBox tc = new VBox(tcName, tcModel, tcDB, tcComputer);
        tc.setSpacing(20);
        tc.setPrefWidth(200);

        content.getChildren().addAll(
                general,
                level,
                sb,
                tp,
                tpa,
                tc
        );

        defaultButton.setOnMouseClicked(event -> {
            saveButton.setDisable(false);
            cancelButton.setDisable(false);

            systemWindow.setSelected(false);
            year.setText("50%");
            levelInternational.setText("0");
            levelAllRussian.setText("0");
            levelRegionalBig.setText("0");
            levelRegionalLittle.setText("0");
            levelMunicipal.setText("0");
            sbRINC.setText("0");
            sbVAK.setText("0");
            sbScopus.setText("0");
            sbWeb.setText("0");
            tpAbstracts.setText("0");
            tpArticle.setText("0");
            tpMonograph.setText("0");
            tpDevelopments.setText("0");
            tpTutorial.setText("0");
            tpDepositing.setText("0");
            tpInvention.setText("0");
            tpSample.setText("0");
            tcModel.setText("0");
            tcDB.setText("0");
            tcComputer.setText("0");
        });

        saveButton.setOnMouseClicked(event -> {
            saveButton.setDisable(true);
            cancelButton.setDisable(true);

            properties.setProperty("system.window", String.valueOf(systemWindow.isSelected()));
            properties.setProperty("rating.down", year.getText());
            properties.setProperty("rating.level.international", levelInternational.getText());
            properties.setProperty("rating.level.allrussian", levelAllRussian.getText());
            properties.setProperty("rating.level.regionalbig", levelRegionalBig.getText());
            properties.setProperty("rating.level.regionallittle", levelRegionalLittle.getText());
            properties.setProperty("rating.level.municipal", levelMunicipal.getText());
            properties.setProperty("rating.sb.rinc", sbRINC.getText());
            properties.setProperty("rating.sb.vak", sbVAK.getText());
            properties.setProperty("rating.sb.scopus", sbScopus.getText());
            properties.setProperty("rating.sb.web", sbWeb.getText());
            properties.setProperty("rating.tp.abstracts", tpAbstracts.getText());
            properties.setProperty("rating.tp.article", tpArticle.getText());
            properties.setProperty("rating.tp.monograph", tpMonograph.getText());
            properties.setProperty("rating.tp.developments", tpDevelopments.getText());
            properties.setProperty("rating.tp.tutorial", tpTutorial.getText());
            properties.setProperty("rating.tp.depositing", tpDepositing.getText());
            properties.setProperty("rating.tp.invention", tpInvention.getText());
            properties.setProperty("rating.tp.sample", tpSample.getText());
            properties.setProperty("rating.tc.model", tcModel.getText());
            properties.setProperty("rating.tc.db", tcDB.getText());
            properties.setProperty("rating.tc.computer", tcComputer.getText());

            try {
                properties.store(new FileOutputStream(config), null);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(saveButton.getScene().getWindow());
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setTitle("Уведомление");
            alert.setHeaderText("Для приминения изменений требуется перезагрузка");
            alert.setContentText(null);
            ButtonType reload = new ButtonType("Перезагрузить");
            ButtonType exit = new ButtonType("Закрыть");
            alert.getButtonTypes().setAll(reload, exit);

            alert.showAndWait();

            if (alert.getResult() == reload) {
                Platform.runLater(() -> {
                    try {
                        Runtime.getRuntime().exec("Reloader.exe");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Platform.exit();
                });
            }

        });

        cancelButton.setOnMouseClicked(event -> {
            saveButton.setDisable(true);
            cancelButton.setDisable(true);

            systemWindow.setSelected(Boolean.valueOf(properties.getProperty("system.window")));
            year.setText(properties.getProperty("rating.down"));
            levelInternational.setText(properties.getProperty("rating.level.international"));
            levelAllRussian.setText(properties.getProperty("rating.level.allrussian"));
            levelRegionalBig.setText(properties.getProperty("rating.level.regionalbig"));
            levelRegionalLittle.setText(properties.getProperty("rating.level.regionallittle"));
            levelMunicipal.setText(properties.getProperty("rating.level.municipal"));
            sbRINC.setText(properties.getProperty("rating.sb.rinc"));
            sbVAK.setText(properties.getProperty("rating.sb.vak"));
            sbScopus.setText(properties.getProperty("rating.sb.scopus"));
            sbWeb.setText(properties.getProperty("rating.sb.web"));
            tpAbstracts.setText(properties.getProperty("rating.tp.abstracts"));
            tpArticle.setText(properties.getProperty("rating.tp.article"));
            tpMonograph.setText(properties.getProperty("rating.tp.monograph"));
            tpDevelopments.setText(properties.getProperty("rating.tp.developments"));
            tpTutorial.setText(properties.getProperty("rating.tp.tutorial"));
            tpDepositing.setText(properties.getProperty("rating.tp.depositing"));
            tpInvention.setText(properties.getProperty("rating.tp.invention"));
            tpSample.setText(properties.getProperty("rating.tp.sample"));
            tcModel.setText(properties.getProperty("rating.tc.model"));
            tcDB.setText(properties.getProperty("rating.tc.db"));
            tcComputer.setText(properties.getProperty("rating.tc.computer"));
        });

    }

    private void setTextField(JFXTextField field, String name, String text) {
        field.setLabelFloat(true);
        field.setPromptText(name);
        field.setText(properties.getProperty(text));
        field.setFocusColor(Color.web("#b4b5b7"));
        field.setUnFocusColor(Color.web("#5d6064"));
        field.setFocusTraversable(false);
        field.textProperty().addListener(observable -> {
            defaultButton.setDisable(false);
            saveButton.setDisable(false);
            cancelButton.setDisable(false);
        });
    }

}
