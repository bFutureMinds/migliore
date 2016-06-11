package hello.rulesProcessor.models;

import hello.rulesProcessor.contracts.MasterStrategy;
import hello.rulesProcessor.contracts.TransactionStrategy;
import hello.rulesProcessor.enums.Operator;

import java.util.List;

/**
 * Created by Krishna on 6/11/2016.
 */
public class TransactionalMasterStrategy implements MasterStrategy {
    private List<TransactionStrategy> rules;
    private List<Operator> operators;
    private String offer;

    public TransactionalMasterStrategy(List<TransactionStrategy> rules, List<Operator> operators, String offer) {
        this.rules = rules;
        this.operators = operators;
        this.offer = offer;
    }

    @Override
    public boolean executeRules(Transaction currentTransaction) {
        if(operators.size() == 0){
            return rules.get(0).execute(currentTransaction);
        } else {
            boolean result = rules.get(0).execute(currentTransaction);
            int rulesIndex = 1;
            for (Operator operator: operators) {
                if(operator == Operator.AND){
                    result = result && (rules.get(rulesIndex++).execute(currentTransaction));
                }else {
                    result = result || (rules.get(rulesIndex++).execute(currentTransaction));
                }
            }
            return result;
        }
    }

    @Override
    public String getOffer() {
        return offer;
    }
}
