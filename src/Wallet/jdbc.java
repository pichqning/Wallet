package Wallet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class jdbc {
    static String userName;
    static String passWord;
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/wallet";

    static Connection connection;

    public static void readFile() {
        File file = null;
        try {
            file = new File("src/Wallet/UserId");
            //in case of getting wrong directory it will print caution text out
        }catch (Exception e){
            System.out.println("Could not access file " + "src/Wallet/UserId.txt");
            return;
        }
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String readline = reader.readLine();
            while (readline != null) {
                    String[] array = readline.split(";");
                    userName = array[0];
                    passWord = array[1];
                readline = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void openConnection() {
        readFile();
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, userName, passWord);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Cannot establish SQL connection");
        }
    }

    public static void closeConnection() {
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

    public static void removeRecord(String tablename, String colum, Object values) {
        openConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            String sql = "delete from " + tablename + " where " + colum + " = ";
            sql += createInsertStatement(values);
            System.out.println(sql);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public static void submitRecord(String tableName, Object... values) {
        openConnection();
        System.out.println("open connection in submitRecord");
        Statement statement;
        try {
            statement = connection.createStatement();
            String sql = "insert into " + tableName + "(amount, detail, date, type) values(";
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
