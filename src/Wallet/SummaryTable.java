package Wallet;

import javafx.beans.property.*;


public class SummaryTable {
        private IntegerProperty id;
        private StringProperty date;
        private StringProperty detail;
        private DoubleProperty amount;

        public SummaryTable(int id, String date, String detail, double amount) {
            this.id = new SimpleIntegerProperty(id);
            this.date = new SimpleStringProperty(date);
            this.detail = new SimpleStringProperty(detail);
            this.amount = new SimpleDoubleProperty(amount);

        }


        public int getId() {
            return id.get();
        }

        public IntegerProperty idProperty() {
            return id;
        }

        public String getDate() {
            return date.get();
        }

        public StringProperty dateProperty() {
            return date;
        }

        public String getDetail() {
            return detail.get();
        }

        public StringProperty detailProperty() {
            return detail;
        }

        public double getAmount() {
            return amount.get();
        }

        public DoubleProperty amountProperty() {
            return amount;
        }
    }
