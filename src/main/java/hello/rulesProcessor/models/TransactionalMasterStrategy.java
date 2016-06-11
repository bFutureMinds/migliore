package hello.rulesProcessor.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hello.models.Notification;
import hello.rulesProcessor.contracts.TransactionStrategy;
import hello.rulesProcessor.enums.Operator;

import java.util.List;

/**
 * Created by Krishna on 6/11/2016.
 */
public class TransactionalMasterStrategy {
    private List<TransactionStrategy> rules;
    private List<Operator> operators;
    private Notification notification;
    private int id;

    public TransactionalMasterStrategy(int id, List<TransactionStrategy> rules, List<Operator> operators, Notification notification) {
        this.id = id;
        this.rules = rules;
        this.operators = operators;
        this.notification = notification;
    }

    public TransactionalMasterStrategy(){

    }

    public List<TransactionStrategy> getRules() {
        return rules;
    }

    public void setRules(List<TransactionStrategy> rules) {
        this.rules = rules;
    }

    public List<Operator> getOperators() {
        return operators;
    }

    public void setOperators(List<Operator> operators) {
        this.operators = operators;
    }

    public void setOffer(Notification notification) {
        this.notification = notification;
    }

    public boolean executeRules(Transaction currentTransaction) {
        if((operators == null) || (operators.size() == 0)){
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

    public Notification getNotification() {
        return notification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
