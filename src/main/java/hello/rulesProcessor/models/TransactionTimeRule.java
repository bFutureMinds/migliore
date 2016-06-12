package hello.rulesProcessor.models;

import com.fasterxml.jackson.annotation.JsonTypeName;
import hello.rulesProcessor.contracts.TransactionStrategy;

import java.sql.Time;

/**
 * Created by Krishna on 6/11/2016.
 */
@JsonTypeName("time")
public class TransactionTimeRule implements TransactionStrategy {
    private Time toTime;
    private Time fromTime;

    public TransactionTimeRule() {
    }

    public Time getToTime() {
        return toTime;
    }

    public void setToTime(Time toTime) {
        this.toTime = toTime;
    }

    public Time getFromTime() {
        return fromTime;
    }

    public void setFromTime(Time fromTime) {
        this.fromTime = fromTime;
    }

    public TransactionTimeRule(Time toTime, Time fromTime) {
        this.toTime = toTime;
        this.fromTime = fromTime;
    }

    @Override
    public boolean execute(Transaction transaction) {
        return ((toTime.compareTo(transaction.getTime()) <= 0) && (fromTime.compareTo(transaction.getTime()) >= 0));
    }
}
