package com.rating.application.Views;

import com.rating.application.Reports.JRPrintPreview;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.swing.JRViewer;

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
        } catch (ClassNotFoundException | SQLException | JRException e) {
            e.printStackTrace();
        }


        comboBox.setItems(FXCollections.observableArrayList(
                "Публикации",
                "Авторы"
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
            }
        });

    }
}
