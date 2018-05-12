package Wallet;


public class SummaryTable {

    private int id;
    private String date;
    private String detail;
    private double amount;
    private String type;

    public SummaryTable(int id, String date, double amount, String detail,String type) {
        this.id = id;
        this.date = date;
        this.detail = detail;
        this.amount = amount;
        this.type = type;

    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getDetail() {
        return detail;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

}
