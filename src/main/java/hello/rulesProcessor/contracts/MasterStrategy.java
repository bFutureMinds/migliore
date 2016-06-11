package hello.rulesProcessor.contracts;

import hello.rulesProcessor.models.Transaction;

/**
 * Created by Krishna on 6/11/2016.
 */
public interface MasterStrategy {
    boolean executeRules(Transaction transaction);
    String getOffer();
}
