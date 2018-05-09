package Wallet;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import static Wallet.TableController.dataList;
import static Wallet.TableController.dateList;

public class GraphController implements Initializable {
    @FXML
   private LineChart <String,Number>lineChart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;


    @FXML
    public void close(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @Override
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("converting...");
        System.out.println(dataList.toString());
        System.out.println(dateList.toString());

         xAxis = new CategoryAxis();
        System.out.println("creating x axis.");
         yAxis = new NumberAxis();
        System.out.println("creating y axis.");

       xAxis .setLabel("Date");
        yAxis.setLabel("Amount");

        xAxis.setCategories(FXCollections.<String> observableArrayList(dateList));
        XYChart.Series XYSeries = new XYChart.Series(dataList);
        XYSeries.setName("Expenses Chart");

        lineChart = new LineChart<>(xAxis,yAxis);
        lineChart.getData().add(XYSeries);
        lineChart.setPrefHeight(600);
        lineChart.setTitle("Expenses Chart");
       // convertToGraph();
    }

}
