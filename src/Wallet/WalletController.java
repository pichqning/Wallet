package Wallet;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class WalletController {
    @FXML
    private ComboBox categories;

    public void initialize () {
        if(categories!=null) {
            categories.getItems().addAll("Income","Outcome","Saving" );
            categories.getSelectionModel().select(0);
        }
    }

}
