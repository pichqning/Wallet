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
   private LineChart <String,Double>lineChart;
    @FXML
    private NumberAxis yaxis;
    @FXML
    private CategoryAxis xaxis;

    private XYChart.Series <String,Double> dataSeries = new XYChart.Series<>();

    private double data2017 , data2018 , data2019 , data2020 , data2021 = 0;





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
    public void initialize(URL location, ResourceBundle resources) {
        loadChart();

    }

    private void loadChart() {
        String[] year;
        for (int i = 0 ; i< dateList.size() ; i++) {
            year = dateList.get(i).split("-");
            addToValueYear(year[0] , datalist.get(0));
        }

        dataSeries.setName("Expenses");
        dataSeries.getData().add(new XYChart.Data<>("2017",data2017));
        dataSeries.getData().add(new XYChart.Data<>("2018", data2018));
        dataSeries.getData().add(new XYChart.Data<>("2019",data2019));
        dataSeries.getData().add(new XYChart.Data<>("2020",data2020));
        dataSeries.getData().add(new XYChart.Data<>("2021",data2021));

        lineChart.getData().add(dataSeries);

    }

    private double addToValueYear (String year , double data) {
        if (year.equals("2017")) return data2017 += data;
        else if (year.equals("2018")) return data2018 += data;
        else if (year.equals("2019")) return data2019 += data;
        else if (year.equals("2020")) return data2020 += data;
        else if (year.equals("2021")) return data2021 += data;
        return 0;
    }


}


