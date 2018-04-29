package Wallet;

import java.time.LocalDate;

public class Maintest {
    public static void main(String[] args){
        String tableName = "income";
        jdbc.submitRecord(tableName,4,LocalDate.of(2018,05,12),1000.0,"Car Bill");
    }
}
