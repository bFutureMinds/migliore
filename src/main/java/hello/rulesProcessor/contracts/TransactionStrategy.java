package hello.rulesProcessor.contracts;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import hello.rulesProcessor.models.Transaction;
import hello.rulesProcessor.models.TransactionAmountRule;
import hello.rulesProcessor.models.TransactionTimeRule;
import hello.rulesProcessor.models.TransactionTypeRule;

/**
 * Created by Krishna on 6/11/2016.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TransactionTimeRule.class, name = "time"),
        @JsonSubTypes.Type(value = TransactionAmountRule.class, name = "amount"),
        @JsonSubTypes.Type(value = TransactionTypeRule.class, name = "type")
})
public interface TransactionStrategy {
    boolean execute(Transaction transaction);
}
