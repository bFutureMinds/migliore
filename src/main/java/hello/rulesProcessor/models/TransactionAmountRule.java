package hello.rulesProcessor.models;

import com.fasterxml.jackson.annotation.JsonTypeName;
import hello.rulesProcessor.contracts.TransactionStrategy;

/**
 * Created by Krishna on 6/11/2016.
 */
@JsonTypeName("amount")
public class TransactionAmountRule implements TransactionStrategy {
    private double minimumAmount;
    private double maximumAmount;

    public TransactionAmountRule(double minimumAmount, double maximumAmount) {
        this.minimumAmount = minimumAmount;
        this.maximumAmount = maximumAmount;
    }

    @Override
    public boolean execute(Transaction transaction) {
        return ((transaction.getAmount() >= minimumAmount) && (transaction.getAmount() <= maximumAmount));
    }

    public TransactionAmountRule() {
    }

    public double getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(double minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public double getMaximumAmount() {
        return maximumAmount;
    }

    public void setMaximumAmount(double maximumAmount) {
        this.maximumAmount = maximumAmount;
    }
}
