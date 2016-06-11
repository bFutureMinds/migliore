package hello.rulesProcessor.models;

import java.sql.Time;

/**
 * Created by Krishna on 6/11/2016.
 */
public class Transaction {
    private Time time;
    private String type;
    private double amount;

    public Transaction(Time time, String type, double amount) {
        this.time = time;
        this.type = type;
        this.amount = amount;
    }

    public Time getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}
