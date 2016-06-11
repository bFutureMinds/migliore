package hello.rulesProcessor.models;

import hello.rulesProcessor.contracts.TransactionStrategy;

import java.sql.Time;

/**
 * Created by Krishna on 6/11/2016.
 */
public class TransactionTimeRule implements TransactionStrategy {

    private Time toTime;
    private Time fromTime;

    public TransactionTimeRule(Time toTime, Time fromTime) {
        this.toTime = toTime;
        this.fromTime = fromTime;
    }

    @Override
    public boolean execute(Transaction transaction) {
        return ((toTime.compareTo(transaction.getTime()) <= 0) && (fromTime.compareTo(transaction.getTime()) >= 0));
    }
}
