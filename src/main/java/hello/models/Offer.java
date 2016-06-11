package hello.models;

import java.util.List;

/**
 * Created by Krishna on 6/11/2016.
 */
public class Offer {

    private List<String> offers;

    public List<String> getOffers() {
        return offers;
    }

    public Offer(List<String> offers) {
        this.offers = offers;
    }
}
