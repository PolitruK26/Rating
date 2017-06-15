package com.rating.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button resize, minimize, close;
    @FXML
    private ToggleButton maximize, listModel, chartModel, reportModel, ratingModel, settingsModel;
    @FXML
    private AnchorPane topMenu;
    @FXML
    private StackPane content;
    @FXML
    private BorderPane window;
    @FXML
    private Separator separator;

    private double initialX;
    private double initialY;

    private double dx;
    private double dy;

    private double windowWidth = 1280;
    private double windowHeight = 720;

    private double currentX;
    private double currentY;

    private AnchorPane listModelView, chartModelView, reportModelView, ratingModelView;
    private BorderPane settingsModelView;

    private Boolean isMaximized = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Rectangle rectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getBounds();
        currentX = rectangle.getWidth() / 2 - 640;
        currentY = rectangle.getHeight() / 2 - 360;

        try {

            listModelView = FXMLLoader.load(getClass().getResource("Views/listView.fxml"));
            chartModelView = FXMLLoader.load(getClass().getResource("Views/chartView.fxml"));
            reportModelView = FXMLLoader.load(getClass().getResource("Views/reportView.fxml"));
            ratingModelView = FXMLLoader.load(getClass().getResource("Views/ratingView.fxml"));
            settingsModelView = FXMLLoader.load(getClass().getResource("Views/settingsView.fxml"));

        } catch (IOException e) {

            e.printStackTrace();

        }

        content.getChildren().add(listModelView);

        topMenu.setOnMousePressed(
                event -> {

                    if (event.getButton() != MouseButton.MIDDLE) {

                        initialX = event.getSceneX();
                        initialY = event.getSceneY();

                    }

                });

        topMenu.setOnMouseDragged(
                event -> {

                    if (event.getButton() != MouseButton.MIDDLE && !isMaximized) {

                        topMenu.getScene().getWindow().setX(event.getScreenX() - initialX);
                        topMenu.getScene().getWindow().setY(event.getScreenY() - initialY);
                        currentX = event.getScreenX() - initialX;
                        currentY = event.getScreenY() - initialY;

                    }

                });

        resize.setOnMousePressed(
                event -> {

                    if (event.getButton() != MouseButton.MIDDLE) {

                        dx = window.getScene().getWindow().getWidth() - event.getSceneX();
                        dy = window.getScene().getWindow().getHeight() - event.getSceneY();

                    }

                });

        resize.setOnMouseDragged(
                event -> {

                    if (event.getButton() != MouseButton.MIDDLE && !isMaximized) {

                        window.getScene().getWindow().setWidth(event.getSceneX() + dx);
                        window.getScene().getWindow().setHeight(event.getSceneY() + dy);
                        windowWidth = event.getSceneX() + dx;
                        windowHeight = event.getSceneY() + dy;

                    }

                    if (window.getScene().getWindow().getWidth() < 1280)
                        window.getScene().getWindow().setWidth(1280);

                    if (window.getScene().getWindow().getHeight() < 720)
                        window.getScene().getWindow().setHeight(720);

                });

        close.setOnMouseClicked(event -> System.exit(0));

        maximize.setOnMouseClicked(event -> {

            Stage stage = (Stage) window.getScene().getWindow();
            if (isMaximized) {

                stage.setWidth(windowWidth);
                stage.setHeight(windowHeight);
                stage.setX(currentX);
                stage.setY(currentY);
                isMaximized = false;
                resize.setVisible(true);

            } else {

                stage.setWidth(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth());
                stage.setHeight(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight());
                stage.setX(0);
                stage.setY(0);
                isMaximized = true;
                resize.setVisible(false);

            }

        });

        minimize.setOnMouseClicked(event -> ((Stage) minimize.getScene().getWindow()).setIconified(true));

        if (Boolean.valueOf(properties.getProperty("system.window"))) {
            close.setVisible(false);
            maximize.setVisible(false);
            minimize.setVisible(false);
            separator.setVisible(false);
            AnchorPane.setRightAnchor(settingsModel, 8.0);
            resize.setVisible(false);

            topMenu.setOnMousePressed(null);
            topMenu.setOnMouseDragged(null);
        }

        listModel.setOnMouseClicked(event -> {
            if (listModel.isSelected()) {
                content.getChildren().clear();
                content.getChildren().add(listModelView);
                settingsModel.setSelected(false);
            } else listModel.setSelected(true);
        });

        chartModel.setOnMouseClicked(event -> {
            if (chartModel.isSelected()) {
                content.getChildren().clear();
                content.getChildren().add(chartModelView);
                settingsModel.setSelected(false);
            } else chartModel.setSelected(true);
        });

        reportModel.setOnMouseClicked(event -> {
            if (reportModel.isSelected()) {
                content.getChildren().clear();
                content.getChildren().add(reportModelView);
                settingsModel.setSelected(false);
            } else reportModel.setSelected(true);
        });

        ratingModel.setOnMouseClicked(event -> {
            if (ratingModel.isSelected()) {
                content.getChildren().clear();
                content.getChildren().add(ratingModelView);
                settingsModel.setSelected(false);
            } else ratingModel.setSelected(true);
        });

        settingsModel.setOnMouseClicked(event -> {
            if (settingsModel.isSelected()) {
                content.getChildren().clear();
                content.getChildren().add(settingsModelView);
                listModel.setSelected(false);
                chartModel.setSelected(false);
                reportModel.setSelected(false);
                ratingModel.setSelected(false);
            } else settingsModel.setSelected(true);
        });

    }

}
