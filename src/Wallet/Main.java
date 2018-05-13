package Wallet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * To run an application.
 * @author Pichaaun Popukdee , Raksani Kunamas.
 *
*/
public class Main extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("WalletUI.fxml"));
        this.stage = stage;
        stage.setTitle("My Wallet");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("Wallet/WalletStyle.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
