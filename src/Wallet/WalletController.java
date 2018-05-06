package Wallet;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    MenuItem status;
    @FXML
    MenuItem monthly;
    @FXML
    Menu summary;
    @FXML
    TableView<SummaryTable> tableView;
    @FXML
    TableColumn<SummaryTable,Integer> idColumn;
    @FXML
    TableColumn<SummaryTable,String> dateColumn;
//    @FXML
//    TableColumn<SummaryTable,Integer> typeColumn;
    @FXML
    TableColumn<SummaryTable,String> detailColumn;
    @FXML
    TableColumn<SummaryTable,Double> amountColumn;
    @FXML
    Button closeButton;
    @FXML
    Button backButton;
    @FXML
    LineChart lineChart;
    @FXML
    Button showChart;
    @FXML
    Axis yaxis;
    @FXML
    Axis xaxis;
    @FXML
    Label warning;
    @FXML
    TextArea incomeBox;
    @FXML
    TextArea outcomeBox;
    @FXML
    TextArea savingBox;
    @FXML
    TextArea balanceBox;

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

        warning.setText("Invalid input.");
        System.out.println("invalid input");
//        if (detail.getText()==null || amount.getText()==null)  {
//               warning.setText("Please input detail and amount.");
//            }
        //check date unmatch with year (leap year for february) 2020 2024 2028
        //check date unmatch with month (30/31 days)
//        if (date.getSelectionModel().getSelectedItem() > month.getSelectionModel().getSelectedItem().length(Year.isLeap(year.getSelectionModel().getSelectedItem()))) {
//               warning.setText("Invalid date.");
//            }
        //input only number for amount
        try {
            if (amount.getText().length() > 0) Integer.parseInt(amount.getText());
        } catch (IllegalFormatException e) {
            warning.setText("Cannot record the data.");
        }
    }

    public void handleRecord(ActionEvent event) {
        if (detail == null || amount == null) {
            InvalidInput();
        }
        try {
            System.out.println("Recording...");
            if (categories.getSelectionModel().getSelectedItem().equals("Income")) {
                submitRecord("income", amount.getText(), detail.getText(), convertDate());
                System.out.println("Submitting to income.");
            } else if (categories.getSelectionModel().getSelectedItem().equals("Outcome")) {
                submitRecord("outcome", amount.getText(), detail.getText(), convertDate());
                System.out.println("Submitting to outcome.");
            } else if (categories.getSelectionModel().getSelectedItem().equals("Saving")) {
                submitRecord("saving", amount.getText(), detail.getText(), convertDate());
                System.out.println("Submitting to savings.");
            } else {
                System.out.println("Else");
            }
        } catch (Exception e) {
            System.out.println("Cannot Recording");
            InvalidInput();
        }
        //Platform.exit();
    }

    //convert selected items to Localdate format.
    public LocalDate convertDate() {
        LocalDate localDate = null;
        int Year = year.getSelectionModel().getSelectedItem();
        int Month = month.getSelectionModel().getSelectedItem().getValue();
        int Date = date.getSelectionModel().getSelectedItem();
        try {
            localDate = LocalDate.of(Year, Month, Date);
        } catch (DateTimeException e) {
            InvalidInput();
        }
        return localDate;
    }

    public void exit() {
        System.exit(1);
    }

    @FXML
    public void close(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadDataFromDB();
    }

    @FXML
    public void openGraph(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("GraphUI.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Expenses Graph");
            Scene scene = new Scene(root);
            scene.getStylesheets().add("Wallet/GraphStyle.css");
            stage.setScene(scene);
            stage.show();
            // Hide this current window (if this is what you want)
            // ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadDataFromDB() {
        ObservableList<SummaryTable> tableList = null;
        try {
            openConnection();
            tableList = FXCollections.observableArrayList();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM outcome");
            while (resultSet.next()) {
                // add type(table's name)
                SummaryTable summaryTable = new SummaryTable(resultSet.getInt("id"),
                        resultSet.getString("date"), resultSet.getDouble("amount"), resultSet.getString("detail"));
                //get string from db,whichever way
                tableList.add(summaryTable);
                System.out.println(tableList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        System.out.println("set cell");
        //typeColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        detailColumn.setCellValueFactory(new PropertyValueFactory<>("detail"));

        //tableView.setItems(null);
        tableView.setItems(tableList);
    }

    @FXML
    public void openStatus(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("StatusUI.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Status");
            Scene scene = new Scene(root);
            scene.getStylesheets().add("Wallet/StatusStyle.css");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e ) {
            e.printStackTrace();
        }
    }

}


