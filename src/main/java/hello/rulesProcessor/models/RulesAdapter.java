package hello.rulesProcessor.models;

import hello.models.Notification;
import hello.rulesProcessor.factory.RulesFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krishna on 6/11/2016.
 */
@Component
public class RulesAdapter {
    private static List<TransactionalMasterStrategy> strategies;
    private static int counter = 1;

    public RulesAdapter(){
        strategies = new ArrayList<>();
        strategies.addAll(RulesFactory.getStrategies());
    }

    public List<Notification> getNotifications(Transaction transaction){
        List<Notification> notifications = new ArrayList<>();
        for (TransactionalMasterStrategy strategy: strategies) {
            if(strategy.executeRules(transaction)){
                notifications.add(strategy.getNotification());
            }
        }
        return notifications;
    }
    
    public void deleteRule(int id){
        for (TransactionalMasterStrategy masterStrategy: strategies) {
            if(masterStrategy.getId() == id){
                strategies.remove(masterStrategy);
            }
        }
    }

    public void addRule(TransactionalMasterStrategy masterStrategy){
        masterStrategy.setId(counter++);
        strategies.add(masterStrategy);
    }

    public void updateRule(TransactionalMasterStrategy masterStrategy){
        deleteRule(masterStrategy.getId());
        strategies.add(masterStrategy);
    }

    public List<TransactionalMasterStrategy> getAll(){
        List<TransactionalMasterStrategy> tempList = new ArrayList<>();
        tempList.addAll(strategies);
        return tempList;
    }
}
