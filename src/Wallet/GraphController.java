package Wallet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

import static Wallet.TableController.*;

public class GraphController implements Initializable {
    @FXML
   private LineChart <String,Double>lineChart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;

    private XYChart.Series <String,Double> dataSeries = new XYChart.Series<>();
    private XYChart.Series <String,Double> dataSeries2 = new XYChart.Series<>();

    private double outcome2017, outcome2018, outcome2019, outcome2020, outcome2021 = 0;
    private double income2017,income2018,income2019,income2020,income2021 = 0 ;





    @FXML
    public void close(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        loadChartOutcome();
        loadChartIncome();

    }

    private void loadChartOutcome() {
        String[] year;
        for (int i = 0 ; i< dateList.size() ; i++) {
            year = dateList.get(i).split("-");
            addToOutcomeYear(year[0] , datalist.get(0));
        }

        dataSeries.setName("Outcome");
        dataSeries.getData().add(new XYChart.Data<>("2017", outcome2017));
        dataSeries.getData().add(new XYChart.Data<>("2018", outcome2018));
        dataSeries.getData().add(new XYChart.Data<>("2019", outcome2019));
        dataSeries.getData().add(new XYChart.Data<>("2020", outcome2020));
        dataSeries.getData().add(new XYChart.Data<>("2021", outcome2021));

        lineChart.getData().add(dataSeries);

    }

    private void loadChartIncome() {
        String[] year;
        for (int i = 0 ; i< dateList2.size() ; i++) {
            year = dateList2.get(i).split("-");
            addToIncomeYear(year[0] , dataList2.get(0));
        }

        dataSeries2.setName("Income");
        dataSeries2.getData().add(new XYChart.Data<>("2017", income2017));
        dataSeries2.getData().add(new XYChart.Data<>("2018", income2018));
        dataSeries2.getData().add(new XYChart.Data<>("2019", income2019));
        dataSeries2.getData().add(new XYChart.Data<>("2020", income2020));
        dataSeries2.getData().add(new XYChart.Data<>("2021", income2021));

        lineChart.getData().add(dataSeries2);


    }

    private double addToOutcomeYear (String year , double data) {
        if (year.equals("2017")) return outcome2017 += data;
        else if (year.equals("2018")) return outcome2018 += data;
        else if (year.equals("2019")) return outcome2019 += data;
        else if (year.equals("2020")) return outcome2020 += data;
        else if (year.equals("2021")) return outcome2021 += data;
        return 0;
    }

    private double addToIncomeYear (String year , double data) {
        if (year.equals("2017")) return income2017 += data;
        else if (year.equals("2018")) return income2018 += data;
        else if (year.equals("2019")) return income2019 += data;
        else if (year.equals("2020")) return income2020 += data;
        else if (year.equals("2021")) return income2021 += data;
        return 0;
    }


}


