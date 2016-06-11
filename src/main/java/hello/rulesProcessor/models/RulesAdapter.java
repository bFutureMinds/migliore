package hello.rulesProcessor.models;

import org.springframework.stereotype.Component;
import hello.rulesProcessor.contracts.MasterStrategy;
import hello.rulesProcessor.contracts.TransactionStrategy;
import hello.rulesProcessor.enums.Operator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krishna on 6/11/2016.
 */
@Component
public class RulesAdapter {
    private List<MasterStrategy> strategies;

    public RulesAdapter(){
        //file reading goes here
        List<Operator> operators = new ArrayList<>();
        operators.add(Operator.AND);
        List<TransactionStrategy> rules = new ArrayList<>();
        rules.add(new TransactionAmountRule(1000, 4000));
        rules.add(new TransactionTypeRule("bar"));
        MasterStrategy strtg1 = new TransactionalMasterStrategy(rules, operators, "uber cab");
        strategies = new ArrayList<>();
        strategies.add(strtg1);
    }

    public void refreshStrategies(){
        //file re read and get new strategies
    }

    public List<String> getOffers(Transaction transaction){
        List<String> offers = new ArrayList<>();
        for (MasterStrategy strategy: strategies) {
            if(strategy.executeRules(transaction)){
                offers.add(strategy.getOffer());
            }
        }
        return offers;
    }
}
