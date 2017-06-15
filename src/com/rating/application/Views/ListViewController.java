package com.rating.application.Views;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListViewController implements Initializable {

    @FXML
    private VBox vbMenu;
    @FXML
    private ToggleButton bPublication;
    @FXML
    private ToggleButton bAuthor;
    @FXML
    private ToggleButton bPatent;
    @FXML
    private ToggleButton bCertificate;
    @FXML
    private ToggleButton bCollection;
    @FXML
    private ToggleButton bConference;
    @FXML
    private StackPane spContent;

    private ToggleButton prev;

    private AnchorPane publicationLoader;
    private AnchorPane authorLoader;
    private AnchorPane patentLoader;
    private AnchorPane certificateLoader;
    private AnchorPane collectionLoader;
    private AnchorPane conferenceLoader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            publicationLoader = FXMLLoader.load(getClass().getResource("ListView/publicationView.fxml"));
            authorLoader = FXMLLoader.load(getClass().getResource("ListView/authorView.fxml"));
            patentLoader = FXMLLoader.load(getClass().getResource("ListView/patentView.fxml"));
            certificateLoader = FXMLLoader.load(getClass().getResource("ListView/certificateView.fxml"));
            collectionLoader = FXMLLoader.load(getClass().getResource("ListView/collectionView.fxml"));
            conferenceLoader = FXMLLoader.load(getClass().getResource("ListView/conferenceView.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        spContent.getChildren().add(publicationLoader);

        prev = bPublication;

        Tooltip tooltipPublication = new Tooltip("Публикации");
        bPublication.setOnMouseClicked(event -> {
            if (prev.isSelected())
                setAnimation(prev).play();
            prev = setNavigate(bPublication, prev, publicationLoader, spContent);
            tooltipPublication.hide();
        });
        bPublication.setOnMouseEntered(event -> {
            tooltipPublication.show(bPublication,
                    bPublication.localToScreen(bPublication.getBoundsInLocal()).getMinX() + 70,
                    bPublication.localToScreen(bPublication.getBoundsInLocal()).getMinY() + 15);
            if (!bPublication.isSelected())
                setAnimationHover(bPublication).play();
        });
        bPublication.setOnMouseExited(event -> {
            tooltipPublication.hide();
            if (!bPublication.isSelected())
                setAnimation(bPublication).play();
        });

        Tooltip tooltipAuthor = new Tooltip("Авторы");
        bAuthor.setOnMouseClicked(event -> {
            if (prev.isSelected())
                setAnimation(prev).play();
            prev = setNavigate(bAuthor, prev, authorLoader, spContent);
            tooltipAuthor.hide();
        });
        bAuthor.setOnMouseEntered(event -> {
            if (!bAuthor.isSelected())
                setAnimationHover(bAuthor).play();
            tooltipAuthor.show(bAuthor,
                    bAuthor.localToScreen(bAuthor.getBoundsInLocal()).getMinX() + 70,
                    bAuthor.localToScreen(bAuthor.getBoundsInLocal()).getMinY() + 15);
        });
        bAuthor.setOnMouseExited(event -> {
            if (!bAuthor.isSelected())
                setAnimation(bAuthor).play();
            tooltipAuthor.hide();
        });

        Tooltip tooltipPatent = new Tooltip("Патенты");
        bPatent.setOnMouseClicked(event -> {
            if (prev.isSelected())
                setAnimation(prev).play();
            prev = setNavigate(bPatent, prev, patentLoader, spContent);
            tooltipPatent.hide();
        });
        bPatent.setOnMouseEntered(event -> {
            if (!bPatent.isSelected())
                setAnimationHover(bPatent).play();
            tooltipPatent.show(bPatent,
                    bPatent.localToScreen(bPatent.getBoundsInLocal()).getMinX() + 70,
                    bPatent.localToScreen(bPatent.getBoundsInLocal()).getMinY() + 15);
        });
        bPatent.setOnMouseExited(event -> {
            if (!bPatent.isSelected())
                setAnimation(bPatent).play();
            tooltipPatent.hide();
        });

        Tooltip tooltipCertificate = new Tooltip("Свидетельства");
        bCertificate.setOnMouseClicked(event -> {
            if (prev.isSelected())
                setAnimation(prev).play();
            prev = setNavigate(bCertificate, prev, certificateLoader, spContent);
            tooltipCertificate.hide();
        });
        bCertificate.setOnMouseEntered(event -> {
            if (!bCertificate.isSelected())
                setAnimationHover(bCertificate).play();
            tooltipCertificate.show(bCertificate,
                    bCertificate.localToScreen(bCertificate.getBoundsInLocal()).getMinX() + 70,
                    bCertificate.localToScreen(bCertificate.getBoundsInLocal()).getMinY() + 15);
        });
        bCertificate.setOnMouseExited(event -> {
            if (!bCertificate.isSelected())
                setAnimation(bCertificate).play();
            tooltipCertificate.hide();
        });

        Tooltip tooltipCollection = new Tooltip("Сборники");
        bCollection.setOnMouseClicked(event -> {
            if (prev.isSelected())
                setAnimation(prev).play();
            prev = setNavigate(bCollection, prev, collectionLoader, spContent);
            tooltipCollection.hide();
        });
        bCollection.setOnMouseEntered(event -> {
            if (!bCollection.isSelected())
                setAnimationHover(bCollection).play();
            tooltipCollection.show(bCollection,
                    bCollection.localToScreen(bCollection.getBoundsInLocal()).getMinX() + 70,
                    bCollection.localToScreen(bCollection.getBoundsInLocal()).getMinY() + 15);
        });
        bCollection.setOnMouseExited(event -> {
            if (!bCollection.isSelected())
                setAnimation(bCollection).play();
            tooltipCollection.hide();
        });

        Tooltip tooltipConference = new Tooltip("Конференции");
        bConference.setOnMouseClicked(event -> {
            if (prev.isSelected())
                setAnimation(prev).play();
            prev = setNavigate(bConference, prev, conferenceLoader, spContent);
            tooltipConference.hide();
        });
        bConference.setOnMouseEntered(event -> {
            if (!bConference.isSelected())
                setAnimationHover(bConference).play();
            tooltipConference.show(bConference,
                    bConference.localToScreen(bConference.getBoundsInLocal()).getMinX() + 70,
                    bConference.localToScreen(bConference.getBoundsInLocal()).getMinY() + 15);
        });
        bConference.setOnMouseExited(event -> {
            if (!bConference.isSelected())
                setAnimation(bConference).play();
            tooltipConference.hide();
        });
    }

    ToggleButton setNavigate(ToggleButton button, ToggleButton prev, AnchorPane loader, StackPane content) {
        if (button.isSelected()) {
            prev.setSelected(false);
            button.setSelected(true);
            prev = button;
            content.getChildren().clear();
            content.getChildren().add(loader);
        } else {
            button.setSelected(true);
        }
        return prev;
    }

    Timeline setAnimationHover(ToggleButton toggleButton) {

        return new Timeline(
                new KeyFrame(Duration.seconds(.05),
                        new KeyValue(toggleButton.styleProperty(), "-fx-background-radius: 28;")
                ),

                new KeyFrame(Duration.seconds(.1),
                        new KeyValue(toggleButton.styleProperty(), "-fx-background-radius: 24;")
                ),

                new KeyFrame(Duration.seconds(.15),
                        new KeyValue(toggleButton.styleProperty(), "-fx-background-radius: 20;")
                ),

                new KeyFrame(Duration.seconds(.2),
                        new KeyValue(toggleButton.styleProperty(), "-fx-background-radius: 16;")
                ),

                new KeyFrame(Duration.seconds(.25),
                        new KeyValue(toggleButton.styleProperty(), "-fx-background-radius: 12;")
                )
        );
    }

    Timeline setAnimation(ToggleButton toggleButton) {
        return new Timeline(
                new KeyFrame(Duration.seconds(.05),
                        new KeyValue(toggleButton.styleProperty(), "-fx-background-radius: 16;")
                ),

                new KeyFrame(Duration.seconds(.1),
                        new KeyValue(toggleButton.styleProperty(), "-fx-background-radius: 20;")
                ),

                new KeyFrame(Duration.seconds(.15),
                        new KeyValue(toggleButton.styleProperty(), "-fx-background-radius: 24;")
                ),

                new KeyFrame(Duration.seconds(.2),
                        new KeyValue(toggleButton.styleProperty(), "-fx-background-radius: 28;")
                ),

                new KeyFrame(Duration.seconds(.25),
                        new KeyValue(toggleButton.styleProperty(), "-fx-background-radius: 32;")
                )
        );
    }

}
