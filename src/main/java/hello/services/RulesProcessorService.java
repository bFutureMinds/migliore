package hello.services;

import hello.OfferHandler;
import hello.models.CustomerTransaction;
import hello.models.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import hello.rulesProcessor.models.RulesAdapter;
import hello.rulesProcessor.models.Transaction;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by Krishna on 6/11/2016.
 */
@Component
public class RulesProcessorService {

    @Autowired
    private RulesAdapter rulesAdapter;
    @Autowired
    private OfferHandler offerHandler;

    public void processRules(CustomerTransaction transaction){
        Date date = transaction.getDateTime();
        Time time;
        if(date != null){
            time = new Time(date.getHours(), date.getMinutes(), date.getSeconds());
        }else{
            time = new Time(0,0,0);
        }
        Transaction currentTransaction = new Transaction(time,
                transaction.getVendorType(), transaction.getAmount());
        List<Notification> notifications = rulesAdapter.getNotifications(currentTransaction);
        for (Notification notification: notifications) {
            offerHandler.pushOfferCallback(notification);
        }
    }
}
