package Wallet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static Wallet.jdbc.connection;
import static Wallet.jdbc.openConnection;
import static Wallet.jdbc.removeRecord;

/**
 * Controller for summary table ,contain with Id column , date column , detail column , amount column and type column.
 * @author Pichaaun Popukdee .Raksani Kunamas.
 */
public class TableController implements Initializable {

    @FXML
    TableView<SummaryTable> tableView;
    @FXML
    TableColumn<SummaryTable, Integer> idColumn;
    @FXML
    TableColumn<SummaryTable, String> dateColumn;
    @FXML
    TableColumn<SummaryTable, String> typeColumn;
    @FXML
    TableColumn<SummaryTable, String> detailColumn;
    @FXML
    TableColumn<SummaryTable, Double> amountColumn;
    @FXML
    Button closeButton;
    @FXML
    Button showChart;
    @FXML
    Button remove;


    public static List<String> dateList = new ArrayList<>();
    public static List<Double> datalist = new ArrayList<>();
    public static List<String> dateList2 = new ArrayList<>();
    public static List<Double> dataList2 = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDataFromDB();
    }


    @FXML
    public void close(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    /**
     * load data from database and put data in the table.
     */
    @FXML
    public void loadDataFromDB() {
        ObservableList<SummaryTable> tableList = null;
        try {
            tableList = FXCollections.observableArrayList();
            openConnection();
            //select * from where date like... is... in case of insert specific month and year. (optional)
            ResultSet incomeResultSet = connection.createStatement().executeQuery("SELECT * FROM income");
            ResultSet outcomeResultSet = connection.createStatement().executeQuery("SELECT * FROM outcome");
            ResultSet savingResultSet = connection.createStatement().executeQuery("SELECT * FROM saving");

            while (incomeResultSet.next()) {
                SummaryTable incomeSummaryTable = new SummaryTable(incomeResultSet.getInt("id"),
                        incomeResultSet.getString("date"), incomeResultSet.getDouble("amount"),
                        incomeResultSet.getString("detail"), incomeResultSet.getString("type"));
                tableList.add(incomeSummaryTable);

                dataList2.add(incomeSummaryTable.getAmount());
                dateList2.add(incomeSummaryTable.getDate());
                //System.out.println(tableList.get(0).getDetail());
            }
            while (outcomeResultSet.next()) {
                SummaryTable outcomeSummaryTable = new SummaryTable(outcomeResultSet.getInt("id"),
                        outcomeResultSet.getString("date"), outcomeResultSet.getDouble("amount"),
                        outcomeResultSet.getString("detail"), outcomeResultSet.getString("type"));
                tableList.add(outcomeSummaryTable);

                datalist.add(outcomeSummaryTable.getAmount());
                dateList.add(outcomeSummaryTable.getDate());
                System.out.print(dateList);
            }
            while (savingResultSet.next()) {
                SummaryTable savingSummaryTable = new SummaryTable(savingResultSet.getInt("id"),
                        savingResultSet.getString("date"), savingResultSet.getDouble("amount"),
                        savingResultSet.getString("detail"), savingResultSet.getString("type"));
                tableList.add(savingSummaryTable);
            }
//            Comparator<SummaryTable> summaryTableComparator = new Comparator<SummaryTable>() {
//                @Override
//                public int compare(SummaryTable o1, SummaryTable o2) {
//                    return o2.getDate().compareTo(o1.getDate());
//                }
//            };
//            tableList.sort(summaryTableComparator);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        detailColumn.setCellValueFactory(new PropertyValueFactory<>("detail"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableView.setItems(null);
        tableView.setItems(tableList);
    }


    /**
     * When user select the row on table and click remove button. The data will be deleted.
     */
    @FXML
    public void remove(){
        removeRecord(tableView.getSelectionModel().getSelectedItem().getType(),"id",tableView.getSelectionModel().getSelectedItem().getId());
        loadDataFromDB();
    }

    /**
     * Open new chart window.
     * @param event
     */
    @FXML
    public void openGraph(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("GraphUI.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Expenses Graph");

            stage.setTitle("Line Chart Sample");
            Scene scene = new Scene(root);

           scene.getStylesheets().add("Wallet/GraphStyle.css");
            stage.setScene(scene);
            stage.show();
            // Hide this current window
            // ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
