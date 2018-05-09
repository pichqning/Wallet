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

import static Wallet.TableController.datalist;
import static Wallet.TableController.dateList;

public class GraphController implements Initializable {
    @FXML
   private LineChart <String,Number>lineChart;
    @FXML
    private NumberAxis yaxis;
    @FXML
    private CategoryAxis xaxis;


    @FXML
    public void close(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @Override
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("converting...");
        System.out.println(datalist.toString());
        System.out.println(dateList.toString());

        xaxis = new CategoryAxis();
        System.out.println("creating x axis.");
        yaxis = new NumberAxis();
        System.out.println("creating y axis.");

        xaxis.setLabel("Date");
        yaxis.setLabel("Amount");

        xaxis.setCategories(FXCollections.<String> observableArrayList(dateList));
        XYChart.Series XYSeries = new XYChart.Series(datalist);
        XYSeries.setName("Expenses Chart");

        lineChart = new LineChart<>(xaxis,yaxis);
        lineChart.getData().add(XYSeries);
        lineChart.setPrefHeight(600);
        lineChart.setTitle("Expenses Chart");
       // convertToGraph();
    }
    
}
