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
    private PieChart chart6;

    private final String query1 = "SELECT publication_Year,count(ID_publication) FROM publication GROUP BY publication_Year";
    private final String query2 = "SELECT certificate_Year,count(ID_certificate) FROM certificate GROUP BY certificate_Year";
    private final String query3 = "SELECT patent_Year,count(ID_patent) FROM patent GROUP BY patent_Year";
    private final String query4 = "SELECT position_author.position_Name,count(author.ID_author) FROM author INNER JOIN position_author ON author.ID_position = position_author.ID_position GROUP BY position_Name";
    private final String query5 = "SELECT type_publication.type_publication_Name,count(publication.ID_publication) FROM publication INNER JOIN type_publication ON publication.ID_type_publication = type_publication.ID_type_publication GROUP BY type_publication_Name";
    private final String query6 = "SELECT keywords.keywords_Name,COUNT(publication.ID_publication) FROM publication_keywords INNER JOIN publication ON publication_keywords.ID_publication = publication.ID_publication INNER JOIN keywords ON publication_keywords.ID_keywords = keywords.ID_keywords GROUP BY keywords_Name ORDER BY COUNT(publication.ID_publication) DESC LIMIT 5";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        XYChart.Series series1 = new XYChart.Series<>();
        XYChart.Series series2 = new XYChart.Series<>();
        XYChart.Series series3 = new XYChart.Series<>();
        ObservableList<PieChart.Data> series4 = FXCollections.observableArrayList();
        ObservableList<PieChart.Data> series5 = FXCollections.observableArrayList();
        ObservableList<PieChart.Data> series6 = FXCollections.observableArrayList();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/publication_analysis", "root", "");
            ResultSet resultSet1 = connection.createStatement().executeQuery(query1);
            ResultSet resultSet2 = connection.createStatement().executeQuery(query2);
            ResultSet resultSet3 = connection.createStatement().executeQuery(query3);
            ResultSet resultSet4 = connection.createStatement().executeQuery(query4);
            ResultSet resultSet5 = connection.createStatement().executeQuery(query5);
            ResultSet resultSet6 = connection.createStatement().executeQuery(query6);
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
            while (resultSet6.next()) {
                series6.add(new PieChart.Data(resultSet6.getString(1), resultSet6.getInt(2)));
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
            chart6 = new PieChart(series6);
            chart6.setLegendVisible(false);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        initContextMenu(chart1, "Количество публикаций по годам");
        initContextMenu(chart2, "Количество свидетельств по годам");
        initContextMenu(chart3, "Количество патентов по годам");
        initContextMenu(chart4, "Количество авторов по должностям");
        initContextMenu(chart5, "Количество публикаций по видам");
        initContextMenu(chart6, "Пять самых распространненых ключевых слов");

        comboBox.setItems(FXCollections.observableArrayList(
                "Количество публикаций по годам",
                "Количество свидетельств по годам",
                "Количество патентов по годам",
                "Количество авторов по должностям",
                "Количество публикаций по видам",
                "Пять самых распространненых ключевых слов"
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
                case "Пять самых распространненых ключевых слов":
                    content.getChildren().clear();
                    content.getChildren().add(chart6);
                    break;
            }
        });

    }

    void initContextMenu(BarChart chart, String string) {
        MenuItem menuItem = new MenuItem("Сохранить как изображение");
        menuItem.setOnAction(event -> {
            WritableImage image = chart.snapshot(new SnapshotParameters(), null);
            File file = new File("..\\" + string + ".png");
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException e) {
            }
        });
        ContextMenu contextMenu = new ContextMenu(menuItem);

        chart.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.SECONDARY))
                contextMenu.show(chart, event.getScreenX(), event.getScreenY());
        });
    }

    void initContextMenu(PieChart chart, String string) {
        MenuItem menuItem = new MenuItem("Сохранить как изображение");
        menuItem.setOnAction(event -> {
            WritableImage image = chart.snapshot(new SnapshotParameters(), null);
            File file = new File("..\\" + string + ".png");
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException e) {
            }
        });
        ContextMenu contextMenu = new ContextMenu(menuItem);

        chart.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.SECONDARY))
                contextMenu.show(chart, event.getScreenX(), event.getScreenY());
        });
    }

}
