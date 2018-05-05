package Wallet;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.DateTimeException;

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
    @FXML
    Button submit;
    @FXML
    MenuItem daily;
    @FXML
    MenuItem monthly;
    @FXML
    AnchorPane table;
    @FXML
    static TableView tableView;
    @FXML
    static TableColumn idColumn;
    @FXML
    static TableColumn dateColumn;
    @FXML
    static TableColumn detailColumn;
    @FXML
    static TableColumn amountColumn;
    @FXML
    static TableColumn typeColumn;


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

    // TODO fix css.

    public void InvalidInput() {
        //check date unmatch with year (leap year for february) 2020 2024 2028
        //check date unmatch with month (30/31 days)
            if (date.getSelectionModel().getSelectedItem() > month.getSelectionModel().getSelectedItem().length(Year.isLeap(year.getSelectionModel().getSelectedItem()))) {
                date.setStyle("-fx-border-color: #ff0000 ;");
            }
        //input only number for amount
        try {
            Integer.parseInt(amount.getText());
        } catch (IllegalFormatException e) {
            amount.setStyle("-fx-border-color: #ff0000 ;");
        }
    }

    public void handleRecord(ActionEvent event) {
        try {
            System.out.println("Recording...");
            if (categories.getSelectionModel().getSelectedItem().equals("Income")) {
                submitRecord("income", amount.getText(), detail.getText(), convertDate());
                System.out.println("Submitting to income.");
            } else if (categories.getSelectionModel().equals("Outcome")) {
                submitRecord("outcome", amount.getText(), detail.getText(), convertDate());
            } else if (categories.getSelectionModel().equals("Saving")) {
                submitRecord("saving", amount.getText(), detail.getText(), convertDate());
            } else {
                System.out.println("Else");
            }
        } catch (Exception e) {
            System.out.println("Cannot Recording");
            InvalidInput();
        }
        Platform.exit();
    }

    //convert selected items to Localdate format.
    public LocalDate convertDate() {
        LocalDate localDate = null;
        int Year = year.getSelectionModel().getSelectedItem();
        int Month = month.getSelectionModel().getSelectedItem().getValue();
        int Date = date.getSelectionModel().getSelectedItem();
        try{
            localDate = LocalDate.of(Year, Month, Date);
        } catch (DateTimeException e){
            InvalidInput();
        }
        return localDate;
    }

    public void exit() {
        System.exit(1);
    }

    @FXML
        public void openTable(ActionEvent event) {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("TableUI.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Summary");
                Scene scene = new Scene(root);
                scene.getStylesheets().add("Wallet/TableStyle.css");
                stage.setScene(scene);
                stage.show();
                // Hide this current window (if this is what you want)
                //((Node)(event.getSource())).getScene().getWindow().hide();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


