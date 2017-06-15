package com.rating.application;

import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;

public class PreloadApplication extends Preloader {

    private Stage preloadStage;
    private Scene scene;

    @Override
    public void init() throws Exception {

        Platform.runLater(() -> {

            ProgressIndicator progressIndicator = new ProgressIndicator(-1);
            StackPane.setMargin(progressIndicator, new Insets(5));
            StackPane stackPane = new StackPane(progressIndicator, new Label("Загрузка оценки\nэффективности\nнаучной\nдеятельности"));
            stackPane.setBackground(null);

            scene = new Scene(stackPane, 200, 200, Color.TRANSPARENT);
            scene.getStylesheets().add("com/rating/application/StyleSheet/preloadStyle.css");

        });

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.preloadStage = primaryStage;

        preloadStage.setScene(scene);
        preloadStage.initStyle(StageStyle.TRANSPARENT);
        Rectangle rectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getBounds();
        preloadStage.setX(rectangle.getWidth() / 2 - 100);
        preloadStage.setY(rectangle.getHeight() / 2 - 100);
        preloadStage.setTitle("Оценка эффективности научной деятельности");
        preloadStage.getIcons().add(new Image("com/rating/application/Images/icon.png"));
        preloadStage.show();

    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {

        switch (info.getType()) {
            case BEFORE_LOAD:
                break;
            case BEFORE_INIT:
                break;
            case BEFORE_START:
                preloadStage.hide();
                break;
        }

    }

}
