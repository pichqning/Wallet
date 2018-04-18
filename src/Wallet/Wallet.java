package Wallet;

import java.util.ArrayList;

public class Wallet {
    private ArrayList listIncome = new ArrayList();
    private ArrayList listOutcome = new ArrayList();
    private ArrayList listSaving = new ArrayList();

    public Wallet(ArrayList listIncome, ArrayList listOutcome, ArrayList listSaving){
        this.listIncome = listIncome;
        this.listOutcome = listOutcome;
        this.listSaving = listSaving;

    }
}
