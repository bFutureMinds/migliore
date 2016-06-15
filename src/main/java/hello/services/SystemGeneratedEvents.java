package hello.services;

import hello.OfferHandler;
import hello.models.Notification;
import hello.models.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Created by Krishna on 6/11/2016.
 */
@Component
public class SystemGeneratedEvents {

    @Autowired
    private OfferHandler offerHandler;
    static boolean isBirthDayEventSent;
    static boolean isAnniversaryEventSent;
    private Date birthDate=new Date(2016,05,15);
    private Date anniversaryDate=new Date(2016,05,17);

    @Scheduled(fixedDelay = 1000)
    public void sendBirthDayEvent(){
        Date currentDate = new Date();
        if(currentDate.getMonth() == birthDate.getMonth() && currentDate.getDate() == birthDate.getDate()) {
            Notification notification = new Notification(NotificationType.GREETING, "Wish You Happy Birthday");
            if (!isBirthDayEventSent) {
                isBirthDayEventSent = true;
                sendNotification(notification);
            }
        }
    }

    @Scheduled(fixedDelay = 1000)
    public void sendAnniversaryEvent(){
        Date currentDate = new Date();
        if(currentDate.getMonth() == anniversaryDate.getMonth() && currentDate.getDate() == anniversaryDate.getDate()) {
            Notification notification = new Notification(NotificationType.GREETING, "Wish You Happy Anniversary");
            if (!isAnniversaryEventSent) {
                isAnniversaryEventSent = true;
                sendNotification(notification);
            }
        }
    }

    private void sendNotification(Notification notification){
        offerHandler.pushOfferCallback(notification);
    }

}
