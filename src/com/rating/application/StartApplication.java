package com.rating.application;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class StartApplication extends Application {

    private Stage stage;
    private Scene scene;

    public static void main(String[] args) {

        LauncherImpl.launchApplication(StartApplication.class, PreloadApplication.class, args);

    }

    @Override
    public void init() throws Exception {

        File config = new File("config.properties");
        if (!config.exists()) {
            config.createNewFile();
            Properties properties = new Properties();
            properties.setProperty("system.window", "false");
            properties.setProperty("rating.level.international", "0");
            properties.setProperty("rating.level.allrussian", "0");
            properties.setProperty("rating.level.regionalbig", "0");
            properties.setProperty("rating.level.regionallittle", "0");
            properties.setProperty("rating.level.municipal", "0");
            properties.setProperty("rating.sb.rinc", "0");
            properties.setProperty("rating.sb.vak", "0");
            properties.setProperty("rating.sb.scopus", "0");
            properties.setProperty("rating.sb.web", "0");
            properties.setProperty("rating.tp.abstracts", "0");
            properties.setProperty("rating.tp.article", "0");
            properties.setProperty("rating.tp.monograph", "0");
            properties.setProperty("rating.tp.developments", "0");
            properties.setProperty("rating.tp.tutorial", "0");
            properties.setProperty("rating.tp.depositing", "0");
            properties.setProperty("rating.tp.invention", "0");
            properties.setProperty("rating.tp.sample", "0");
            properties.setProperty("rating.tc.model", "0");
            properties.setProperty("rating.tc.db", "0");
            properties.setProperty("rating.tc.computer", "0");
            properties.store(new FileOutputStream(config), null);
        }

        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader.load(getClass().getResource("mainView.fxml"));
        } catch (Exception e) {
            Platform.exit();
        } finally {
            scene = new Scene(FXMLLoader.load(getClass().getResource("mainView.fxml")));
            scene.getStylesheets().add("com/rating/application/StyleSheet/colors.css");
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.stage = primaryStage;

        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));

        primaryStage.setScene(scene);

        if (!Boolean.valueOf(properties.getProperty("system.window"))) {
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            Rectangle rectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getBounds();
            primaryStage.setX(rectangle.getWidth() / 2 - 640);
            primaryStage.setY(rectangle.getHeight() / 2 - 360);
        } else {
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.setMinWidth(800);
            primaryStage.setMinHeight(600);
        }
        primaryStage.setTitle("Оценка эффективности научной деятельности");
        primaryStage.getIcons().add(new Image("com/rating/application/Images/icon.png"));
        primaryStage.show();

    }

}
