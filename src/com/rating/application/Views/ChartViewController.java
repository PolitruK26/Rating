package com.rating.application.Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChartViewController implements Initializable {

    @FXML
    private StackPane content;

    @FXML
    private ComboBox<String> comboBox;

    private BarChart chart1;
    private BarChart chart2;
    private BarChart chart3;
    private PieChart chart4;
    private PieChart chart5;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        XYChart.Series series1 = new XYChart.Series<>();
        XYChart.Series series2 = new XYChart.Series<>();
        XYChart.Series series3 = new XYChart.Series<>();
        ObservableList<PieChart.Data> series4 = FXCollections.observableArrayList();
        ObservableList<PieChart.Data> series5 = FXCollections.observableArrayList();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/publication_analysis", "root", "");
            ResultSet resultSet1 = connection.createStatement().executeQuery("SELECT publication_Year,count(ID_publication) FROM publication GROUP BY publication_Year");
            ResultSet resultSet2 = connection.createStatement().executeQuery("SELECT certificate_Year,count(ID_certificate) FROM certificate GROUP BY certificate_Year");
            ResultSet resultSet3 = connection.createStatement().executeQuery("SELECT patent_Year,count(ID_patent) FROM patent GROUP BY patent_Year");
            ResultSet resultSet4 = connection.createStatement().executeQuery("SELECT position_author.position_Name,count(author.ID_author) FROM author INNER JOIN position_author ON author.ID_position = position_author.ID_position GROUP BY position_Name");
            ResultSet resultSet5 = connection.createStatement().executeQuery("SELECT type_publication.type_publication_Name,count(publication.ID_publication) FROM publication INNER JOIN type_publication ON publication.ID_type_publication = type_publication.ID_type_publication GROUP BY type_publication_Name");
            while (resultSet1.next()) {
                series1.getData().add(new XYChart.Data(String.valueOf(resultSet1.getInt(1)), resultSet1.getInt(2)));
            }
            while (resultSet2.next()) {
                series2.getData().add(new XYChart.Data(String.valueOf(resultSet2.getInt(1)), resultSet2.getInt(2)));
            }
            while (resultSet3.next()) {
                series3.getData().add(new XYChart.Data(String.valueOf(resultSet3.getInt(1)), resultSet3.getInt(2)));
            }
            while (resultSet4.next()) {
                series4.add(new PieChart.Data(resultSet4.getString(1), resultSet4.getInt(2)));
            }
            while (resultSet5.next()) {
                series5.add(new PieChart.Data(resultSet5.getString(1), resultSet5.getInt(2)));
            }
            chart1 = new BarChart(new CategoryAxis(), new NumberAxis());
            chart1.getData().add(series1);
            chart1.setLegendVisible(false);
            chart2 = new BarChart(new CategoryAxis(), new NumberAxis());
            chart2.getData().add(series2);
            chart2.setLegendVisible(false);
            chart3 = new BarChart(new CategoryAxis(), new NumberAxis());
            chart3.getData().add(series3);
            chart3.setLegendVisible(false);
            chart4 = new PieChart(series4);
            chart4.setLegendVisible(false);
            chart5 = new PieChart(series5);
            chart5.setLegendVisible(false);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        MenuItem menuItem = new MenuItem("Сохранить в png");
        menuItem.setOnAction(event -> {
            WritableImage image = chart1.snapshot(new SnapshotParameters(), null);
            File file = new File("chart.png");
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException e) {
            }
        });
        ContextMenu contextMenu = new ContextMenu(menuItem);

        chart1.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.SECONDARY))
                contextMenu.show(chart1, event.getScreenX(), event.getScreenY());
        });

        comboBox.setItems(FXCollections.observableArrayList(
                "Количество публикаций по годам",
                "Количество свидетельств по годам",
                "Количество патентов по годам",
                "Количество авторов по должностям",
                "Количество публикаций по видам"
        ));

        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case "Количество публикаций по годам":
                    content.getChildren().clear();
                    content.getChildren().add(chart1);
                    break;
                case "Количество свидетельств по годам":
                    content.getChildren().clear();
                    content.getChildren().add(chart2);
                    break;
                case "Количество патентов по годам":
                    content.getChildren().clear();
                    content.getChildren().add(chart3);
                    break;
                case "Количество авторов по должностям":
                    content.getChildren().clear();
                    content.getChildren().add(chart4);
                    break;
                case "Количество публикаций по видам":
                    content.getChildren().clear();
                    content.getChildren().add(chart5);
                    break;
            }
        });

    }

}
