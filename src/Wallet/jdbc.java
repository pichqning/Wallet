package Wallet;


import javafx.collections.FXCollections;

import java.sql.*;
import java.time.LocalDate;

public class jdbc {
    static final String userName = "Raksani";
    static final String passWord = "07102541b";
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/wallet";

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

    //show the outcome table.
    public String getOutcomeTableBetweenDate(){
        openConnection();
        System.out.println("open connection");
        ResultSet outcome = null;
        String outCome = null;

        try {
            outcome = connection.createStatement().executeQuery("select * from outcome");
            while (outcome.next()) {
                outCome += outcome.getString("outcome");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return outCome;

    }

    // TODO writing method for showing the outcome table in monthly and convert it as a graph.

    public void loadDataFromDB() {
        try {
            openConnection();
            //data = FXCollections.observableArrayList();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM outcome");
            while (resultSet.next()) {
//                SummaryTable summaryTable = new SummaryTable(resultSet.getInt("id"),
//                        resultSet.getString("date"),
//                        resultSet.getDouble("amount"),
//                        resultSet.getString("detail"))
                //get string from db,whichever way
               // data.add(managerDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        id.setCellValueFactory(new PropertyValueFactory<>("id"));
//        from.setCellValueFactory(new PropertyValueFactory<>("depart"));
//        to.setCellValueFactory(new PropertyValueFactory<>("arrive"));
//        departinfo.setCellValueFactory(new PropertyValueFactory<>("departinfo"));
//        arriveinfo.setCellValueFactory(new PropertyValueFactory<>("arriveinfo"));
//        companyinfo.setCellValueFactory(new PropertyValueFactory<>("company"));
//        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
//
//        manage.setItems(null);
//        manage.setItems(data);
    }


    public static void submitRecord(String tableName, Object... values) {
        openConnection();
        System.out.println("open connection");
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
