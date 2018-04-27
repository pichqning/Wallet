package Wallet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class WalletController{
    @FXML
    private ComboBox categories;
    @FXML
    private ComboBox date;
    @FXML
    private ComboBox month;
    @FXML
    private ComboBox year;
    @FXML
    TextField detail;
    @FXML
    TextField amount;

    //private list of month.
    public void initialize () {
        if(categories!=null) {
            categories.getItems().addAll("Income","Outcome","Saving" );
            categories.getSelectionModel().select(0);
        }
        if(year != null){
            //I cant remember the range of year we set LOL pls fix it if i'm wrong.
            for(int i = 2017; i<= 2021; i++){
                year.getItems().add(i);
            }

        }
        if(month != null){
            month.getItems().addAll("January","Febuary","March","April","May","June","July","August","September","October","November","December");
            categories.getSelectionModel().select(0);
        }
        if(date != null){
            for(int i = 1; i<= 31; i++){
                date.getItems().add(i);
            }
        }
    }

    public void handleRecord(ActionEvent event){
//        //recheck
//        // categories.getValue().equals?
//        if(categories.equals("income")){
//            listIncome.add(date,month,year,detail.getText(),amount.getText());
//        }
//        if(categories.equals("outcome")){
//            listIncome.add(date,month,year,detail.getText(),amount.getText());
//        }
//        if(categories.equals("Saving")){
//            listIncome.add(date,month,year,detail.getText(),amount.getText());
//        }
    }

}
