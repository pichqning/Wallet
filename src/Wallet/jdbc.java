package Wallet;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;


import java.sql.*;
import java.time.LocalDate;

import static Wallet.WalletController.*;

public class jdbc {
    static final String userName = "Raksani";
    static final String passWord = "07102541b";
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/wallet";
    private static ObservableList<Object> tableList;

    static Connection connection;

    private static void openConnection() {

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, userName, passWord);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Cannot establish SQL connection");
        }
    }

    private static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Cannot close SQL connection");
            }
        }
    }

    private static String createInsertStatement(Object... values) {
        StringBuilder sql = new StringBuilder();
        for (Object value : values) {
            if (value.getClass().equals(String.class)) sql.append("\"").append(value).append("\"");
            else if (value.getClass().equals(LocalDate.class)) sql.append("\'").append(value).append("\'");

            else sql.append(value);
            if (!value.equals(values[values.length - 1])) sql.append(", ");

        }
        return sql.toString();
    }

    //method get total amount from income or outcome or savings.
    public double getTotalFromColumn(String columnName, String tableName) {
        openConnection();
        System.out.println("open connection");
        ResultSet total = null;
        double result = 0;
        try {
            total = connection.createStatement().executeQuery("select " + columnName + " from " + tableName);
            if (total.next()) result += Double.parseDouble(total.getString(columnName));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

//    //show the outcome table.
//    public String getOutcomeTableBetweenDate(){
//        openConnection();
//        System.out.println("open connection");
//        ResultSet outcome = null;
//        String outCome = null;
//
//        try {
//            outcome = connection.createStatement().executeQuery("select * from outcome");
//            while (outcome.next()) {
//                outCome += outcome.getString("outcome");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeConnection();
//        }
//        return outCome;
//
//    }

    //method for showing the summary table.

    public static void loadDataFromDB() {
        try {
            openConnection();
            tableList = FXCollections.observableArrayList();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM outcome");
            while (resultSet.next()) {
                SummaryTable summaryTable = new SummaryTable(resultSet.getInt("id"),
                        resultSet.getString("date"),resultSet.getString("detail"),
                        resultSet.getDouble("amount"));
                //get string from db,whichever way
                tableList.add(summaryTable);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        detailColumn.setCellValueFactory(new PropertyValueFactory<>("detail"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        tableView.setItems(null);
        tableView.setItems(tableList);
    }


    public static void submitRecord(String tableName, Object... values) {
        openConnection();
        System.out.println("open connection in submitRecord");
        Statement statement;
        try {
            statement = connection.createStatement();
            String sql = "insert into " + tableName + "(amount, detail, date) values(";
            sql += createInsertStatement(values);
            sql += ")";
            System.out.println(sql);
            statement.execute(sql);

        } catch (SQLException e) {
            System.err.println("Cannot execute statement");
        } finally {
            closeConnection();
        }

    }
}
