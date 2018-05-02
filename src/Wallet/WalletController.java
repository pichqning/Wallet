package Wallet;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.IllegalFormatException;

public class WalletController extends jdbc {
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
            for (int i = 2017; i <= 2021; i++) {
                year.getItems().add(i);
            }
            year.getSelectionModel().select(0);

        }
        if (month != null) {
            month.getItems().addAll(Month.values());
            month.getSelectionModel().select(0);
        }
        if (date != null) {
            for (int i = 1; i <= 31; i++) {
                date.getItems().add(i);
            }
            date.getSelectionModel().select(0);
        }
    }

    public void InvalidInput(ActionEvent event) {
        //check date unmatch with year (leap year for february) 2020 2024 2028
        //check date unmatch with month (30/31 days)
        if (date.getSelectionModel().getSelectedItem() > month.getSelectionModel().getSelectedItem().length(Year.isLeap(year.getSelectionModel().getSelectedItem()))) {
            date.setStyle("-fx-border-color: red ;");
        }
        //input only number for amount
        try {
            Integer.parseInt(amount.getText());
        } catch (IllegalFormatException e) {
            amount.setStyle("-fx-border-color: red ;");
        }
    }

    public void handleRecord(ActionEvent event) {
        System.out.println("WTF");
        LocalDate date = convertDate();
        //recheck
        try {
            if (categories.getSelectionModel().getSelectedItem().equals("Income")) {
                System.out.println("Income");
                submitRecord("income", amount.getText(), detail.getText(), convertDate());
            } else if (categories.getSelectionModel().equals("Outcome")) {
                submitRecord("outcome", amount.getText(), detail.getText(), convertDate());
            } else if (categories.getSelectionModel().equals("Saving")) {
                submitRecord("saving", amount.getText(), detail.getText(), convertDate());
            } else {
                System.out.println("Else");
            }
        } catch (Exception e) {
            InvalidInput(event);
        }
//        switch (categories.getSelectionModel().getSelectedItem().toString()){
//            case "Income" :
//                submitRecord("income",amount.getText(),detail.getText(),convertDate());
//        }
        Platform.exit();
    }

    //convert selected items to Localdate format.
    public LocalDate convertDate() {
        int Year = year.getSelectionModel().getSelectedItem();
        int Month = month.getSelectionModel().getSelectedItem().getValue();
        int Date = date.getSelectionModel().getSelectedItem();
        String yearmonthdate = Year + "-" + Month + "-" + Date;
//        LocalDate localDate = LocalDate.parse(yearmonthdate);
        LocalDate localDate = LocalDate.of(Year, Month, Date);
        return localDate;
    }
}
