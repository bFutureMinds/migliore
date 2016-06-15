package hello.rulesProcessor.factory;

import hello.models.Notification;
import hello.models.NotificationType;
import hello.rulesProcessor.contracts.TransactionStrategy;
import hello.rulesProcessor.enums.Operator;
import hello.rulesProcessor.models.TransactionAmountRule;
import hello.rulesProcessor.models.TransactionTimeRule;
import hello.rulesProcessor.models.TransactionTypeRule;
import hello.rulesProcessor.models.TransactionalMasterStrategy;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krishna on 6/16/2016.
 */
public class RulesFactory {

    public static List<TransactionalMasterStrategy> getStrategies(){
        List<TransactionalMasterStrategy> masterStrategies = new ArrayList<>();
        List<TransactionStrategy> transactionStrategies = new ArrayList<>();
        TransactionStrategy typeStrategy = new TransactionTypeRule("bar");
        TransactionStrategy timeStrategy = new TransactionTimeRule(new Time(22,0,0), new Time(23,59,59));
        transactionStrategies.add(typeStrategy);
        transactionStrategies.add(timeStrategy);

        List<Operator> operators = new ArrayList<>();
        operators.add(Operator.AND);
        Notification notification = new Notification(NotificationType.OFFER, "Let us assist you to book a cab for you , With just a click");
        TransactionalMasterStrategy strategy = new TransactionalMasterStrategy(1, transactionStrategies, operators, notification);

        List<TransactionStrategy> transactionStrategies1 = new ArrayList<>();
        TransactionStrategy typeStrategy1 = new TransactionTypeRule("amazon");
        TransactionStrategy amountStrategy1 = new TransactionAmountRule(5000, 100000);
        transactionStrategies1.add(typeStrategy1);
        transactionStrategies1.add(amountStrategy1);

        List<Operator> operators1 = new ArrayList<>();
        operators1.add(Operator.AND);
        Notification notification1 = new Notification(NotificationType.OFFER, "Dear Customer, We have co-branded card with amazon " +
                "with exciting offers. for Apply click www.barclays.com/creditcards/apply");
        TransactionalMasterStrategy strategy1 = new TransactionalMasterStrategy(1, transactionStrategies1, operators1, notification1);
        masterStrategies.add(strategy);
        masterStrategies.add(strategy1);
        return masterStrategies;
    }
}
