package com.rating.application.Views;

import com.rating.application.Reports.JRPrintPreview;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import net.sf.jasperreports.engine.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReportViewController implements Initializable {

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private StackPane content;

    private JRPrintPreview jrPrintPreview1;
    private JRPrintPreview jrPrintPreview2;
    private JRPrintPreview jrPrintPreview3;
    private JRPrintPreview jrPrintPreview4;
    private JRPrintPreview jrPrintPreview5;
    private JRPrintPreview jrPrintPreview6;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/publication_analysis", "root", "");
            JasperReport jasperReport1 = JasperCompileManager.compileReport(getClass().getResourceAsStream("/com/rating/application/Reports/report1.jrxml"));
            JasperPrint jasperPrint1 = JasperFillManager.fillReport(jasperReport1, null, connection);
            jrPrintPreview1 = new JRPrintPreview(jasperPrint1);
            JasperReport jasperReport2 = JasperCompileManager.compileReport(getClass().getResourceAsStream("/com/rating/application/Reports/report2.jrxml"));
            JasperPrint jasperPrint2 = JasperFillManager.fillReport(jasperReport2, null, connection);
            jrPrintPreview2 = new JRPrintPreview(jasperPrint2);
            JasperReport jasperReport3 = JasperCompileManager.compileReport(getClass().getResourceAsStream("/com/rating/application/Reports/report3.jrxml"));
            JasperPrint jasperPrint3 = JasperFillManager.fillReport(jasperReport3, null, connection);
            jrPrintPreview3 = new JRPrintPreview(jasperPrint3);
            JasperReport jasperReport4 = JasperCompileManager.compileReport(getClass().getResourceAsStream("/com/rating/application/Reports/report4.jrxml"));
            JasperPrint jasperPrint4 = JasperFillManager.fillReport(jasperReport4, null, connection);
            jrPrintPreview4 = new JRPrintPreview(jasperPrint4);
            JasperReport jasperReport5 = JasperCompileManager.compileReport(getClass().getResourceAsStream("/com/rating/application/Reports/report5.jrxml"));
            JasperPrint jasperPrint5 = JasperFillManager.fillReport(jasperReport5, null, connection);
            jrPrintPreview5 = new JRPrintPreview(jasperPrint5);
            JasperReport jasperReport6 = JasperCompileManager.compileReport(getClass().getResourceAsStream("/com/rating/application/Reports/report6.jrxml"));
            JasperPrint jasperPrint6 = JasperFillManager.fillReport(jasperReport6, null, connection);
            jrPrintPreview6 = new JRPrintPreview(jasperPrint6);
        } catch (ClassNotFoundException | SQLException | JRException e) {
            e.printStackTrace();
        }

        comboBox.setItems(FXCollections.observableArrayList(
                "Публикации",
                "Авторы",
                "Патенты",
                "Свидетельства",
                "Сборники",
                "Конференции"
        ));

        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case "Публикации":
                    content.getChildren().clear();
                    content.getChildren().add(jrPrintPreview1);
                    break;
                case "Авторы":
                    content.getChildren().clear();
                    content.getChildren().add(jrPrintPreview2);
                    break;
                case "Патенты":
                    content.getChildren().clear();
                    content.getChildren().add(jrPrintPreview3);
                    break;
                case "Свидетельства":
                    content.getChildren().clear();
                    content.getChildren().add(jrPrintPreview4);
                    break;
                case "Сборники":
                    content.getChildren().clear();
                    content.getChildren().add(jrPrintPreview5);
                    break;
                case "Конференции":
                    content.getChildren().clear();
                    content.getChildren().add(jrPrintPreview6);
                    break;
            }
        });

    }
}
