package Wallet;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static Wallet.jdbc.closeConnection;
import static Wallet.jdbc.openConnection;
import static Wallet.jdbc.connection;

public class StatusController implements Initializable {

    @FXML
    TextArea incomeBox;
    @FXML
    TextArea outcomeBox;
    @FXML
    TextArea savingBox;
    @FXML
    TextArea balanceBox;

    private double balance;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBox();
    }

//    method get total amount from income or outcome or savings.
//    public double getTotalFromColumn(String columnName, String tableName) {
    public double getTotalFromColumn(String tableName) {
        openConnection();
        System.out.println("open connection");
        double result = 0;
        try {
            ResultSet total = connection.createStatement().executeQuery("select amount" + " from " + tableName);
//            total = connection.createStatement().executeQuery("select " + columnName + " from " + tableName);
//            total = connection.createStatement().executeQuery("select amount" + " from " + tableName);
//            if (total.next()) result += Double.parseDouble(total.getString(columnName));
            while (total.next()) {
                result += Double.parseDouble(total.getString("amount"));
            }
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }


    //every time user clicked the submit button, setBox will be called.
    @FXML
    public void setBox() {
        //TODO fix the font and size.
        incomeBox.setText(String.valueOf(getTotalFromColumn("income")));
        outcomeBox.setText(String.valueOf(getTotalFromColumn("outcome")));
        savingBox.setText(String.valueOf(getTotalFromColumn("saving")));
        balance = getTotalFromColumn("income") - getTotalFromColumn("outcome");
        balanceBox.setText(String.valueOf(balance));
    }
}
