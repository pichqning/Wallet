package Wallet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.time.Month;
import java.time.Year;
import java.util.IllegalFormatException;

public class WalletController {
    @FXML
    private ComboBox categories;
    @FXML
    private ComboBox<Integer> date;
    @FXML
    private ComboBox<Month> month;
    @FXML
    private ComboBox<Integer> year;
    @FXML
    TextField detail;
    @FXML
    TextField amount;

    //private list of month.
    public void initialize() {
        if (categories != null) {
            categories.getItems().addAll("Income", "Outcome", "Saving");
            categories.getSelectionModel().select(0);
        }
        if (year != null) {
            //I cant remember the range of year we set LOL pls fix it if i'm wrong.
            for(int i = 2017; i<= 2021; i++){
                year.getItems().add(i);
            }

        }
        if (month != null) {
            month.getItems().addAll(Month.values());
            categories.getSelectionModel().select(0);
        }
        if (date != null) {
            for (int i = 1; i <= 31; i++) {
                date.getItems().add(i);
            }
        }
    }

    public void InvalidInput(ActionEvent event) {
        //check date unmatch with year (leap year for february) 2020 2024 2028
        //check date unmatch with month (30/31 days)
        if(date.getSelectionModel().getSelectedItem() > month.getSelectionModel().getSelectedItem().length(Year.isLeap(year.getSelectionModel().getSelectedItem()))){
            //return red border.
        }
        //input only number for amount
        try{
            Integer.parseInt(amount.getText());
        }catch (IllegalFormatException e){
            // return red border.
        }
    }

    public void handleRecord(ActionEvent event) {
        //recheck
        // categories.getValue().equals?
//        if (categories.equals("income")) {
//            listIncome.add(date, month, year, detail.getText(), amount.getText());
//        }
//        if (categories.equals("outcome")) {
//            listIncome.add(date, month, year, detail.getText(), amount.getText());
//        }
//        if (categories.equals("Saving")) {
//            listIncome.add(date, month, year, detail.getText(), amount.getText());
//        }
    }

}
