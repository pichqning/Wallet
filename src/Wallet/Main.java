package Wallet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
    To run an application.
*/
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("WalletUI.fxml"));
        stage.setTitle("My Wallet");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("Wallet/WalletStyle.css");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
//        jdbc jdbc = new jdbc();
//        System.out.println(jdbc.getTotalFromColumn("amount", "income"));
    }
}
