package Wallet;

import jdk.nashorn.internal.scripts.JD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class jdbc{
    static final String userName = "Raksani";
    static final String passWord = "07102541b";
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/wallet";

    static Connection connection;

    private static void openConnection(){

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, userName, passWord);
            } catch (SQLException | ClassNotFoundException e) {
                System.err.println("Cannot establish SQL connection");
            }
    }

    private static void closeConnection(){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Cannot close SQL connection");
            }
        }
    }

    private static String createInsertStatement(Object ... values){
        StringBuilder sql = new StringBuilder();
        for (Object value : values) {
            if (value.getClass().equals(String.class)) sql.append("\"").append(value).append("\"");
            else if (value.getClass().equals(LocalDate.class)) sql.append("\'").append(value).append("\'");

            else sql.append(value);
            if(!value.equals(values[values.length - 1])) sql.append(", ");

        }
        return sql.toString();
    }

    // TODO writing method for showing the table in monthly.

    public static void submitRecord(String tableName, Object ... values){
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
