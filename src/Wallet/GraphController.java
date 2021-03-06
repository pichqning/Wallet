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

/**
 * Controller for graphUI, this class has method for add income and outcome value and show in linechart. User can select year that want linechart show .
 * @author Pichaaun Popukdee , Raksani Kunamas
 */

public class GraphController implements Initializable {
    @FXML
    private LineChart <String,Double> lineChart;
    @FXML
    ComboBox year;

    private XYChart.Series <String,Double> dataSeries = new XYChart.Series<>();
    private XYChart.Series <String,Double> dataSeries2 = new XYChart.Series<>();
    /**
     * Parameter for keep value of income and outcome in 12 months
     */
    private double out1 ,out2 ,out3,out4,out5,out6,out7,out8,out9,out10,out11,out12 =0;
    private double in1,in2,in3,in4,in5,in6,in7,in8,in9,in10,in11,in12 = 0 ;

    /**
     * Method for close button.
     * @param event
     */
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
        if (year != null) {
            for (int i = 2017; i <= 2021; i++) {
                year.getItems().add(i);
            }
            year.getSelectionModel().select(0);
        }

        loadChart();

    }

    public void loadChart() {

        loadChartOutcome();
        loadChartIncome();
    }


    /**
     * Create outcome line from dataList which contain outcome amount.
     */
    private void loadChartOutcome() {
        dataSeries.getData().clear();
        String[] year;
        out1=0;out2=0;out3=0;out4=0;out5=0;out6=0;out7=0;out8=0;out9=0;out10=0;out11=0;out12=0;

        for (int i = 0 ; i< dateList.size() ; i++) {
            year = dateList.get(i).split("-");
            if (thisYear(year[0])) addToOutcomeMonth(year[1] , datalist.get(i));
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

    /**
     * Method for create income line.
     */
    private void loadChartIncome() {
        dataSeries2.getData().clear();
        String[] year;
        in1=0;in2=0;in3=0;in4=0;in5=0;in6=0;in7=0;in8=0;in9=0;in10=0;in11=0;in12=0;


        for (int i = 0 ; i< dateList2.size() ; i++) {
            year = dateList2.get(i).split("-");
            if (thisYear(year[0])) addToIncomeMonth(year[1] , dataList2.get(i));
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

    /**
     * Check the year that user want chart show.
     * @param years
     * @return year from combobox , but if the user doesn't select the year will be this year from localdate.
     */
    private boolean thisYear(String years) {

        String now = String.valueOf(year.getSelectionModel().getSelectedItem());
        String date = LocalDate.now().toString();
        String[] dates = date.split("-");
        if (now == null) now = dates[0];
        lineChart.setTitle("Expenses year " + now);
        if (years.equals(now) )return true;
        return false;
    }


    /**
     *
     * Add income value from datalist to parameter we created for keep the income value each month.
     * @param month
     * @param data
     *
     */
    private void addToIncomeMonth ( String month , double data) {
        if (month.equals("01")) in1 += data;
        else if (month.equals("02")) in2 += data;
        else if (month.equals("03")) in3 += data;
        else if (month.equals("04")) in4 += data;
        else if (month.equals("05")) in5 += data;
        else if (month.equals("06")) in6 += data;
        else if (month.equals("07")) in7+= data;
        else if (month.equals("08")) in8 += data;
        else if (month.equals("09")) in9 += data;
        else if (month.equals("10")) in10 += data;
        else if (month.equals("11")) in11+= data;
        else if (month.equals("12")) in12 += data;
    }

    /**
     * Add income value from datalist to parameter we created for keep the income value each month.
     * @param month
     * @param data
     *
     */
    private void addToOutcomeMonth ( String month , double data) {
        if (month.equals("01")) out1 += data;
        else if (month.equals("02")) out2 += data;
        else if (month.equals("03")) out3 += data;
        else if (month.equals("04")) out4 += data;
        else if (month.equals("05")) out5 += data;
        else if (month.equals("06")) out6 += data;
        else if (month.equals("07")) out7 += data;
        else if (month.equals("08")) out8 += data;
        else if (month.equals("09")) out9 += data;
        else if (month.equals("10")) out10 += data;
        else if (month.equals("11")) out11+= data;
        else if (month.equals("12")) out12 += data;
    }
}


