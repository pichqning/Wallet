package Wallet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;

import static Wallet.TableController.*;

public class GraphController implements Initializable {
    @FXML
    private LineChart <String,Double> lineChart;
    @FXML
    ComboBox year;

    private XYChart.Series <String,Double> dataSeries = new XYChart.Series<>();
    private XYChart.Series <String,Double> dataSeries2 = new XYChart.Series<>();
    private double income2017,income2018,income2019,income2020,income2021 = 0 ;
    private double out1 ,out2 ,out3,out4,out5,out6,out7,out8,out9,out10,out11,out12 =0;
    private double in1,in2,in3,in4,in5,in6,in7,in8,in9,in10,in11,in12 = 0 ;





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
        addYear();
        loadChartOutcome();
        loadChartIncome();


    }

    private void addYear() {
        if (year != null) {
            //I cant remember the range of year we set LOL pls fix it if i'm wrong.
            for (int i = 2017; i <= 2021; i++) {
                year.getItems().add(i);
            }
            year.getSelectionModel().select(0);

        }
    }


    private void loadChartOutcome() {
        String[] year;
        for (int i = 0 ; i< dateList.size() ; i++) {
            year = dateList.get(i).split("-");
            if (thisYear(year[0])) addToOutcomeMonth(year[0] , datalist.get(0));
        }

        dataSeries.setName("Outcome");
        dataSeries.getData().add(new XYChart.Data<>(Month.JANUARY.toString(),out1));
        dataSeries.getData().add(new XYChart.Data<>(Month.FEBRUARY.toString(),out2));
        dataSeries.getData().add(new XYChart.Data<>(Month.MARCH.toString(),out3));
        dataSeries.getData().add(new XYChart.Data<>(Month.APRIL.toString(),out4));
        dataSeries.getData().add(new XYChart.Data<>(Month.MAY.toString(),out5));
        dataSeries.getData().add(new XYChart.Data<>(Month.JUNE.toString(),out6));
        dataSeries.getData().add(new XYChart.Data<>(Month.JULY.toString(),out7));
        dataSeries.getData().add(new XYChart.Data<>(Month.AUGUST.toString(),out8));
        dataSeries.getData().add(new XYChart.Data<>(Month.SEPTEMBER.toString(),out9));
        dataSeries.getData().add(new XYChart.Data<>(Month.OCTOBER.toString(),out10));
        dataSeries.getData().add(new XYChart.Data<>(Month.NOVEMBER.toString(),out11));
        dataSeries.getData().add(new XYChart.Data<>(Month.DECEMBER.toString(),out12));

        lineChart.getData().add(dataSeries);

    }

    private void loadChartIncome() {
        String[] year;
        for (int i = 0 ; i< dateList2.size() ; i++) {
            year = dateList2.get(i).split("-");
            if (thisYear(year[0])) addToIncomeMonth(year[0] , dataList2.get(0));
        }

        dataSeries2.setName("Income");
        dataSeries2.getData().add(new XYChart.Data<>(Month.JANUARY.toString(),in1));
        dataSeries2.getData().add(new XYChart.Data<>(Month.FEBRUARY.toString(),in2));
        dataSeries2.getData().add(new XYChart.Data<>(Month.MARCH.toString(),in3));
        dataSeries2.getData().add(new XYChart.Data<>(Month.APRIL.toString(),in4));
        dataSeries2.getData().add(new XYChart.Data<>(Month.MAY.toString(),in5));
        dataSeries2.getData().add(new XYChart.Data<>(Month.JUNE.toString(),in6));
        dataSeries2.getData().add(new XYChart.Data<>(Month.JULY.toString(),in7));
        dataSeries2.getData().add(new XYChart.Data<>(Month.AUGUST.toString(),in8));
        dataSeries2.getData().add(new XYChart.Data<>(Month.SEPTEMBER.toString(),in9));
        dataSeries2.getData().add(new XYChart.Data<>(Month.OCTOBER.toString(),in10));
        dataSeries2.getData().add(new XYChart.Data<>(Month.NOVEMBER.toString(),in11));
        dataSeries2.getData().add(new XYChart.Data<>(Month.DECEMBER.toString(),in12));

        lineChart.getData().add(dataSeries2);

    }

    private boolean thisYear(String years) {
        String now = String.valueOf(year.getSelectionModel().getSelectedItem());
        String date = LocalDate.now().toString();
        String[] dates = date.split("-");
        if (now == null) now = dates[0];
        lineChart.setTitle("Expenses year" + now);
        if (years.equals(now) )return true;
        return false;
    }

    private double addToIncomeMonth ( String month , double data) {
        if (month.equals("01")) return in1 += data;
        else if (month.equals("02")) return in2 += data;
        else if (month.equals("03")) return in3 += data;
        else if (month.equals("04")) return in4 += data;
        else if (month.equals("05")) return in5 += data;
        else if (month.equals("06")) return in6 += data;
        else if (month.equals("07")) return in7+= data;
        else if (month.equals("08")) return in8 += data;
        else if (month.equals("09")) return in9 += data;
        else if (month.equals("10")) return in10 += data;
        else if (month.equals("11")) return in11+= data;
        else if (month.equals("12")) return in12 += data;
        return 0;
    }

    private double addToOutcomeMonth ( String month , double data) {
        if (month.equals("01")) return out1 += data;
        else if (month.equals("02")) return out2 += data;
        else if (month.equals("03")) return out3 += data;
        else if (month.equals("04")) return out4 += data;
        else if (month.equals("05")) return out5 += data;
        else if (month.equals("06")) return out6 += data;
        else if (month.equals("07")) return out7 += data;
        else if (month.equals("08")) return out8 += data;
        else if (month.equals("09")) return out9 += data;
        else if (month.equals("10")) return out10 += data;
        else if (month.equals("11")) return out11+= data;
        else if (month.equals("12")) return out12 += data;
        return 0;
    }
}


