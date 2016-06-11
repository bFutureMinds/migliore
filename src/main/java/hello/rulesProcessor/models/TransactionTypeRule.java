package hello.rulesProcessor.models;

import hello.rulesProcessor.contracts.TransactionStrategy;

/**
 * Created by Krishna on 6/11/2016.
 */
public class TransactionTypeRule implements TransactionStrategy {
    private String types;

    public TransactionTypeRule(String types) {
        this.types = types;
    }

    @Override
    public boolean execute(Transaction transaction) {
        return transaction.getType().contains(types);
    }
}
