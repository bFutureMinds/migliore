package hello.services;

import hello.OfferHandler;
import hello.models.Notification;
import hello.models.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by Krishna on 6/16/2016.
 */
@Component
public class SocialDataProcessingService {
    @Autowired
    private OfferHandler offerHandler;
    private Map<String, String> lifeEvents = new HashMap<>();

    public SocialDataProcessingService() {
        this.lifeEvents.put("baby_blessed_happy", "Congratulations sir, you are blessed with baby. We are" +
                "offering you 10% off on all baby care products at amazon.com");
        this.lifeEvents.put("travel_fun_road_trip", "Happy journey sir, almost all the petrol pumps support our" +
                "platinum fuel credit card and rewards for hotel booking via barclays lifestyle credit card, " +
                "so no need to carry extra cash. have safe ride :)");
        this.lifeEvents.put("bought_car_happy", "Congratulations sir, On your special day we would like to recommend you" +
                "our Barclays fuel platinum card full of rewards and no fuel surcharge at any fuel pump." +
                " to apply click 'www.barclays.com/credit_card/apply'");
    }

    public void processPost(String description){
        for(String eventKey : lifeEvents.keySet()){
            StringTokenizer tokenizer = new StringTokenizer(eventKey,"_");
            if(isEligible(tokenizer, description)){
                Notification notification = new Notification(NotificationType.OFFER, lifeEvents.get(eventKey));
                offerHandler.pushOfferCallback(notification);
            }
        }
    }

    private boolean isEligible(StringTokenizer tokenizer, String description){
        while (tokenizer.hasMoreTokens()) {
            if(!description.toLowerCase().contains(tokenizer.nextToken())){
                return false;
            }
        }
        return true;
    }
}
