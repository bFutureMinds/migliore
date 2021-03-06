package hello.rulesProcessor.models;

import com.fasterxml.jackson.annotation.JsonTypeName;
import hello.rulesProcessor.contracts.TransactionStrategy;

/**
 * Created by Krishna on 6/11/2016.
 */
@JsonTypeName("type")
public class TransactionTypeRule implements TransactionStrategy {
    private String types;

    public TransactionTypeRule() {
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public TransactionTypeRule(String types) {
        this.types = types;
    }

    @Override
    public boolean execute(Transaction transaction) {
        return transaction.getType().contains(types);
    }
}
